<template>
  <div style="max-width:400px;margin:40px auto;">
    <h2>Login</h2>
    <form @submit.prevent="onLogin">
      <div><input v-model="email" placeholder="Email" type="email" required /></div>
      <div><input v-model="password" placeholder="Password" type="password" required /></div>
      <button type="submit">Login</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { useAuthStore } from '../store/auth'
const email = ref('')
const password = ref('')
const auth = useAuthStore()
async function onLogin() {
  await auth.login(email.value, password.value)
}
</script>
