<script setup>
import SportObjectContent from "@/components/objects/SportObjectContent.vue"
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import {defineComponent} from "vue";

defineComponent(SportObjectContent)
</script>

<template>
    <div class="container">
        <div class="title">{{profile.sportObject.name}} - {{profile.sportObject.location.address.street}} {{profile.sportObject.location.address.number}}</div>
        <div class="content-wrapper">
            <SportObjectContent v-for="content in profile.sportObject.content" :key="content.name" :contentData = "content"/>
        </div>
    </div>
</template>


<script>
 export default {
  name: "ManagersSportObject",
  data: function() {
    return {
    }
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  },
}
</script>

<style scoped>
@import "@/assets/base.css";
.container{
    padding: 1rem;
}

.content-wrapper{
    display: flex;
    gap: 1.5rem;
    margin-top: 1rem;
}

.title{
    font-size: 1.5rem;
}
</style>