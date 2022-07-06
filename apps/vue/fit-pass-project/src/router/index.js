import { createRouter, createWebHistory } from 'vue-router'
import { useProfileStore } from "../stores/profile-store.js";
import HomeView from '../views/HomeView.vue'
import Objects from '../components/objects/SportObjects.vue'
import AdminProfilesView from "../components/admin/views/AdminProfilesView.vue";
import SportObjectView from "../views/SportObjectView.vue"
import ProfileView from "../views/ProfileView.vue"
import ManagersSportObject from "../components/manager/ManagersSportObject.vue"
import BuyerCheckInView from "../components/buyer/views/BuyerCheckInView.vue"

let roleBeforeEnter = (role, redirectRoute = "/") => {
  return (to, from) => {
    let profileStore = useProfileStore()
    if(!profileStore.getLoggedIn || profileStore.getLoggedProfile.role !== role)
      return { route: redirectRoute }
    return true
  }
}

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
      beforeEnter: roleBeforeEnter("ADMIN")
    },
    {
      path: '/manager/object',
      name: 'manager-object',
      component: ManagersSportObject,
      beforeEnter: roleBeforeEnter("MANAGER")
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
    {
      path: '/training/checkIn',
      name: 'buyer-checkIn',
      component: BuyerCheckInView
    }
  ]
})

router.beforeEach(async (to, from, next) => {
  let profileStore = useProfileStore()
  await profileStore.tryAlreadyLoggedIn();
  next()
})




export default router
