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
                name:"synergy",
                type:"gym",
                content:"personal trainings",
                status:"open",
                location:"kornelija stankovica 22",
                logo:"logo.png",
                averageGrade:"9.3",
                workingTime:"radimo uvek brt"
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