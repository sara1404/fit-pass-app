<script setup>
import axios from "axios"
</script>

<template>
    <form ref="registerForm" action="" class="form-wrapper">
        <div @click="$emit('closeRegisterForm')" class="close-icon">
            <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
        </div>
        <p class="title">Register</p>
        <div class="name-wrapper">
            <input type="text" placeholder="Name" v-model="name">
            <span class="error"> {{errors.nameError}}</span>
        </div>
        <div class="surname-wrapper">
            <input type="text" placeholder="Surname" v-model="surname">
            <span class="error"> {{errors.surnameError}}</span>
        </div>
        <div class="username-wrapper">
            <input type="text" placeholder="Username" v-model="username">
            <span class="error"> {{errors.usernameError}}</span>
        </div>
        <div class="sex-wrapper">
            <input type="radio" value="MALE" v-model="sex" name="sex-choice">
            <label for="">Male</label>
            <input type="radio" value="FEMALE" v-model="sex" name="sex-choice">
            <label for="">Female</label>
        </div>
        <div class="birthDate-wrapper">
            <input type="date" placeholder="Date of birth" v-model="birthDate">
            <span class="error"> {{errors.birthDateError}}</span>
        </div>
        <div class="password-wrapper">
            <input type="password" placeholder="Password" v-model="password">
            <span class="error"> {{errors.passwordError}}</span>
        </div>
        <button class="registerBtn" type="submit" v-on:click.prevent="register">REGISTER</button>
    </form>
</template>

<script>
export default {
  name: "LoginForm",
  data: function() {
    return {
      name: "",
      surname: "",
      username: "",
      password: "",
      sex: "MALE",
      birthDate: "",
      errors: {
        nameError: "",
        surnameError: "",
        usernameError: "",
        passwordError: "",
        birthDateError: "",
      }
    }
  },
  computed: {
    anyErrors: function() {
      let errors = Object.entries(this.errors).map(field => {
        console.log(field)
        return field[1].trim() === "" ? 0 : 1
      })
      return errors.reduce((accumulator, value) => accumulator + value, 0) != 0
    },
  },
  methods: {
    register: async function() {
      this.resetErrors()
      this.validate()
      if(this.anyErrors) return;

      try {
        await axios.post("http://localhost:8000/api/auth/register", this.createRegisterBody())
        this.$refs.registerForm.reset()
        this.$emit('closeRegisterForm')
      } catch(err) {
        console.log(err)
      }
    },
    validate() {
      this.required("name")
      this.required("surname")
      this.required("username")
      this.required("password")
      this.required("birthDate")
      this.minLength("password")
    },

    required: function(field) {
      if(this[field].trim() === "") {
        this.errors[field + "Error"] = "Field should not be empty"
      }
    },

    minLength: function(field) {
      if(this[field].trim().length < 8) {
        this.errors[field + "Error"] = "Minimum allowed size is 8"
      }
    },
    resetErrors: function() {
      this.errors = {
          nameError: "",
          surnameError: "",
          usernameError: "",
          passwordError: "",
          birthDateError: "",
      }
    },
    createRegisterBody() {
      return {
        name: this.name,
        surname: this.surname,
        username: this.username,
        password: this.password,
        sex: this.sex,
        birthDate: this.birthDate,

      }
    }


  }
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
    height: 650px;
    width: 500px;
    background-color: #fff;
    z-index: 1000;
    top: 50%;
    left: 50%;
    margin-top: -350px;
    margin-left: -250px;
    border-radius: 10px;
}

.title{
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.username-wrapper, .password-wrapper, .name-wrapper, 
.surname-wrapper, .birthDate-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    height: 60px;
    margin-top: 20px;
    gap: 5px;
}

.username-wrapper input, .password-wrapper input, .name-wrapper input,
.surname-wrapper input, .birthDate-wrapper input{
    min-height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
}

.sex-wrapper{
    display: flex;
    justify-content: center;
    align-items: center;
    height: 30px;
    gap: 20px;
    margin-top: 10px;
}

.registerBtn{
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
    background: linear-gradient(to right, black 50%, #ff7810 50%);
    background-size: 200% 100%;
    background-position: right bottom;
    transition: all .5s ease-out;

}

.registerBtn:hover{
    background-position: left bottom;
}

.error {
  color: red;
  width: 80%;
}

</style>