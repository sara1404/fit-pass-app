<script setup>
import { useProfileStore } from '../../stores/profile-store';
</script>

<template>
    <div class="container">
        <div class="plan-card-top">
            <div class="plan-top-type">
                <h1>{{ plan.type }}</h1>
            </div>
            <div class="plan-top-price">
                <span>
                    <h1>{{ plan.price }} RSD</h1>
                    &nbsp;
                    &nbsp;
                    <h2> / {{ planDuration }}</h2>
                </span>
            </div>
        </div>
        <hr />
        <div class="plan-card-center">
            <h2>Allowed Enters per Day {{ plan.allowedEntersPerDay }}</h2>
            <h2>All Objects Included</h2>
            <h2>Personal Trainings</h2>
            <h2>Group Trainings</h2>
        </div>
        <div class="plan-card-button">
            <button @click="buySubscription">GET STARTED</button>
        </div>
    </div>
</template>

<script>
export default {
    name: "Plan",
    props: {
        plan: Object
    },
    data: function () {
        return {
            profileStore: null,
        }
    },
    computed: {
        planDuration: function () {
            if (this.plan.type === "MONTHLY") {
                return "month"
            }
            return "year"
        }
    },
    mounted: function () {
        this.profileStore = useProfileStore()
    },
    methods: {
        buySubscription: function () {

        }
    }
}
</script>

<style scoped>
@import "@/assets/base.css";

.container {
    width: 400px;
    min-height: 60%;
    flex-wrap: wrap;
    align-items: flex-start;
    justify-content: center;
    box-shadow: 0 0 8px rgba(0, 0, 0, 0.3);
    row-gap: 2rem;
    padding: 2rem;
    background: white;
    border-radius: 5px;
}

.plan-card-top {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    max-height: 300px;
    padding-bottom: 3rem;
}

.plan-top-type {
    color: rgb(122, 118, 118);
}

.plan-top-price {
    display: flex;
}

.plan-top-price * {
    margin: 0;
}

.plan-top-price span {
    display: flex;
    align-items: flex-end;
    justify-content: center;
}

.plan-top-price span h2 {
    color: rgb(122, 118, 118);
    padding-bottom: 10px;
}

.plan-top-price span h1 {
    color: #ff7810;
    font-size: 3rem;
}

.plan-card-button {
    width: 100%;
    height: 60px;
    display: flex;
    justify-content: center;
    margin-top: 4rem;
}

.plan-card-button button {
    font-family: Calibri, sans-serif;
    font-size: 1.5rem;
    font-weight: bold;
    background: #ff7810;
    color: white;
    outline: white;
    border: none;
    border-radius: 5px;
    padding: 10px;
    cursor: pointer;
    background: linear-gradient(to right, black 50%, #ff7810 50%);
    background-size: 200% 100%;
    background-position: right bottom;
    transition: all .5s ease-out;
    width: 80%;
    height: 100%;
}


.plan-card-button button:hover {
    background-position: left bottom;
}

.plan-card-center {
    color: rgb(122, 118, 118);
    padding: 2rem 0 2rem 0;
    display: flex;
    flex-direction: column;
    align-items: center;
}

hr {
    border: 1px solid lightgray;
    width: 100%;
}
</style>