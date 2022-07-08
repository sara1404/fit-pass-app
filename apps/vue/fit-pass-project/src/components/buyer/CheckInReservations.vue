<template>
    <div class="reservation-wrapper-outer">
        <h2>Check in for training reservation</h2>
        <div class="reservation-wrapper">
            <TrainingReservation v-for="reservation in reservations" :training="reservation"
                :overlay-btn-text="'Check In'" @overlay-pressed="checkIn(reservation)" />
        </div>
        <h2>Check in for regular content</h2>
        <div class="reservation-wrapper">
            <TrainingReservation :training="regularContentReservation" @overlay-pressed="checkInForRegularContent"
                :overlay-btn-text="'Check In'" />
        </div>
    </div>

</template>

<script>
import { useTrainingStore } from '../../stores/training-store'
import { useToast } from 'vue-toast-notification'
import TrainingReservation from '../coach/TrainingReservation.vue'

export default {
    name: "CheckInReservations",
    components: {
        TrainingReservation
    },
    created: async function () {
        this.trainingStore = useTrainingStore()
        this.reservations = await this.trainingStore.getBuyerReservations()
        this.toast = useToast()
    },
    data: function () {
        return {
            reservations: [],
            trainingStore: null,
            toast: null,
            regularContentReservation: {
                application: "Work Time",
                reservation: {
                    type: "OTHER",
                    contentName: "REGULAR_CONTENT",
                },
                sportObject: {
                    name: "All included",
                    type: "All included"
                },
                coachUsername: "None"
            }
        }
    },
    methods: {
        checkIn: async function (val) {
            let resp = await this.trainingStore.checkInTraining(val)
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }
            this.toast.success("Succesfully checked in reservation!");
            this.reservations = await this.trainingStore.getBuyerReservations()
        },
        checkInForRegularContent: async function () {
            console.log("checking in")
            let resp = await this.trainingStore.checkInForRegularTraining()
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }
            this.toast.success("Successfully checked in for regular training")
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

.reservation-wrapper-outer {
    display: flex;
    flex-direction: column;
    padding: 1rem;
}
</style>