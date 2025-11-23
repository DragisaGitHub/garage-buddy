import { createRouter, createWebHistory } from 'vue-router'
import Login from '../pages/Login.vue'
import Register from '../pages/Register.vue'
import Dashboard from '../pages/Dashboard.vue'
import Cars from '../pages/Cars.vue'
import Reminders from '../pages/Reminders.vue'

const routes = [
  { path: '/', component: Dashboard },
  { path: '/login', component: Login },
  { path: '/register', component: Register },
  { path: '/cars', component: Cars },
  { path: '/reminders', component: Reminders }
]

const router = createRouter({ history: createWebHistory(), routes })
export default router
