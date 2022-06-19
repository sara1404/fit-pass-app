import { createRouter, createWebHistory } from 'vue-router'
import { useProfileStore } from "../stores/profile-store.js";
import HomeView from '../views/HomeView.vue'
import Objects from '../components/objects/SportObjects.vue'
import AdminProfilesView from "../components/admin/views/AdminProfilesView.vue";
import SportObjectView from "../views/SportObjectView.vue"
import ProfileView from "../views/ProfileView.vue"


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
      component: AdminProfilesView,
      beforeEnter: (to, from) => {
        let profileStore = useProfileStore()
        if(!profileStore.getLoggedIn || profileStore.getLoggedProfile.role !== "ADMIN")
          return { route: "/" }
        return true
      }
    },
    {
      path: '/users/me',
      name: 'user-profile',
      component: ProfileView
    },
    {
      path: '/objects/:id',
      name: 'object',
      component: SportObjectView
    },
  ]
})

router.beforeEach(async (to, from, next) => {
  let profileStore = useProfileStore()
  await profileStore.tryAlreadyLoggedIn();
  next()
})




export default router
