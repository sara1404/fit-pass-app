<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
</script>

<template>
     <div class="wrapper">
        <form action="" class="form-wrapper">
            <div @click="$emit('closeAddForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>
            <p class="title">Add content</p>
            <div class="option-wrapper">
                <div>
                    <input type="radio" name="contentType" value="RELAXATION" @click="chosenChange(false)" v-model="typeOfContent">
                    <label for="">RELAXATION</label>
                </div>
                <div>
                    <input type="radio" name="contentType" value="TRAINING" @click="chosenChange(true)" v-model="typeOfContent">
                    <label for="">TRAINING</label>
                </div>
            </div>
            <div class="name-wrapper">
                <input type="text" placeholder="Name" v-model="name">
            </div>
            <div class="type-wrapper">
                <input type="text" placeholder="Type" v-model="type">
            </div>
            <div class="pic-wrapper">
                <input type="file" >
            </div>
            <div class="description-wrapper" v-show="chosen">
                <input type="text" placeholder="description" >
            </div>
            <div class="object-wrapper" v-show="chosen">
                <select name="" id="" v-model="object">
                    <option :value="obj.id" v-for="obj in sportObjects" :key="obj.id">{{obj.name}}</option>
                </select>
            </div>
            <div class="coach-wrapper" v-show="chosen">
                <select name="" id="">
                    <option :value="coach.username" v-for="coach in coaches" :key="coach.username">{{coach.name + coach.surname}}</option>
                </select>
            </div>
            <div class="duration-wrapper" v-show="chosen">
                <input  type="number" placeholder="Training duration in minutes" min="0" max="120">
            </div>
            <button class="addBtn" type="submit" v-on:click.prevent="addContent">SAVE</button>
        </form>
    </div>
</template>

<script>
export default {
  name: "SportObjectContentForm",
  props:{
    content: Object,
  },
  data: function() {
      return {
          name: "",
          password: "",
          profileStore: null
      }
  },
  methods: {
    login: function() {
        let body = {
            username: this.username, 
            password: this.password
        }
        
        this.profileStore.login(body)
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

.wrapper{
    display: flex;
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-color: rgba(255,255,255,0.5);
    z-index: 100000000000;
}

.form-wrapper{
    display: flex;
    flex-direction: column;
    position: fixed;
    width: 500px;
    min-height: 500px;
    max-height: 1000px;
    background-color: #fff;
    z-index: 1000;
    left: 50%;
    margin-left: -250px;
    border: 1px solid lightgray;
    border-radius: 10px;
    padding-bottom: 1rem;
}

.title{
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.option-wrapper{
    gap: 1.2rem;
}

.name-wrapper, .type-wrapper, .pic-wrapper, .description-wrapper,
.duration-wrapper, .object-wrapper, .coach-wrapper, .option-wrapper{
    display: flex;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
}

.name-wrapper input, .type-wrapper input, .description-wrapper input, .duration-wrapper input,
.object-wrapper select, .coach-wrapper select{
    height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
}

.addBtn{
    width: 80%;
    height: 50px;
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
    transition: all .3s ease-out;
    margin: 1rem auto;
}

.addBtn:hover{
    background-position: left bottom;
}

</style>