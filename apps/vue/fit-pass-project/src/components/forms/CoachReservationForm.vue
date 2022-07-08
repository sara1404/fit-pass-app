<template>
    <ReservationForm :emitName="'CoachReservation'" @closeCoachReservationForm="$emit('closeCoachReservationForm')"
        :submitReservation="submitReservation" :captureContent="captureContent" />
</template>


<script>
import { sportObjectsStore } from '../../stores/objects-store'
import ReservationForm from './ReservationForm.vue'

export default {
    name: "CoachReservationForm",
    components: {
        ReservationForm
    },
    created: function () {
        this.objectsStore = sportObjectsStore()
    },
    data: function () {
        return {
            objectsStore: null,
            captureContent: async (objectId) => { return this.objectsStore.getAllGroupTrainingContent(objectId) },
            submitReservation: async (content) => { return this.objectsStore.reserveGroupTraining(content) }
        }
    }
}
</script>