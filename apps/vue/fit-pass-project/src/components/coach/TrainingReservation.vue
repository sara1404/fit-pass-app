<template>
    <div class="training-reservation" :style="customStyle">
        <div class="data-wrapper">
            <div class="training-reserved-at">
                <span>Reservation: </span>
                {{ training.reservation.reservedAt }}
            </div>
            <div class="training-name">
                <span>Content Name: </span>
                {{ training.reservation.contentName }}
            </div>
            <div class="training-type">
                <span>Content Type: </span>
                {{ training.reservation.type }}
            </div>
            <div class="object-name">
                <span>Object Name: </span>
                {{ training.sportObject.name }}
            </div>
            <div class="object-type">
                <span>Object Type: </span>
                {{ training.sportObject.type }}
            </div>
            <div class="training-canceled">
                <span>Training canceled: </span>
                {{ isCanceled }}
            </div>
        </div>

        <div class="reservation-overlay">
            <button @click="$emit('overlay-pressed', training.reservation.id)">
                {{ overlayBtnText }}
            </button>
        </div>
    </div>

</template>


<script>
export default {
    name: "TrainingReservation",
    props: {
        training: {},
        customStyle: {},
        overlayBtnText: {
            type: String,
            default: "Cancel"
        }
    },
    data: function () {
        return {
        }
    },
    computed: {
        isCanceled: function () {
            return this.training.reservation.isCanceled ? "YES" : "NO"
        }
    }
}
</script>



<style scoped>
.training-reservation {
    display: flex;
    align-items: center;
    justify-content: center;
    position: relative;
    outline: 1px solid lightgray;
    padding: 1rem;
    font-size: 1.5rem;
    cursor: pointer;
}

.training-reservation div {
    font-weight: 500;
}

span {
    font-weight: 900;
}



.reservation-overlay {
    display: none;
    align-items: center;
    justify-content: center;
    position: absolute;
    top: 0;
    left: 0;
    z-index: 100;
    width: 100%;
    height: 100%;
    background-color: rgba(255, 255, 255, 0.6)
}

.training-reservation:hover .reservation-overlay {
    display: flex;
}

.reservation-overlay button {
    background: orange;
    color: white;
    font-size: 1rem;
    height: 2.5rem;
    width: 30%;
    border: none;
    cursor: pointer;
}
</style>