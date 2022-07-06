<template>
    <div class="reservations-wrapper">
        <h1>Reservations</h1>
        <div class="reservations">
            <TrainingReservation :customStyle="reservationStyle" v-for="(res, index) in reservations" :key="index"
                :training="res" @overlay-pressed="cancelTraining" />
        </div>
    </div>
</template>

<script>
import TrainingReservation from './TrainingReservation.vue'
import { useTrainingStore } from '../../stores/training-store'
import { useToast } from 'vue-toast-notification';

export default {
    name: "CoachTrainingReservations",
    components: {
        TrainingReservation
    },
    created: async function () {
        this.trainingStore = useTrainingStore();
        this.reservations = await this.trainingStore.getCoachTrainingReservations();
        this.toast = useToast()
    },
    data: function () {
        return {
            reservations: [],
            trainingStore: null,
            toast: null
        }
    },
    computed: {
        reservationStyle: function () {
            return {
                minWidth: "30rem",
                maxWidth: "35rem",
                minHeight: "3rem"
            }
        }
    },
    methods: {
        cancelTraining: async function (id) {
            let resp = await this.trainingStore.cancelTraining(id)
            if (resp.error) {
                this.toast.error(resp.error)
                return;
            }

            let instance = this.toast.success("Successfully canceled training!")
            this.reservations = await this.trainingStore.getCoachTrainingReservations()
        }
    }
}

</script>


<style scoped>
.reservations-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 2rem;
    outline: 1px solid lightgray;
}


.reservations {
    display: flex;
    flex-wrap: wrap;
    align-items: center;
    justify-content: space-evenly;
    min-height: 3rem;
    padding: 1rem;
    gap: 1rem;
}
</style>