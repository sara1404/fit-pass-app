<script setup>
import Training from "./Training.vue";

</script>
<template>
  <div class="training-history">
    <Training v-for="(training, index) in buyerTrainings" :training="training" :key="index" />
  </div>
</template>

<script>
import axios from "axios"
import { useTrainingStore } from "@/stores/training-store"
import { mapState } from "pinia/dist/pinia.mjs";

export default {
  name: "TrainingHistory",
  created: async function () {
    this.trainingStore = useTrainingStore()
    await this.trainingStore.filterBuyerTrainings({})
  },
  data: function () {
    return {
      history: [],
      trainingStore: null
    }
  },
  computed: {
    ...mapState(useTrainingStore, ['buyerTrainings'])
  }
}
</script>

<style scoped>
.training-history {
  display: flex;
  flex-wrap: wrap;
  gap: 1rem;
  margin-top: 2rem;
  min-height: 300px;
  width: 100%
}
</style>