<script setup>
import {mapState} from "pinia"
import { useProfileStore } from "@/stores/profile-store.js"
</script>

<template>
  <div class="auth-menu">
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
  mounted: function(){
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  }
}

</script>

<style scoped>
  .auth-menu {
    display: flex;
    gap: 10px;
    padding-right: 20px;
  }

  .auth-menu button {
    width: 80px;
    height: 43px;
    background: green;
    color: white;
    outline: none;
    border: none;
    border-radius: 10px;
    padding: 5px;
    cursor: pointer;
    font-size: 15px;
  }

  .auth-menu button:hover{
    transform: scale(1.05);
    transition-duration: 0.5s;
  }

</style>