<script setup>
import { useTestStore } from "@/stores/test-store.js"
const testStore = useTestStore()

function login() {
    testStore.printMessage();
}
</script>

<template>
    <form action="">
        <p class="title">Login</p>
        <div class="username-wrapper">
            <input type="text" placeholder="Username">
        </div>
        <div class="password-wrapper">
            <input type="password" placeholder="Password">
        </div>
        <button type="submit" v-on:click.prevent="login">LOGIN</button>
    </form>
</template>

<script>
export default {
  name: "LoginForm",
}
</script>

<style scoped>
@import "@/assets/base.css";


</style>