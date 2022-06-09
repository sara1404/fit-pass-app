<script setup>
import { useTestStore } from "@/stores/test-store.js"
const testStore = useTestStore()

function login() {
    testStore.printMessage();
}
</script>

<template>
    <form action="" class="form-wrapper">
        <div @click="$emit('closeLoginForm')" class="close-icon">
            <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
        </div>
        <p class="title">Login</p>
        <div class="username-wrapper">
            <input type="text" placeholder="Username">
        </div>
        <div class="password-wrapper">
            <input type="password" placeholder="Password">
        </div>
        <button class="loginBtn" type="submit" v-on:click.prevent="login">LOGIN</button>
    </form>
</template>

<script>
export default {
  name: "LoginForm",
}
</script>

<style scoped>
@import "@/assets/base.css";

.close-icon{
    display: flex;
    justify-self: flex-end;
    align-self: flex-end;
    height: 20px;
    width: 20px;
    padding: 5px;
    cursor: pointer;
}

.close-icon img{
    height: 20px;
    width: 20px;
}

.form-wrapper{
    display: flex;
    flex-direction: column;
    position: fixed;
    margin: auto;
    height: 380px;
    width: 500px;
    background-color: #fff;
    z-index: 1000;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    margin-left: -250px;
    border-radius: 10px;
}

.title{
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.username-wrapper, .password-wrapper{
    display: flex;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
}

.username-wrapper input, .password-wrapper input{
    height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
}

.loginBtn{
    width: 80%;
    height: 50px;
    margin: auto;
    border-radius: 5px;
    outline: none;
    border: none;
    background: #ff7810;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    background: linear-gradient(to right, #fff 50%, #ff7810 50%);
    background-size: 200% 100%;
    background-position: right bottom;
    transition: all .5s ease-out;

}

.loginBtn:hover{
    background-position: left bottom;
}

</style>