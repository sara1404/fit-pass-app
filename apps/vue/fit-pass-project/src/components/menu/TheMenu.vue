<script setup>
import AuthMenu from "@/components/menu/AuthMenu.vue";
import UserProfileHeader from "@/components/menu/UserProfileHeader.vue";
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState } from "pinia"
import { defineComponent } from "vue";

</script>

<template>
  <nav>
    <div class="logo-wrapper">
      <img src="../../assets/imgs/logo.png" height="20px" width="40px">
      <ul class="menu-items">
        <li class="menu-item">
          <router-link to="/">Home</router-link>
        </li>
        <li class="menu-item">
          <router-link to="/plans">Plans</router-link>
        </li>
        <li class="menu-item">
          <router-link to="/objects">Objects</router-link>
        </li>
        <li class="menu-item">
          <router-link to="/about">About us</router-link>
        </li>
        <li class="menu-item" v-show="profile?.role === 'ADMIN'">
          <router-link to="/admin/profiles">Profiles</router-link>
        </li>
        <li class="menu-item" v-show="profile?.role === 'ADMIN'">
          <router-link to="/admin/comments">Comments</router-link>
        </li>
        <li class="menu-item" v-show="profile?.role === 'MANAGER'">
          <router-link to="/manager/object">My sport object</router-link>
        </li>
        <li class="menu-item" v-show="profile?.role === 'BUYER'">
          <router-link to="/training/checkIn">Check In</router-link>
        </li>
        <li class="menu-item" v-show="profile?.role === 'BUYER'">
          <router-link to="/subscriptions">Subscriptions</router-link>
        </li>
      </ul>
    </div>
    <AuthMenu @displayLogin="$emit('displayLogin')" @displayRegister="$emit('displayRegister')" v-show="!loggedIn" />
    <UserProfileHeader v-show="loggedIn" @displayPromoCodeForm="$emit('displayPromoCodeForm')"
      @displaySubscriptionForm="$emit('displaySubscriptionForm')" @displayAddObjectForm="$emit('displayAddObjectForm')"
      @displayExtrasForm="$emit('displayExtrasForm')"
      @displayCoachReservationForm="$emit('displayCoachReservationForm')"
      @displayBuyerReservationForm="$emit('displayBuyerReservationForm')" />
  </nav>
</template>

<script>
export default {
  name: "TheMenu",
  computed: {
    ...mapState(useProfileStore, ['loggedIn', 'profile'])
  },
  data: function () {
    return {
      profileStore: null
    }
  },
  mounted: function () {
    this.profileStore = useProfileStore();
    this.profileStore.tryAlreadyLoggedIn();
  }
}
</script>

<style scoped>
@import "@/assets/base.css";

nav {
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 8vh;
  max-width: 100vw;
  position: sticky;
  top: 0;
  left: 0;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  background-color: #fff;
  z-index: 10000000;
}

nav img {
  height: 90%;
  width: 8%;
  padding-left: 10px;
  padding-top: 4px;
}

.logo-wrapper {
  display: flex;
  gap: 40px;
  height: 100%;
  width: 60%;
}

.menu-items {
  display: flex;
  align-items: center;
  gap: 40px;
  padding: 0;
}

.menu-item {
  list-style: none;
  font-size: 1.5rem;
  font-weight: normal;
}

.menu-item a {
  text-decoration: none;
  color: #4b4545;
}

.menu-item a:hover {
  text-decoration: underline;
  text-decoration-color: #4b4545;
}
</style>