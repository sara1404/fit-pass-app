<script setup>
import { RouterLink, RouterView } from 'vue-router'
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState } from "pinia"
import TheMenu from "@/components/menu/TheMenu.vue";
import LoginForm from '@/components/forms/LoginForm.vue';
import RegisterForm from '@/components/forms/RegisterForm.vue';
import AddPromoCodeForm from '@/components/forms/AddPromoCodeForm.vue'
import BuySubscriptionForm from '@/components/forms/BuySubscriptionForm.vue'
import ExtrasForm from './components/forms/ExtrasForm.vue';
import CoachReservationForm from './components/forms/CoachReservationForm.vue';
import BuyerReservationForm from './components/forms/BuyerReservationForm.vue';
import UserProfileHeader from '@/components/menu/UserProfileHeader.vue'
import AddSportObjectForm from '@/components/forms/AddSportObjectForm.vue'
import Footer from '@/components/footer/Footer.vue'
</script>

<template>
  <TheMenu @displayLogin="displayLogin" @displayRegister="displayRegister" @displayPromoCodeForm="displayPromoCode"
    @displaySubscriptionForm="displaySubscription" @displayAddObjectForm="displayAddObjectForm"
    @displayExtrasForm="displayExtrasForm" @displayCoachReservationForm="displayCoachReservationForm"
    @displayBuyerReservationForm="displayBuyerReservationForm" />
  <RouterView />
  <LoginForm v-show="isLoginDisplayed && !loggedIn" @closeLoginForm="isLoginDisplayed = false;" />
  <RegisterForm v-show="isRegisterDisplayed" @closeRegisterForm="isRegisterDisplayed = false;" />
  <AddSportObjectForm v-show="isAddSportObjectDisplayed" @closeAddObjectForm="isAddSportObjectDisplayed = false;" />
  <AddPromoCodeForm v-show="isPromoCodeDisplayed" @closePromoCodeForm="isPromoCodeDisplayed = false;" />
  <BuySubscriptionForm v-show="isBuySubscriptionDisplayed"
    @closeBuySubscriptionForm="isBuySubscriptionDisplayed = false;" />
  <ExtrasForm v-show="isExtrasDisplayed" @closeExtrasForm="isExtrasDisplayed = false;" />
  <CoachReservationForm v-show="isCoachReservationDisplayed"
    @closeCoachReservationForm="isCoachReservationDisplayed = false" />
  <BuyerReservationForm v-show="isBuyerReservationDisplayed"
    @closeBuyerReservationForm="isBuyerReservationDisplayed = false" />
  <!-- <Footer /> -->
</template>

<script>
export default {
  data() {
    return {
      isLoginDisplayed: false,
      isRegisterDisplayed: false,
      isAddSportObjectDisplayed: false,
      isPromoCodeDisplayed: false,
      isBuySubscriptionDisplayed: false,
      isExtrasDisplayed: false,
      isCoachReservationDisplayed: false,
      isBuyerReservationDisplayed: false,
      profileStore: null,
    }
  },
  methods: {
    displayLogin: function () {
      this.isLoginDisplayed = true
      this.isRegisterDisplayed = false;
      this.isPromoCodeDisplayed = false
    },
    displayRegister: function () {
      this.isRegisterDisplayed = true
      this.isLoginDisplayed = false;
      this.isPromoCodeDisplayed = false
    },
    displayPromoCode: function () {
      this.isPromoCodeDisplayed = true
      this.isRegisterDisplayed = false
      this.isLoginDisplayed = false
    },
    displaySubscription: function () {
      this.isBuySubscriptionDisplayed = true
    },
    displayAddObjectForm: function () {
      this.isAddSportObjectDisplayed = true
    },
    displayExtrasForm: function () {
      this.isExtrasDisplayed = true;
      this.isLoginDisplayed = false;
      this.isRegisterDisplayed = false;
      this.isPromoCodeDisplayed = false;
      this.isBuySubscriptionDisplayed = false;
      this.isCoachReservationDisplayed = false;
    },
    displayCoachReservationForm: function () {
      this.isCoachReservationDisplayed = true;
      this.isExtrasDisplayed = false;
      this.isLoginDisplayed = false;
      this.isRegisterDisplayed = false;
      this.isPromoCodeDisplayed = false;
      this.isBuySubscriptionDisplayed = false;
    },
    displayBuyerReservationForm: function () {
      console.log("display buyer form")
      this.isBuyerReservationDisplayed = true;
      this.isCoachReservationDisplayed = false;
      this.isExtrasDisplayed = false;
      this.isLoginDisplayed = false;
      this.isRegisterDisplayed = false;
      this.isPromoCodeDisplayed = false;
      this.isBuySubscriptionDisplayed = false;
    }


  },
  mounted: function () {
  },
  computed: {
    ...mapState(useProfileStore, ['loggedIn'])
  }
}
</script>

<style>
#app {
  display: flex;
  flex-direction: column;
  min-height: 100%;
}
</style>
