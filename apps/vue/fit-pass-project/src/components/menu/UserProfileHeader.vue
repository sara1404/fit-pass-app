<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState} from "pinia"
</script>

<template>
  <div class="profile-username" @click="displayProfile">
      {{profile.username}}
  </div>
</template>

<script>
export default {
  name: "UserProfileHeader",
  data: function(){
      return{
      }
  },
  mounted: function() {
    this.profileStore = useProfileStore()
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  },
  methods:{
    displayProfile: function(){
      this.$router.push({path: "/users/me"})
    },
  }
}

</script>

<style scoped>
  .profile-username {
    display: flex;
    gap: 10px;
    padding: 10px;
    border: 1px solid #ff7810;
    border-radius: 5px;
    font-size: 20px;
    margin-right: 20px;
    cursor: pointer;
  }
</style>