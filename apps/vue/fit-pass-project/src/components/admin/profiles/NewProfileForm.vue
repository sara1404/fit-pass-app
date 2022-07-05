<template>
  <form ref="registerForm" action="" class="form-wrapper">
    <div @click="$emit('closeRegisterForm')" class="close-icon">
      <img src="../../../assets/imgs/close-icon.png" height="20px" width="20px">
    </div>
    <p class="title">Register</p>
    <div class="error-wrapper">
      {{globalError}}
    </div>
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
    <div class="birthDate-wrapper">
      <input type="date" placeholder="Date of birth" v-model="birthDate">
      <span class="error"> {{errors.birthDateError}}</span>
    </div>
    <div class="password-wrapper">
      <input type="password" placeholder="Password" v-model="password">
      <span class="error"> {{errors.passwordError}}</span>
    </div>
    <div class="role-wrapper">
      <select name="" v-model="role">
        <option value="MANAGER">MANAGER</option>
        <option value="COACH">COACH</option>
      </select>
      <span class="error"> {{errors.roleError}}</span>
    </div>
    <div class="sex-wrapper">
      <input type="radio" value="MALE" v-model="sex">
      <label for="">Male</label>
      <input type="radio" value="FEMALE" v-model="sex">
      <label for="">Female</label>
    </div>
    <button class="register-btn" type="submit" v-on:click.prevent="register">REGISTER</button>
  </form>
</template>

<script>

import {sportObjectsStore} from "../../../stores/objects-store.js";
import {useProfileStore} from "../../../stores/profile-store.js";
import {mapState} from "pinia";

export default {
  name: "NewProfileForm",
  data: function() {
    return {
      objectsStore: null,
      profileStore: null,
      name: "",
      surname: "",
      username: "",
      password: "",
      sex: "MALE",
      birthDate: "",
      globalError: "",
      sportObject: null,
      role: "MANAGER",
      errors: {
        nameError: "",
        surnameError: "",
        usernameError: "",
        passwordError: "",
        birthDateError: "",
        sportObjectError: "",
        roleError: "",
      }
    }
  },
  mounted: function() {
      this.objectsStore = sportObjectsStore();
      this.objectsStore.getSportObjects()
      this.profileStore = useProfileStore()
  },
  computed: {
    ...mapState(sportObjectsStore, ['sportObjects']),
    anyErrors: function() {
      let errors = Object.entries(this.errors).map(field => {
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
        this.globalError = ""
        await this.profileStore.registerUserAsAdmin(this.createRegisterBody())
        this.resetFormData()
        this.$emit('closeRegisterForm')
        this.$emit('user-registered')
      } catch(err) {
        this.globalError = err.response.data
        console.log(err)
      }
    },
    validate() {
      this.required("name")
      this.required("surname")
      this.required("username")
      this.required("password")
      this.required("birthDate")
      this.required("role")
      this.minLength("password")
    },

    required: function(field) {
      if(this[field].trim() === "") {
        this.errors[field + "Error"] = "Field should not be empty!"
      }
    },
    notNull: function(field) {
      if(this[field] === null) {
        this.errors[field + "Error"] = "Field should not be empty!"
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
        sportObjectError: "",
        roleError: ""
      }
    },
    createRegisterBody: function () {
      return {
        name: this.name,
        surname: this.surname,
        username: this.username,
        password: this.password,
        sex: this.sex,
        birthDate: this.birthDate,
        sportObject: this.sportObject,
        role: this.role
      }
    },
    resetFormData: function() {
      this.name = ""
      this.username = ""
      this.surname = ""
      this.sex = "MALE"
      this.password = ""
      this.birthDate = ""
      this.sportObject = null
      this.role = "MANAGER"
      this.globalError = ""
      this.resetErrors()
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
  justify-content: space-evenly;
  position: fixed;
  margin: -350px auto auto -250px;
  min-height: 650px;
  width: 500px;
  background-color: #fff;
  z-index: 1000;
  top: 50%;
  left: 50%;
  padding: 5px 5px 5px 5px ;
  border: 2px solid lightgray;
  border-radius: 10px;
}

.title{
  display: flex;
  justify-content: center;
  font-size: 30px;
  margin: 0;
}

.username-wrapper, .password-wrapper, .name-wrapper,
.surname-wrapper, .birthDate-wrapper, .role-wrapper, .sport-objects-wrapper {
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

.sport-objects-wrapper select, .role-wrapper select {
  width: 80%;
  min-height: 40px;
  border: 1px solid gray;
  font-size: 1.2rem;
  text-align: center;
}
.sex-wrapper{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 30px;
  gap: 20px;
  margin-top: 10px;
}

.register-btn{
  width: 80%;
  height: 50px;
  margin: 20px auto;
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

.register-btn:hover{
  background-position: left bottom;
}

.error {
  color: red;
  width: 80%;
}

.error-wrapper {
  color: red;
  width: 80%;
  height: 15px;
  margin: 0px;
  padding: 0px;
  align-self: center;
  text-align: center;
}

</style>