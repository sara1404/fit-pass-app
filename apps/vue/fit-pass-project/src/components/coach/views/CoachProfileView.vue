<script setup>
import ProfileEditing from "../../custom/ProfileEditing.vue";
import CoachTrainingReservations from "../CoachTrainingReservations.vue";
</script>
<template>
  <BuyerTrainingFilter/>
  <div class="profile-wrapper">
    <ProfileEditing/>
    <CoachTrainingReservations></CoachTrainingReservations>
  </div>
</template>

<script>
export default {
  name: "CoachProfileView"
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