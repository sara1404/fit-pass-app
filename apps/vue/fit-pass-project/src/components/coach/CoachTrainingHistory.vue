<template>
    <div class="coach-history-wrapper">
        <Training v-for="(hist, index) in history" :key="index" :training="hist" />
    </div>
</template>


<script>

import { useTrainingStore } from '../../stores/training-store'
import Training from '../buyer/Training.vue'

export default {
    name: "CoachTrainingHistory",
    components: {
        Training
    },
    created: async function () {
        this.trainingStore = useTrainingStore()
        let resp = await this.trainingStore.getCoachTrainingHistory()
        this.history = resp.data
    },
    data: function () {
        return {
            history: [],
            trainingStore: null
        }
    }
}

</script>


<style scoped>
.coach-history-wrapper {
    display: flex;
    flex-direction: column;
    width: 100%;
    height: 90%;
    outline: 1px solid lightgray;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
    border-radius: 15px;
    overflow-y: scroll;
    padding: 1.5rem;
}
</style>