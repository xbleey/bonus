const STATE_KEY = 'bonus_oauth_state'
const VERIFIER_KEY = 'bonus_oauth_verifier'
const REDIRECT_KEY = 'bonus_oauth_redirect'

const generateRandomString = (length = 64) => {
  const charset = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-._~'
  const randomValues = new Uint8Array(length)
  crypto.getRandomValues(randomValues)
  return Array.from(randomValues, (value) => charset[value % charset.length]).join('')
}

const base64UrlEncode = (buffer) => {
  const bytes = new Uint8Array(buffer)
  let binary = ''
  bytes.forEach((byte) => {
    binary += String.fromCharCode(byte)
  })
  return btoa(binary).replace(/\+/g, '-').replace(/\//g, '_').replace(/=+$/, '')
}

const sha256 = async (value) => {
  const encoder = new TextEncoder()
  const data = encoder.encode(value)
  const digest = await crypto.subtle.digest('SHA-256', data)
  return base64UrlEncode(digest)
}

export const getRedirectUri = () => {
  const configured = import.meta.env.VITE_OAUTH2_REDIRECT_URI
  return configured || `${window.location.origin}/oauth2/callback`
}

export const startOauthLogin = async ({ redirect } = {}) => {
  const authUrl = import.meta.env.VITE_OAUTH2_AUTH_URL
  const clientId = import.meta.env.VITE_OAUTH2_CLIENT_ID
  const scope = import.meta.env.VITE_OAUTH2_SCOPE || 'openid profile email'

  if (!authUrl || !clientId) {
    throw new Error('Missing OAuth2 config. Set VITE_OAUTH2_AUTH_URL and VITE_OAUTH2_CLIENT_ID.')
  }

  const state = generateRandomString(24)
  const verifier = generateRandomString(96)
  const challenge = await sha256(verifier)
  const redirectUri = getRedirectUri()

  sessionStorage.setItem(STATE_KEY, state)
  sessionStorage.setItem(VERIFIER_KEY, verifier)
  sessionStorage.setItem(REDIRECT_KEY, redirect || '/')

  const params = new URLSearchParams({
    response_type: 'code',
    client_id: clientId,
    redirect_uri: redirectUri,
    scope,
    state,
    code_challenge: challenge,
    code_challenge_method: 'S256',
  })

  window.location.href = `${authUrl}?${params.toString()}`
}

export const getOauthState = () => sessionStorage.getItem(STATE_KEY)

export const getOauthVerifier = () => sessionStorage.getItem(VERIFIER_KEY)

export const getOauthRedirect = () => sessionStorage.getItem(REDIRECT_KEY) || '/'

export const clearOauthSession = () => {
  sessionStorage.removeItem(STATE_KEY)
  sessionStorage.removeItem(VERIFIER_KEY)
  sessionStorage.removeItem(REDIRECT_KEY)
}
