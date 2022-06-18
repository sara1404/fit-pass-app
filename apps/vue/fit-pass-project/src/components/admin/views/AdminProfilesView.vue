<script setup>
import UserProfile from "@/components/admin/profiles/UserProfile.vue";
</script>
<template>
  <div class="profiles-wrapper">
    <UserProfile v-for="user in profiles" :key="user.username" :addProfile="false" :profile="user"></UserProfile>
    <UserProfile :addProfile="true" :user="{}"></UserProfile>
  </div>
</template>

<script>

import {useProfileStore} from "../../../stores/profile-store.js";
import {mapState} from "pinia";

export default {
  name: "AdminProfilesView",
  components: {},

  data: function() {
    return {
      profileStore: null,
    }
  },

  computed: {
      ...mapState(useProfileStore, ['profiles'])
  },
  mounted: async function() {
    this.profileStore = useProfileStore()
    await this.profileStore.captureAllProfiles()
  },


}
</script>

<style scoped>
  .profiles-wrapper {
    width: 100%;
    height: 100%;
    display: flex;
    flex-wrap: wrap;
    gap: 1rem;
    margin: 10px;
  }
</style>