<script setup>
import axios from 'axios'
import Plan from '@/components/custom/Plan.vue'
</script>

<template>
    <div class="plans-container">
        <Plan :plan="plan" v-for="plan in plans" />
    </div>
    <div></div>
</template>

<script>
export default {
    name: "PlansView",
    data: function () {
        return {
            base: "http://localhost:8000/api/",
            plans: [],
        }
    },
    mounted: async function () {
        await this.getPlans();
    },
    methods: {
        getPlans: async function () {
            try {
                let resp = await axios.get(this.base + "users/subscription/packages", {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })
                this.plans = resp.data
            } catch (e) {
                console.log(e)
            }
        },
    }
}
</script>

<style scoped>
@import "@/assets/base.css";

.plans-container {
    width: 100%;
    height: 500px;
}
</style>