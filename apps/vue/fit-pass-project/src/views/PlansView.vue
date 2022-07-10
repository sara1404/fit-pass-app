<script setup>
import axios from 'axios'
import Plan from '@/components/custom/Plan.vue'
</script>

<template>
    <div class="plans-container-outer">
        <h1 class="plans-header">Plans that we offer!</h1>
        <div class="plans-container">
            <Plan :plan="plan" v-for="plan in plans" />
        </div>
    </div>

    <Footer></Footer>
</template>

<script>

import Footer from '../components/footer/Footer.vue';
export default {
    name: "PlansView",
    components: {
        Footer
    },
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


.plans-container-outer {
    width: 100%;
    min-height: 100vh;
    background: url(../assets/imgs/training-2.jpg) no-repeat;
    background-size: cover;
    padding-bottom: 2rem;
}

.plans-container {
    width: 100%;
    display: flex;
    gap: 2rem;
    align-items: center;
    justify-content: center;
    flex-wrap: wrap;
}

.plans-header {
    font-size: 4rem;
    font-family: Calibri, sans-serif;
    font-weight: bold;
    /* color: #ff7810; */
    color: white;
    text-align: center;
}
</style>