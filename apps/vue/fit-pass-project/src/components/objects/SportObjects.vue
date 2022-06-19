<script setup>
import SportObject from "@/components/objects/SportObject.vue";
import Filter from "@/components/filters/Filter.vue"
import SportObjectsMap from "@/components/objects/SportObjectsMap.vue";
</script>

<template> 
    <div class="objects-view-container">
        <Filter/>
      <div class="map-and-objects-wrapper">
        <div class="objects-container">
          <SportObject  v-for="obj in this.sportObjects" v-bind:key="obj.name" :sportObjectChild="obj"/>
        </div>
        <div class="map-container">
          <SportObjectsMap :sportObjects="sportObjects"></SportObjectsMap>
        </div>
      </div>
    </div>
</template>

<script>
import { sportObjectsStore } from "@/stores/objects-store.js"
import { mapState } from 'pinia';

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
   mounted: async function() {
     this.sportObjectsStore = sportObjectsStore()
     await this.sportObjectsStore.getSportObjects();
  },
}
</script>

<style scoped>
@import "@/assets/base.css";

.map-and-objects-wrapper {
  display: flex;
  height: 81vh;
  width: 100%;
}

.objects-container{
    height: 100%;
    width: 60%;
    display: flex;
    justify-content: space-around;
    gap: 15px;
    padding: 1em;
    flex: 1;
    flex-wrap: wrap;
    overflow-y: scroll;
    overflow-x: hidden;
}

.map-container {
  width: 40%;
  min-width: 40%;
  min-height: 100%;
  max-width: 40%;
  flex: 1;
}

.object-wrapper {
  height: 30rem;
}
</style>