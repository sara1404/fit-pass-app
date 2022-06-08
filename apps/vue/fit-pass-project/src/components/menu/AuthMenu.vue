<template>
  <div class="auth-menu">
    <button>Login</button>
    <button>Register</button>
  </div>

</template>

<script>
export default {
  name: "AuthMenu",
  methods: {
    login: function() {
      let body = {
        username: "srdjan123",
        password: "123"
      }
      fetch('http://localhost:8000/api/auth/login', {
        method: 'post',
        body,
        headers: {
            contentType: "application/json"
        }
      }).then(res => {
        if(!res.ok) {
          const error = new Error(res.statusText);
          error.json = res.json;
          throw error;
        }
        return res.json();
      }).then(json => {
        console.log(json)
      }).catch(err => {
        console.log(err)
      })
    }
  }
}
</script>

<style scoped>
  .auth-menu {
    display: flex;
    align-self: flex-end;
  }

  .auth-menu button {
    width: 60px;
    height: 40px;
    background: green;
    color: white;
  }

</style>