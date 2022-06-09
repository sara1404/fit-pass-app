<script setup>
import { RouterLink, RouterView } from 'vue-router'
import TheMenu from "@/components/menu/TheMenu.vue";
import LoginForm from '@/components/forms/LoginForm.vue';
import RegisterForm from '@/components/forms/RegisterForm.vue';

</script>

<template>
  <TheMenu @displayLogin="displayLogin" @displayRegister="displayRegister"/>
  <LoginForm v-show="isLoginDisplayed" @closeLoginForm="isLoginDisplayed=false;"/>
  <RegisterForm v-show="isRegisterDisplayed" @closeRegisterForm="isRegisterDisplayed=false;"/>
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
  }
}
</script>

<style>
</style>
