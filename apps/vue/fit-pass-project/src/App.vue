<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import TheMenu from "@/components/menu/TheMenu.vue";
import LoginForm from '@/components/forms/LoginForm.vue';
import RegisterForm from '@/components/forms/RegisterForm.vue';
import UserProfileHeader from '@/components/menu/UserProfileHeader.vue'




</script>

<template>
  <TheMenu @displayLogin="displayLogin" @displayRegister="displayRegister"/>
  <LoginForm v-show="isLoginDisplayed" @closeLoginForm="isLoginDisplayed=false;"/>
  <RegisterForm v-show="isRegisterDisplayed" @closeRegisterForm="isRegisterDisplayed=false;"/>
  <UserProfileHeader v-show="profile.loggedIn"/>
  <RouterView />
</template>

<script>
export default {
  data(){
    return{
      isLoginDisplayed : false,
      isRegisterDisplayed : false,
    }
  },
  methods:{
    displayLogin : function(){
      this.isLoginDisplayed = true
    },
    displayRegister : function(){
      this.isRegisterDisplayed = true
    }
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  }
}
</script>

<style>
</style>
