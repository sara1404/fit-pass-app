<script setup>
import ProfileEditing from "../../custom/ProfileEditing.vue";
import CoachTrainingReservations from "../CoachTrainingReservations.vue";
import CoachTrainingHistory from "../CoachTrainingHistory.vue";
import CoachTrainingFilter from "../CoachTrainingFilter.vue";
</script>


<template>
  <CoachTrainingFilter />
  <div class="profile-wrapper">
    <div class="profile-and-history-wrapper">
      <ProfileEditing style="width: 100%; height: 90%;" />
      <CoachTrainingHistory />
    </div>
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

.profile-wrapper {
  max-width: 100%;
  min-height: 100%;
  height: 100%;
  padding: 1.5em;
}

.profile-and-history-wrapper {
  height: 70vh;
  width: 100%;
  display: flex;
  gap: 1rem;
  flex: 1;
}
</style>