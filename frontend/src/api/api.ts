import axios from 'axios'

const base = (import.meta.env.VITE_API_URL || '/api') as string
const api = axios.create({ baseURL: base })

// Attach token if stored in localStorage (simple approach)
api.interceptors.request.use((config) => {
  const token = localStorage.getItem('gb_token')
  if (token) config.headers = { ...(config.headers || {}), Authorization: `Bearer ${token}` }
  return config
})

export default api
