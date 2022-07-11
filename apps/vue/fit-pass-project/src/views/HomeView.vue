<script setup>
import TheWelcome from '@/components/welcome/TheWelcome.vue'
import CustomObjectSlider from '../components/custom/CustomObjectSlider.vue';
import Footer from '../components/footer/Footer.vue';

</script>

<template>
    <TheWelcome />
    <CustomObjectSlider></CustomObjectSlider>
    <div class="plans">
        <a href="/plans">Buy your subscription here!</a>
    </div>
    <Footer />
</template>


<style>
@import "@/assets/base.css";

.plans {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 50px;
    background: linear-gradient(to right, rgb(252, 74, 26), rgb(247, 183, 51));
}

.plans a {
    color: #fff;
    font-size: 1.7rem;
    text-decoration: none;
}

.plans a:hover {
    text-decoration: underline;
}
</style>
