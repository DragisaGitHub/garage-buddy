<template>
  <div style="padding:20px;">
    <h1>Service Events</h1>
    <div v-for="ev in events" :key="ev.id" style="border-bottom:1px solid #eee;padding:8px 0;">
      <div>{{ ev.date }} — {{ ev.description }} — {{ ev.cost }}</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '../api/api'
const events = ref([] as any[])
onMounted(async () => {
  const res = await api.get('/cars/1/service-events')
  events.value = res.data
})
</script>
