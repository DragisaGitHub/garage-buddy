<template>
  <div style="margin:12px 0;padding:12px;border:1px solid #ddd;">
    <h3>Add Car</h3>
    <form @submit.prevent="save">
      <div><input v-model="brand" placeholder="Brand" required /></div>
      <div><input v-model="model" placeholder="Model" required /></div>
      <div><input v-model.number="year" type="number" placeholder="Year" required /></div>
      <div><input v-model.number="mileage" type="number" placeholder="Mileage" required /></div>
      <div><input v-model="vin" placeholder="VIN" required /></div>
      <div><input v-model="registrationExpiry" type="date" required /></div>
      <div><input v-model.number="purchasePrice" type="number" step="0.01" placeholder="Purchase Price" required /></div>
      <button type="submit">Save</button>
      <button type="button" @click="$emit('cancel')">Cancel</button>
    </form>
  </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import api from '../api/api'

const brand = ref('')
const model = ref('')
const year = ref<number | null>(new Date().getFullYear())
const mileage = ref(0)
const vin = ref('')
const registrationExpiry = ref('')
const purchasePrice = ref<number | null>(0)

async function save() {
  const payload = {
    brand: brand.value,
    model: model.value,
    year: year.value,
    mileage: mileage.value,
    vin: vin.value,
    registrationExpiry: registrationExpiry.value,
    purchasePrice: purchasePrice.value
  }
  await api.post('/cars', payload)
  // notify parent
  (window as any).dispatchEvent(new CustomEvent('carSaved'))
  // or emit
  // $emit('saved') - $emit not available in <script setup> without defineEmits
  location.reload()
}
</script>
