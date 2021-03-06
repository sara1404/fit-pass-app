import { createRouter, createWebHistory } from 'vue-router'
import { useProfileStore } from "../stores/profile-store.js";
import HomeView from '../views/HomeView.vue'
import Objects from '../components/objects/SportObjects.vue'
import AdminProfilesView from "../components/admin/views/AdminProfilesView.vue";
import AdminCommentsView from "../components/admin/views/AdminCommentsView.vue";
import SportObjectView from "../views/SportObjectView.vue"
import ProfileView from "../views/ProfileView.vue"
import ManagersSportObject from "../components/manager/ManagersSportObject.vue"
import BuyerCheckInView from "../components/buyer/views/BuyerCheckInView.vue"
import PlansView from "../views/PlansView.vue"
import SubscriptionsView from "../components/buyer/views/SubscriptionsView.vue"

let roleBeforeEnter = (role, redirectRoute = "/") => {
  return (to, from) => {
    let profileStore = useProfileStore()
    if (!profileStore.getLoggedIn || profileStore.getLoggedProfile.role !== role)
      return { route: redirectRoute }
    return true
  }
}


let managerObjectBeforeEnter = (role, redirectRoute = "/") => {
  return (to, from) => {
    let profileStore = useProfileStore()
    if (!profileStore.getLoggedIn || profileStore.getLoggedProfile.role !== role || !profileStore.getLoggedProfile.sportObject)
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
      path: '/plans',
      name: 'plans',
      component: PlansView
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
      path: '/admin/comments',
      name: "admin-comments",
      component: AdminCommentsView,
      beforeEnter: roleBeforeEnter("ADMIN")
    },
    {
      path: '/manager/object',
      name: 'manager-object',
      component: ManagersSportObject,
      beforeEnter: managerObjectBeforeEnter("MANAGER")
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
    },
    {
      path: '/subscriptions',
      name: 'subscription',
      component: SubscriptionsView
    }
  ]
})

router.beforeEach(async (to, from, next) => {
  let profileStore = useProfileStore()
  await profileStore.tryAlreadyLoggedIn();
  next()
})




export default router
