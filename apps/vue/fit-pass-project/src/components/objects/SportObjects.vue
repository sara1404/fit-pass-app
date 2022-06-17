<script setup>
import SportObject from "@/components/objects/SportObject.vue";
import Filter from "@/components/filters/Filter.vue"
import {defineComponent} from "vue";
import { sportObjectsStore } from "@/stores/objects-store.js"
import { mapState } from 'pinia';
defineComponent(Object)
</script>

<template> 
    <div class="container">
        <Filter/>
        <div class="objects-container">
            <SportObject  v-for="obj in this.sportObjects" v-bind:key="obj.name" :sportObjectChild="obj"/>
        </div>
    </div>
</template>

<script>
export default {
  name: "Objects",
  data: function(){
      return{
          sportObjectsStore : null,
      }
  },
  computed: {
        ...mapState(sportObjectsStore, ['sportObjects'])
  },
   mounted: function() {
     this.sportObjectsStore = sportObjectsStore()
     this.sportObjectsStore.getSportObjects();
  },
}
</script>

<style scoped>
@import "@/assets/base.css";
.objects-container{
    width: 100%;
    height: 100%;
    display: flex;
    gap: 15px;
    padding: 1em;
}
</style>