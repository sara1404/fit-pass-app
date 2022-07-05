<script setup>
import TrainingHistory from "../TrainingHistory.vue";
import ProfileEditing from "../../custom/ProfileEditing.vue";
import BuyerTrainingFilter from "../BuyerTrainingFilter.vue";
</script>
<template>
  <BuyerTrainingFilter/>
  <div class="profile-wrapper">
    <ProfileEditing/>
    <TrainingHistory/>
  </div>
</template>

<script>
export default {
  name: "BuyerProfileView"
}
</script>

<style scoped>
  @import "@/assets/base.css";
  .profile-wrapper{
    max-width: 100%;
    min-height: 100%;
    height: 100%;
    padding: 1.5em;
  }
</style>