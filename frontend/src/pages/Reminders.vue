<template>
  <div style="padding:20px;">
    <h1>Reminders</h1>
    <div v-for="r in reminders" :key="r.id">{{ r.type }} — due: {{ r.dueDate }} — sent: {{ r.sent }}</div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '../api/api'

const reminders = ref([] as any[])
onMounted(async () => {
  const res = await api.get('/reminders/due')
  reminders.value = res.data
})
</script>
