import { createRouter, createWebHistory } from 'vue-router'
import Login from '../pages/Login.vue'
import Dashboard from '../pages/Dashboard.vue'
import Cars from '../pages/Cars.vue'

const routes = [
  { path: '/', component: Dashboard },
  { path: '/login', component: Login },
  { path: '/cars', component: Cars }
]

const router = createRouter({ history: createWebHistory(), routes })
export default router
