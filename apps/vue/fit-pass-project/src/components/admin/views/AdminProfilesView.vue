<script setup>
import UserProfile from "@/components/admin/profiles/UserProfile.vue";
import NewProfileForm from "@/components/admin/profiles/NewProfileForm.vue";
import UserFilter from "../../filters/UserFilter.vue";
</script>
<template>
  <UserFilter />
  <div class="profiles-wrapper">
    <UserProfile v-for="user in profiles" :key="user.username" :addProfile="false" :profile="user" :showOverlay="true"
      :btnOverlayText="'Delete'" @overlay-pressed="deleteProfile(user.username)"></UserProfile>
    <UserProfile :addProfile="true" :user="{}" @registerProfile="registerNewProfile"></UserProfile>
    <NewProfileForm v-show="registerProfile" @closeRegisterForm="registerProfile = false"
      @user-registered="userRegistered"></NewProfileForm>
  </div>

</template>

<script>

import { useProfileStore } from "../../../stores/profile-store.js";
import { useToast } from "vue-toast-notification";
import { mapState } from "pinia";

export default {
  name: "AdminProfilesView",
  components: {},

  data: function () {
    return {
      profileStore: null,
      registerProfile: false,
      toast: null
    }
  },

  computed: {
    ...mapState(useProfileStore, ['profiles'])
  },
  methods: {
    registerNewProfile: function () {
      this.registerProfile = true
    },
    userRegistered: async function () {
      await this.profileStore.captureAllProfiles();
    },
    deleteProfile: async function (username) {
      let resp = await this.profileStore.deleteByUsername(username);
      if (resp.error) {
        this.toast.error(resp.error)
        return
      }
      this.toast.success("Sucessfully deleted user " + username);
      await this.profileStore.captureAllProfiles();
    }
  },
  mounted: async function () {
    this.profileStore = useProfileStore()
    this.toast = useToast()
    await this.profileStore.captureAllProfiles()
  },


}
</script>

<style scoped>
.profiles-wrapper {
  max-width: 100%;
  height: 100%;
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  margin: 10px;
}
</style>