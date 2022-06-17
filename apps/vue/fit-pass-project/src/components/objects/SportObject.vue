<script setup>
import SportObjectWorkTime from "@/components/objects/SportObjectWorkTime.vue";
import {defineComponent} from "vue";

defineComponent(SportObjectWorkTime)
</script>


<template>
    <div class="object-wrapper">
      <div class="logo-wrapper">
        <img v-bind:src="sportObjectChild.logoUrl" class="logo">
      </div>
      <div class="object-info">
        <div class="name-and-grade">
          <label for="" class="grade">{{sportObjectChild.averageMark}}</label>
          <label for="" class="name">{{sportObjectChild.name}}</label>
        </div>
        <div class="address">
          <label for="">{{sportObjectChild.location.address.street + " " + sportObjectChild.location.address.number + ", " + sportObjectChild.location.address.city }}</label>
        </div>
        <div class="type">
          <label for="">{{sportObjectChild.type}}</label>
        </div>
        <div class="status-and-time" >
          <label class="status" for="">{{sportObjectChild.status}}</label>
          <label for="" class="time" v-on:mouseover="this.displayWorkTime = true;" v-on:mouseleave="this.displayWorkTime = false;">{{this.currentWorkTime}}</label>
        </div>
      </div>
      <SportObjectWorkTime :objectsWorkTime="sportObjectChild.workTime" v-show="displayWorkTime===true"/>
    </div>
</template>

<script>
Date.now()
export default {
  name: "SportObject",
  props: {
    sportObjectChild: Object
    },
  data: function(){
    return{
      currentWorkTime: "",
      displayWorkTime: false,
    }
  },
  mounted: function() {
    this.currentWorkTime = this.calculateCurrentWorkTime()
  },

  methods: {
    calculateCurrentWorkTime: function() {
      let date = new Date(Date.now())
      if(date.getDay() == 0) return this.convertTimeObjectToFormatedString(this.sportObjectChild.workTime[6].time)
      return this.convertTimeObjectToFormatedString(this.sportObjectChild.workTime[date.getDay() - 1].time)
    },

    convertTimeObjectToFormatedString(time) {
      let startTime = this.formatWorkTime(time.starts)
      let endTime = this.formatWorkTime(time.ends)
      return startTime + " - " + endTime;
    },
    printObject: function() {
      console.log(this.sportObjectChild)
    },
    convertEnumerationToDay: function(param){
      switch(param){
        case 0: return "Sunday";
        case 1: return "Monday"; 
        case 2: return "Tuesday";
        case 3: return "Wednesday";
        case 4: return "Thursday";
        case 5: return "Friday";
        case 6: return "Saturday";
      }
    },
    formatWorkTime:function(time){
      let tokens = []
      tokens = time.split('-')
      return tokens[0]+':'+tokens[1]
    }

  }
}

</script>

<style scoped>
@import "@/assets/base.css";

.object-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 100%;
  width: 300px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  border-radius: 7px;
  padding: 10px;
}

.object-wrapper:hover{
  transform: scale(1.02);
  transition-duration: 1000ms;
  cursor: pointer;
  border: 1px solid darkgray;
  box-shadow: 0 0 10px darkgray;
}

.logo-wrapper{
  display:flex;
  justify-content: center;
}

.logo{
  height: 70%;
  width: 100%;
  border-radius: 5px;
}

.name-and-grade{
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 10px 0;
  gap: 15px;
}

.name{
  font-size: 24px;
}

.grade{
  width: 23px;
  background-color: #ff7810;
  padding: 5px;
  border-radius: 10px;
  color: #fff;
  font-size: 18px;
  text-align: center;
}

.address{
  color: rgb(126, 123, 123);
  margin-bottom: 12px;
}

.type{
  color: rgb(75, 74, 74);
  font-style: bold;
  margin-bottom: 15px;
}

.status-and-time{
  display: flex;
  align-items: center;
  gap: 30px;
}

.status{
  text-align: center;
  align-items: center;
  width: 17%;
  border: 1px solid rgb(30, 50, 223);
  border-radius: 5px;
  padding: 5px 8px;
  color: rgb(30, 50, 223);
}

.time{
  text-align: center;
  align-items: center;
  border: 1px solid rgb(30, 50, 223);
  border-radius: 5px;
  padding: 5px 8px;
  color: rgb(75, 74, 74);
  cursor: pointer;
}

.no-work-time{
  display: none;
}
</style>