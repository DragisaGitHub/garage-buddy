<template>
  <span :style="style">{{ label }}</span>
</template>

<script setup lang="ts">
import { computed } from 'vue'
const props = defineProps<{ level: string }>()
const label = computed(() => props.level || 'UNKNOWN')
const style = computed(() => ({ padding: '2px 6px', borderRadius: '4px', background: props.level === 'GREEN' ? '#d4edda' : props.level === 'YELLOW' ? '#fff3cd' : '#f8d7da' }))
</script>
