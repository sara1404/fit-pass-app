<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
import axios from "axios"
import {mapState} from "pinia"
</script>

<template>
    <div class="wrapper">
        <form action="" class="form-wrapper">
            <div @click="$emit('closeBuySubscriptionForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>
            <p class="title">Buy subscription</p>
            <label for="" class="errorLabel">{{error}}</label>
            <div class="sub-wrapper">
                <select name="" id="" v-model="selectedSubscription">
                    <option :value="sub" v-for="sub in subscriptions" :key="sub">{{sub.type + " " + sub.allowedEntersPerDay + " " + sub.price}}</option>
                </select>
            </div>
            <div class="promo-wrapper">
                <input v-model="promoCode" type="text" placeholder="Promo code">
            </div>
            <button class="saveBtn" type="submit" v-on:click.prevent="buySubscription">BUY</button>
        </form>
    </div>
        
    
</template>

<script>
export default {
  name: "BuySubscriptionForm",
  mounted: async function() {
    this.profileStore = useProfileStore()
    await this.profileStore.captureAllSubscriptions()
  },
  computed:{
    ...mapState(useProfileStore, ['subscriptions'])
  },
  data: function() {
      return {
          profileStore: null,
          selectedSubscription:{},
          promoCode:"",
      }
  },
  methods: {
    buySubscription: async function() {
        await this.profileStore.buySubscription(this.selectedSubscription)
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

.errorLabel{
    display: flex;
    justify-content: center;
    color: red;
}

.wrapper{
    display: flex;
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-color: rgba(255,255,255,0.5);
    z-index: 10000000;
}

.form-wrapper{
    display: flex;
    flex-direction: column;
    position: fixed;
    height: 570px;
    width: 500px;
    background-color: #fff;
    z-index: 1000;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    margin-left: -250px;
    border: 1px solid lightgray;
    border-radius: 10px;
}

.title{
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.sub-wrapper, .promo-wrapper{
    display: flex;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
}

.sub-wrapper input, .promo-wrapper input{
    height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
}

.saveBtn{
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
    transition: all .3s ease-out;
}

.saveBtn:hover{
    background-position: left bottom;
}


</style>