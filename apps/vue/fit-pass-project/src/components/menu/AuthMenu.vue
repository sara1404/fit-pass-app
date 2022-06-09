<template>
  <div class="auth-menu">
    <button @click="$emit('displayLogin')">Login</button>
    <button @click="$emit('displayRegister')">Register</button>
  </div>

</template>

<script>
export default {
  name: "AuthMenu",
}

</script>

<style scoped>
  .auth-menu {
    display: flex;
    gap: 10px;
    padding-right: 20px;
  }

  .auth-menu button {
    width: 80px;
    height: 43px;
    background: green;
    color: white;
    outline: none;
    border: none;
    border-radius: 10px;
    padding: 5px;
    cursor: pointer;
    font-size: 15px;
  }

  .auth-menu button:hover{
    transform: scale(1.05);
    transition-duration: 0.5s;
  }

</style>