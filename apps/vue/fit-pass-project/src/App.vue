<script setup>
import { RouterLink, RouterView } from 'vue-router'
import TheMenu from "@/components/menu/TheMenu.vue";
import LoginForm from '@/components/forms/LoginForm.vue'

</script>

<template>
  <TheMenu @displayLogin="display"/>
  <RouterView />
</template>

<script>
export default {
  data(){
    return{
      displayLogin : false,
    }
  },
  methods:{
    display : function(){
      this.displayLogin = true
      console.log("okinuo")
    }
  }
}
</script>

<style>
</style>
