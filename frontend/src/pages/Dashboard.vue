<template>
  <div style="padding:20px;">
    <h1>Garage Dashboard</h1>
    <div v-if="cars.length === 0">No cars yet. Add one on the Cars page.</div>
    <div v-for="car in cars" :key="car.id" style="border:1px solid #ddd;padding:12px;margin:8px 0;">
      <div><strong>{{ car.brand }} {{ car.model }}</strong> — VIN: {{ car.vin }}</div>
      <div>Health: <strong>{{ car.health }}</strong></div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '../api/api'

const cars = ref([] as any[])
onMounted(async () => {
  const res = await api.get('/dashboard')
  cars.value = res.data.cars || []
})
</script>
