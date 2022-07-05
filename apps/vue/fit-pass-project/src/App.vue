<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import TheMenu from "@/components/menu/TheMenu.vue";
import LoginForm from '@/components/forms/LoginForm.vue';
import RegisterForm from '@/components/forms/RegisterForm.vue';
import AddPromoCodeForm from '@/components/forms/AddPromoCodeForm.vue'
import BuySubscriptionForm from '@/components/forms/BuySubscriptionForm.vue'
import UserProfileHeader from '@/components/menu/UserProfileHeader.vue'
import AddSportObjectForm from '@/components/forms/AddSportObjectForm.vue'
import Footer from '@/components/footer/Footer.vue'
</script>

<template>
  <TheMenu @displayLogin="displayLogin" @displayRegister="displayRegister" @displayPromoCodeForm="displayPromoCode" @displaySubscriptionForm="displaySubscription" @displayAddObjectForm="displayAddObjectForm"/>
  <RouterView />
  <LoginForm v-show="isLoginDisplayed && !loggedIn" @closeLoginForm="isLoginDisplayed=false;"/>
  <RegisterForm v-show="isRegisterDisplayed" @closeRegisterForm="isRegisterDisplayed=false;"/>
  <AddSportObjectForm v-show="isAddSportObjectDisplayed" @closeAddObjectForm="isAddSportObjectDisplayed=false;"/>
  <AddPromoCodeForm v-show="isPromoCodeDisplayed" @closePromoCodeForm="isPromoCodeDisplayed=false;"/>
  <BuySubscriptionForm v-show="isBuySubscriptionDisplayed" @closeBuySubscriptionForm="isBuySubscriptionDisplayed=false;"/>
  <!-- <Footer/> -->
</template>

<script>
export default {
  data(){
    return{
      isLoginDisplayed : false,
      isRegisterDisplayed : false,
      isAddSportObjectDisplayed: false,
      isPromoCodeDisplayed: false,
      isBuySubscriptionDisplayed: false,
      profileStore: null,
    }
  },
  methods:{
    displayLogin : function(){
      this.isLoginDisplayed = true
      this.isRegisterDisplayed = false;
      this.isPromoCodeDisplayed = false
    },
    displayRegister : function(){
      this.isRegisterDisplayed = true
      this.isLoginDisplayed = false;
      this.isPromoCodeDisplayed = false
    },
    displayPromoCode: function(){
      this.isPromoCodeDisplayed = true
      this.isRegisterDisplayed = false
      this.isLoginDisplayed = false
    },
    displaySubscription: function(){
      this.isBuySubscriptionDisplayed = true
    },
    displayAddObjectForm: function(){
      this.isAddSportObjectDisplayed = true
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
