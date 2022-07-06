<template>

    <div class="reservation-wrapper">
        <TrainingReservation v-for="reservation in reservations" :training="reservation" :overlay-btn-text="'Check In'"
            @overlay-pressed="checkIn" />
    </div>

</template>



<script>
import { useTrainingStore } from '../../stores/training-store'
import TrainingReservation from '../coach/TrainingReservation.vue'

export default {
    name: "CheckInReservations",
    components: {
        TrainingReservation
    },
    created: async function () {
        this.trainingStore = useTrainingStore()
        this.reservations = await this.trainingStore.getBuyerReservations()

    },
    data: function () {
        return {
            reservations: [],
            trainingStore: null
        }
    },
    methods: {
        checkIn: function (val) {
            console.log(val)
        }
    }

}
</script>


<style scoped>
.reservation-wrapper {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: flex-start;
    gap: 1rem;
    padding: 1rem;

}
</style>