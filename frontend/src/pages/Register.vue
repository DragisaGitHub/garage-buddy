<template>
  <div style="max-width:400px;margin:40px auto;">
    <h2>Register</h2>
    <form @submit.prevent="onRegister">
      <div><input v-model="name" placeholder="Name" required /></div>
      <div><input v-model="email" placeholder="Email" type="email" required /></div>
      <div><input v-model="password" placeholder="Password" type="password" required /></div>
      <button type="submit">Register</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useAuthStore } from '../store/auth'
import { useRouter } from 'vue-router'

const name = ref('')
const email = ref('')
const password = ref('')
const auth = useAuthStore()
const router = useRouter()

async function onRegister() {
  try {
    const res = await auth.register(email.value, password.value, name.value)
    if (res && res.token) {
      auth.token = res.token
      localStorage.setItem('gb_token', res.token)
    }
    router.push('/dashboard')
  } catch (e) {
    // ignore for now
  }
}
</script>
