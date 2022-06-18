import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import Objects from '../components/objects/SportObjects.vue'
import AdminProfilesView from "../components/admin/views/AdminProfilesView.vue";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  mode: 'hash',
  routes: [
    {
      path: '/',
      name: 'home',
      component: HomeView
    },
    {
      path: '/objects',
      name: 'objects',
      component: Objects
    },
    {
      path: '/admin/profiles',
      name: "admin-profiles",
      component: AdminProfilesView
    }
  ]
})

export default router
