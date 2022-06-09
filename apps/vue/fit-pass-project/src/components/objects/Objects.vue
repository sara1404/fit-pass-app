<script setup>
import SportObject from "@/components/objects/SportObject.vue";
import {defineComponent} from "vue";
defineComponent(Object)
</script>

<template>
    <div class="objects-container">
        <SportObject  v-for="obj in objects" v-bind:key="obj.name" :objectForChild="obj"/>
    </div>
</template>

<script>
export default {
  name: "Objects",
  data: function(){
      return{
          objects:[
              {
                name:"sara"
              }
          ],
         
      }
  }
}
</script>

<style>
@import "@/assets/base.css";
.objects-container{
    width: 100%;
    height: 100%;
}
</style>