<script setup>
  import BuyerProfileView from "../components/buyer/views/BuyerProfileView.vue"
  import CoachProfileView from "../components/coach/views/CoachProfileView.vue"
</script>
    
<template>
    <BuyerProfileView v-if="profile.role === 'BUYER'"/>
    <CoachProfileView v-if="profile.role === 'COACH'"/>

</template>

<script>
  import {useProfileStore} from "@/stores/profile-store.js"
  import { mapState } from 'pinia'


  export default {
      name: "ProfileView",
      computed: {
        ...mapState(useProfileStore, ['profile'])
      }
  }
  </script>

<style scoped>


</style>