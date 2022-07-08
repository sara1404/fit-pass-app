<template>
    <ReservationForm :emitName="'BuyerReservation'" @closeBuyerReservationForm="$emit('closeBuyerReservationForm')"
        :submitReservation="submitReservation" :captureContent="captureContent" />
</template>


<script>
import { sportObjectsStore } from '../../stores/objects-store'
import ReservationForm from './ReservationForm.vue'

export default {
    name: "BuyerReservationForm",
    components: {
        ReservationForm
    },
    created: function () {
        this.objectsStore = sportObjectsStore()
    },
    data: function () {
        return {
            objectsStore: null,
            captureContent: async (objectId) => { return this.objectsStore.getAllPersonalTrainingContent(objectId) },
            submitReservation: async (content) => { return this.objectsStore.reservePersonalTraining(content) }
        }
    }
}
</script>