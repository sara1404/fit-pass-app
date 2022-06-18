<template>
  <div class="auth-menu">
    <button id="loginBtn" @click="$emit('displayLogin')">Login</button>
    <button id="registerBtn" @click="$emit('displayRegister')">Register</button>
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
    display: flex;
    justify-content: center;
    align-items: center;
    width: 80px;
    height: 43px;
    border-radius: 10px;
    padding: 5px;
    cursor: pointer;
    font-size: 1rem;
    font-weight: 500;
  }

  #loginBtn{
    color: #ff7810;
    border: 1.7px solid #ff7810;
    background-color: transparent;
  }

  #loginBtn:hover{
    transition: border;
    border: 2px solid #ff7810;
  }

  #registerBtn{
    width: 100px;
    background-color: #ff7810;
    border: none;
    color: #fff;
  }
  

  .auth-menu button:hover{
    transform: scale(1.05);
    transition-duration: 0.5s;
  }

</style>