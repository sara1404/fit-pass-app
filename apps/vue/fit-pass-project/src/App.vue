<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import TheMenu from "@/components/menu/TheMenu.vue";
import LoginForm from '@/components/forms/LoginForm.vue';
import RegisterForm from '@/components/forms/RegisterForm.vue';
import UserProfileHeader from '@/components/menu/UserProfileHeader.vue'
import Footer from '@/components/footer/Footer.vue'
</script>

<template>
  <TheMenu @displayLogin="displayLogin" @displayRegister="displayRegister"/>
  <RouterView />
  <LoginForm v-show="isLoginDisplayed && !loggedIn" @closeLoginForm="isLoginDisplayed=false;"/>
  <RegisterForm v-show="isRegisterDisplayed" @closeRegisterForm="isRegisterDisplayed=false;"/>
  <!-- <Footer/> -->
</template>

<script>
export default {
  data(){
    return{
      isLoginDisplayed : false,
      isRegisterDisplayed : false,
      profileStore: null,
    }
  },
  methods:{
    displayLogin : function(){
      this.isLoginDisplayed = true
      this.isRegisterDisplayed = false;
    },
    displayRegister : function(){
      this.isRegisterDisplayed = true
      this.isLoginDisplayed = false;
    }
  },
  mounted: function() {
  },
  computed: {
    ...mapState(useProfileStore, ['loggedIn'])
  }
}
</script>

<style>
#app{
  display: flex;
  flex-direction: column;
  min-height: 100%;
}
</style>
