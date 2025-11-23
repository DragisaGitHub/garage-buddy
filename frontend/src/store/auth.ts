import { defineStore } from 'pinia'
import api from '../api/api'

export const useAuthStore = defineStore('auth', {
  state: () => ({ user: null as any, token: null as string | null }),
  actions: {
    async register(email: string, password: string, name?: string) {
      const res = await api.post('/auth/register', { email, password, name })
      return res.data
    },
    async login(email: string, password: string) {
      // placeholder login - implement endpoint when backend supports login
      const res = await api.post('/auth/login', { email, password }).catch(() => null)
      if (res && res.data && res.data.token) {
        this.token = res.data.token
        localStorage.setItem('gb_token', this.token)
      }
    },
    logout() {
      this.token = null
      this.user = null
      localStorage.removeItem('gb_token')
    }
  }
})
