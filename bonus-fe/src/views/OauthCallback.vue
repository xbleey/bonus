<script setup>
import { onMounted, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Loading } from '@element-plus/icons-vue'
import { useRoute, useRouter } from 'vue-router'
import { setToken } from '@/utils/auth'
import {
  clearOauthSession,
  getOauthRedirect,
  getOauthState,
  getOauthVerifier,
  getRedirectUri,
} from '@/utils/oauth'

const route = useRoute()
const router = useRouter()
const status = ref('Finishing sign-in...')

const exchangeToken = async (code) => {
  const tokenUrl = import.meta.env.VITE_OAUTH2_TOKEN_URL
  const clientId = import.meta.env.VITE_OAUTH2_CLIENT_ID
  const redirectUri = getRedirectUri()
  const codeVerifier = getOauthVerifier()

  if (!tokenUrl || !clientId) {
    throw new Error('Missing OAuth2 config. Set VITE_OAUTH2_TOKEN_URL and VITE_OAUTH2_CLIENT_ID.')
  }

  if (!codeVerifier) {
    throw new Error('Missing PKCE verifier. Please sign in again.')
  }

  const body = new URLSearchParams({
    grant_type: 'authorization_code',
    client_id: clientId,
    code,
    redirect_uri: redirectUri,
    code_verifier: codeVerifier,
  })

  const response = await fetch(tokenUrl, {
    method: 'POST',
    headers: {
      'Content-Type': 'application/x-www-form-urlencoded',
    },
    body,
  })

  const data = await response.json().catch(() => ({}))

  if (!response.ok) {
    const message = data?.error_description || data?.error || 'Authorization failed.'
    throw new Error(message)
  }

  if (!data?.access_token) {
    throw new Error('No access_token returned.')
  }

  return data.access_token
}

onMounted(async () => {
  const code = typeof route.query.code === 'string' ? route.query.code : ''
  const state = typeof route.query.state === 'string' ? route.query.state : ''
  const storedState = getOauthState()

  if (!code) {
    status.value = 'Invalid authorization response.'
    ElMessage.error('No authorization code returned.')
    return
  }

  if (!state || state !== storedState) {
    status.value = 'State verification failed.'
    ElMessage.error('State verification failed. Please sign in again.')
    clearOauthSession()
    return
  }

  try {
    status.value = 'Exchanging code for JWT...'
    const token = await exchangeToken(code)
    setToken(token)
    clearOauthSession()
    status.value = 'Sign-in complete. Redirecting...'
    await router.replace(getOauthRedirect())
  } catch (error) {
    status.value = 'Sign-in failed.'
    ElMessage.error(error?.message || 'Sign-in failed. Please retry.')
    clearOauthSession()
  }
})
</script>

<template>
  <div class="callback-page">
    <el-card class="callback-card" shadow="always">
      <el-icon class="callback-icon" size="32">
        <Loading />
      </el-icon>
      <h2>Working on it</h2>
      <p>{{ status }}</p>
    </el-card>
  </div>
</template>

<style scoped>
.callback-page {
  min-height: 100vh;
  display: grid;
  place-items: center;
  padding: 32px 20px;
}

.callback-card {
  border-radius: 20px;
  border: none;
  text-align: center;
  padding: 24px 20px;
  min-width: 280px;
}

.callback-icon {
  color: #0f8b8d;
  margin-bottom: 12px;
}

h2 {
  margin: 0 0 8px;
  color: #0b1f2a;
}

p {
  color: rgba(11, 31, 42, 0.6);
}
</style>
