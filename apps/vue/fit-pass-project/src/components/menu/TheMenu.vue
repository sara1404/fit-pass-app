<script setup>
import AuthMenu from "@/components/menu/AuthMenu.vue";
import {defineComponent} from "vue";

defineComponent(AuthMenu)
</script>

<template>
    <nav>
      <ul class="menu-items">
        <li class="menu-item"><router-link to="/">Home</router-link></li>
        <li class="menu-item"><router-link to="/plans">Plans</router-link></li>
        <li class="menu-item"><router-link to="/objects">Objects</router-link></li>
        <li class="menu-item"><router-link to="/about">About us</router-link></li>
      </ul>

      <AuthMenu/>
    </nav>
</template>

<script>
export default {
  name: "TheMenu"
}
</script>

<style scoped>
@import "/fit-pass-project/src/assets/base.css";

  nav {
    height: 10vh;
    width: 100%;
    position: sticky;
    top: 0;
    left: 0;
  }

  .menu-items {
    display: flex;
  }

  .menu-item {
    list-style: none;
    font-size: 1.5rem;
    font-weight: bold;
    color: black;
    margin: 5px;
    box-shadow: black ;

  }

  .menu-item a {
    text-decoration: none;
    color: black;
  }
</style>