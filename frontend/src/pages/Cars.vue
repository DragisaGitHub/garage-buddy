<template>
  <div style="padding:20px;">
    <h1>Your Cars</h1>
    <button @click="showForm = true">Add Car</button>
    <CarForm v-if="showForm" @saved="onSaved" @cancel="showForm=false" />
    <div v-for="car in cars" :key="car.id" style="border-bottom:1px solid #eee;padding:8px 0;">
      <div>{{ car.brand }} {{ car.model }} ({{ car.year }})</div>
    </div>
  </div>
</template>

<script setup lang="ts">
import { ref, onMounted } from 'vue'
import api from '../api/api'
import CarForm from './CarForm.vue'

const cars = ref([] as any[])
const showForm = ref(false)

async function load() {
  const res = await api.get('/cars')
  cars.value = res.data
}

onMounted(load)

function onSaved() { showForm.value = false; load() }
</script>
