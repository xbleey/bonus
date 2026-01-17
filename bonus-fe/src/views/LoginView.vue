<script setup>
import { computed, ref } from 'vue'
import { ElMessage } from 'element-plus'
import { useRoute } from 'vue-router'
import { clearToken, getToken } from '@/utils/auth'
import { startOauthLogin } from '@/utils/oauth'

const route = useRoute()
const loading = ref(false)
const tokenPresent = ref(Boolean(getToken()))

const missingConfig = computed(
  () => !import.meta.env.VITE_OAUTH2_AUTH_URL || !import.meta.env.VITE_OAUTH2_CLIENT_ID
)

const handleLogin = async () => {
  if (missingConfig.value) {
    ElMessage.error('Configure OAuth2 env vars before logging in.')
    return
  }

  const redirect =
    typeof route.query.redirect === 'string' && route.query.redirect.length > 0
      ? route.query.redirect
      : '/'

  loading.value = true
  try {
    await startOauthLogin({ redirect })
  } catch (error) {
    ElMessage.error(error?.message || 'Login failed. Please try again.')
    loading.value = false
  }
}

const handleClearToken = () => {
  clearToken()
  tokenPresent.value = false
  ElMessage.success('Local JWT cleared.')
}
</script>

<template>
  <div class="login-page">
    <div class="login-shell">
      <header class="login-header">
        <div class="brand-mark">B</div>
        <div>
          <p class="eyebrow">Bonus Console</p>
          <h1>Unified Sign-In</h1>
          <p class="subhead">Use OAuth2 SSO to fetch a JWT and access the workspace.</p>
        </div>
      </header>

      <el-card class="login-card" shadow="always">
        <div class="card-title">
          <h2>Enterprise Authentication</h2>
          <p>Authorization Code + PKCE</p>
        </div>

        <el-button
          type="primary"
          size="large"
          class="login-button"
          :loading="loading"
          @click="handleLogin"
        >
          Continue with SSO
        </el-button>

        <el-alert
          v-if="missingConfig"
          title="OAuth2 configuration missing"
          type="warning"
          show-icon
          class="config-alert"
          description="Set VITE_OAUTH2_AUTH_URL and VITE_OAUTH2_CLIENT_ID in your .env file."
        />

        <div class="token-row">
          <el-tag :type="tokenPresent ? 'success' : 'info'">
            Local JWT: {{ tokenPresent ? 'Present' : 'Missing' }}
          </el-tag>
          <el-button text :disabled="!tokenPresent" @click="handleClearToken">Clear</el-button>
        </div>
      </el-card>

      <footer class="login-footer">
        <span>You will be redirected to the authorization server.</span>
        <span>The returned JWT is stored for subsequent API calls.</span>
      </footer>
    </div>
    <div class="login-orb orb-1"></div>
    <div class="login-orb orb-2"></div>
    <div class="login-orb orb-3"></div>
  </div>
</template>

<style scoped>
.login-page {
  position: relative;
  min-height: 100vh;
  display: grid;
  place-items: center;
  padding: 48px 24px;
  overflow: hidden;
}

.login-shell {
  position: relative;
  z-index: 2;
  max-width: 520px;
  width: 100%;
  display: grid;
  gap: 24px;
}

.login-header {
  display: grid;
  grid-template-columns: auto 1fr;
  gap: 16px;
  align-items: center;
}

.brand-mark {
  width: 56px;
  height: 56px;
  border-radius: 18px;
  background: linear-gradient(135deg, #0f8b8d 0%, #3cbb87 100%);
  color: #fff;
  display: grid;
  place-items: center;
  font-weight: 700;
  font-size: 26px;
  letter-spacing: 1px;
  box-shadow: 0 12px 30px rgba(15, 139, 141, 0.3);
}

.eyebrow {
  text-transform: uppercase;
  letter-spacing: 0.2em;
  font-size: 12px;
  color: rgba(11, 31, 42, 0.7);
}

h1 {
  margin: 6px 0 8px;
  font-size: 34px;
  line-height: 1.1;
  color: #0b1f2a;
}

.subhead {
  color: rgba(11, 31, 42, 0.7);
  font-size: 16px;
}

.login-card {
  border-radius: 24px;
  border: none;
  background: rgba(255, 255, 255, 0.92);
  backdrop-filter: blur(8px);
  box-shadow: 0 20px 60px rgba(15, 24, 40, 0.18);
}

.card-title h2 {
  margin: 0;
  font-size: 22px;
  color: #0b1f2a;
}

.card-title p {
  margin: 6px 0 20px;
  color: rgba(11, 31, 42, 0.6);
}

.login-button {
  width: 100%;
  height: 48px;
  font-weight: 600;
}

.config-alert {
  margin-top: 16px;
}

.token-row {
  margin-top: 18px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.login-footer {
  display: grid;
  gap: 6px;
  font-size: 13px;
  color: rgba(11, 31, 42, 0.6);
}

.login-orb {
  position: absolute;
  border-radius: 50%;
  filter: blur(0px);
  opacity: 0.7;
}

.orb-1 {
  width: 280px;
  height: 280px;
  top: -80px;
  right: -60px;
  background: radial-gradient(circle at 30% 30%, #f7c59f, #f29e4c);
}

.orb-2 {
  width: 220px;
  height: 220px;
  bottom: -80px;
  left: -60px;
  background: radial-gradient(circle at 30% 30%, #9de1c9, #3cbb87);
}

.orb-3 {
  width: 160px;
  height: 160px;
  bottom: 120px;
  right: 10%;
  background: radial-gradient(circle at 30% 30%, #c7e0ff, #4c9bf2);
  opacity: 0.4;
}

@media (max-width: 600px) {
  .login-shell {
    gap: 18px;
  }

  h1 {
    font-size: 28px;
  }
}
</style>
