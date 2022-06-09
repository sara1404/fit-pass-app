<script setup>
import AuthMenu from "@/components/menu/AuthMenu.vue";
import UserProfileHeader from "@/components/menu/UserProfileHeader.vue";
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import {defineComponent} from "vue";

defineComponent(AuthMenu)
</script>

<template>
    <nav>
      <div class="logo-wrapper">
        <img src="../../assets/imgs/logo.png" height="20px" width="40px">
        <ul class="menu-items">
          <li class="menu-item"><router-link to="/">Home</router-link></li>
          <li class="menu-item"><router-link to="/plans">Plans</router-link></li>
          <li class="menu-item"><router-link to="/objects">Objects</router-link></li>
          <li class="menu-item"><router-link to="/about">About us</router-link></li>
        </ul>
      </div>
      <AuthMenu @displayLogin="$emit('displayLogin')" @displayRegister="$emit('displayRegister')" v-show="!loggedIn"/>
      <UserProfileHeader v-show="loggedIn"/>
    </nav>
</template>

<script>
export default {
  name: "TheMenu",
  computed:{
      ...mapState(useProfileStore, ['loggedIn'])
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
    width: 100%;
    position: sticky;
    top: 0;
    left: 0;
    border-bottom: 1px solid lightgray;
  }

  nav img{
    height: 65px;
    width: 80px;
    padding-left: 10px;
    padding-top: 4px;
  }

  .logo-wrapper{
    display: flex;
    gap: 20px;
  }

  .menu-items {
    display: flex;
    gap: 10px;
    padding: 0;
  }

  .menu-item {
    list-style: none;
    font-size: 1.5rem;
    font-weight: normal;
    margin: 5px;

  }

  .menu-item a {
    text-decoration: none;
    color: #4b4545;
  }

  .menu-item a:hover{
    text-decoration: underline;
    text-decoration-color: #4b4545;
  }
</style>