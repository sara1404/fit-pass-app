<script setup>
    import axios from 'axios'
    import SportObjectWorkTime from '@/components/objects/SportObjectWorkTime.vue'
    import Comment from '@/components/custom/Comment.vue'
    import { useProfileStore } from "@/stores/profile-store.js"
    import { mapState } from "pinia"
import { sportObjectsStore } from '../stores/objects-store'
</script>


<template>
    <div class="container">
      <div class="basic-info">
        <div>
          <img v-bind:src="sportObject.logoUrl" alt="" height="100" width="100">
        </div>
        <div class="name-grade-wrapper">
          <label class="title" for="">{{sportObject.name}}</label>
          <div class="grade-wrapper">
            <label for="">Grade: </label>
            <label class="grade" for="">{{sportObject.averageMark}} </label>
          </div>
        </div>
      </div>
      
      <div class="content-wrapper">
        <div v-for="content in sportObject.content" :key="content.name" class="objContent">
          {{content.name}}
        </div>
      </div>
      <div class="time-and-map">
        <SportObjectWorkTime :workTimeStyle="workTimeStyle" :singleWorkTimeStyle="singleWorkTimeStyle" :titleStyle="titleStyle" class="work-time" :objectsWorkTime='sportObject.workTime'/>
      </div>

      <div class="schedule-wrapper">
        <div class="schedule-header">
          <img src="../assets/imgs/left-arrow.png" alt="" @click="displayContentDayBefore">
          <div class="date-input">{{chosenDate}}</div>
          <img src="../assets/imgs/right-arrow.png" alt="" @click="displayContentDayAfter">
        </div>
        <div class="schedule-content">
          <div class="single-content" v-for="content in sportObject.content" :key="content.name" v-show="isContentAvailableInDate(content)">
            <div class="content-name">
                <label for="">{{content.name}}</label>
                <label for="" v-show="content.flag==='training'">{{content.appointments}}</label>
            </div>
            <label for="">{{displayAdditionalFees(content)}}</label>
          </div>
        </div>
      </div>
      
      <section class="comments">
        <div class="comment-title-wrapper">
          <label class="comment-title" for="">Comments and grades ({{commentsSize}})</label>
          <img src="../assets/imgs/add-comment.png" alt="" height="40" width="40" @click="addCommentClicked=true;" v-show="visited">
        </div>
        <div class="add-comment" v-show="addCommentClicked">
          <input type="text" placeholder="Your comment...">
          <button class="submit-btn">Submit</button>
        </div>
        <div class="average-grade">
          <img src="../assets/imgs/star.png" alt="" height="50" width="50">
          <label for="">{{sportObject.averageMark}}</label>

        </div>
        <Comment :comment="com" v-for="com in comments" :key="com.id"/>
      </section>

    </div>
</template>

<script>
export default {
  name: "SportObjectView",
  data: function(){
    return{
        sportObject : Object,
        chosenDate: this.getNow(),
        comments: [],
        commentsSize: "0",
        addCommentClicked: false,
        visited: false,
        workTimeStyle: {
          display: "flex",
          position: "unset",
          color: "#000",
          backgroundColor: "transparent",
          flexDirection: "column",
          justifyContent: "center",
          alignItems: "flex-start",
          width: "30%",
          fontSize:"18px",
          marginTop:"1rem"
        },
        singleWorkTimeStyle: {
            display: "flex",
            justifyContent: "space-between",
            width: "100%",
            padding:"10px 0"
        },
        titleStyle: {
          fontSize:"22px",
          justifyContent:"center",
          textAlign:"center",
          width:"100%"
        }
    }
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  },
  mounted: async function(){
    try{
        let resp = await axios.get('http://localhost:8000/api/objects/' + this.$route.params.id)
        if(resp.status == 200)
            this.sportObject = resp.data
        let resp1 = await axios.get('http://localhost:8000/api/objects/' + this.sportObject.id+ "/comments/all",
        {
            headers:{
                Authorization: "Bearer " + localStorage.getItem("auth-token")
        }})
        if(resp1.status == 200){
            this.comments = resp1.data
            this.commentsSize = this.comments.length
        }
        console.log(this.comments)

    }catch(e){
        console.log(e.message)
    }
    this.setVisited()
  },
  methods:{
    calculateAverageGrade: function(){
      let total = 0 
      for(comment in this.comments){
        total += comment.grade
      }
      return total/this.comments.length
    },

    setVisited: function(){
      if(this.profile.role !== "BUYER")
        this.visited = false
      for(let obj of this.profile.visitedObjects){
        if(obj.id === this.sportObject.id){
          this.visited = true
        }
      }
    },

    getNow: function(){
       let now = Date.now()
       let today = new Date(now);
       return today.toDateString()
    },

    displayContentDayBefore: function(){
      let date = new Date(this.chosenDate);
      date.setDate(date.getDate() - 1);
      this.chosenDate = date.toDateString()
    },

    displayContentDayAfter: function(){
      let date = new Date(this.chosenDate);
      date.setDate(date.getDate() + 1);
      this.chosenDate = date.toDateString()
    },

    isContentAvailableInDate: function(content){
        let day = this.mapNumToDay(new Date(this.chosenDate).getDay())
        for(let workDay of content.workTime){
          if(workDay.day === day){
            return true
          }
        }
        return false
    },

    mapNumToDay: function(num){
      switch(num){
        case 0: return "Sunday"
        case 1: return "Monday"
        case 2: return "Tuesday"
        case 3: return "Wednesday"
        case 4: return "Thursday"
        case 5: return "Friday"
        case 6: return "Saturday"
      }
    },

    displayAdditionalFees: function(content){
      if(content.type === "OTHER")
        return "WITHOUT ADDITIONAL PAY"
      return content.price
    }
  }
}

</script>

<style scoped>
@import "@/assets/base.css";

.container{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  padding: 1rem 3rem;
}

.title{
  font-size: 1.8rem;
  font-weight: 500;

}

.average-grade{
  display: flex;
  align-items: center;
  border-bottom: 1px solid lightgray;
  font-size: 2rem;
  gap: 1rem;
  padding: .5rem 0;
}

.basic-info{
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1rem;
  width: 60%;
}

.time-and-map{
  width: 60%;
}

.name-grade-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: .8rem;
}

.grade-wrapper{
  display: flex;
  align-items: center;
  font-size: 18px;
  gap: 7px;
}

.grade{
  background-color: #ff7810;
  padding: 6px 10px;
  border-radius: 7px;
  color: #fff;
}

.content-wrapper{
  display: flex;
  flex-wrap: wrap;
  gap: 5px;
  color: rgb(87, 86, 86);
  width: 60%;
}

.objContent{
  padding: 7px;
  border-radius: 10px;
  border: 1px solid darkgray;
}

.schedule-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 60%;
}

.schedule-header{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 3rem;
  gap: 10px;
}

.date-input{
  display: flex;
  justify-content: center;
  align-items: center;
  height: 2rem;
  width: 70%;
  border: 1px solid lightgray;
  border-radius: 1rem;
  font-size: 1.2rem;
}

.schedule-header img{
  cursor: pointer;
}

.schedule-content{
  display: flex;
  flex-direction: column;
  width: 80%;
}

.single-content{
  display: flex;
  justify-content: space-between;
  padding: .5rem;
  border-bottom: 1px solid rgb(224, 224, 224);
  color: gray;
}

.comments{
  display: flex;
  flex-direction: column;
  padding: .5rem 0;
  width: 60%;
}

.comment-title-wrapper{
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid lightgray;
  font-size: 1.5rem;
  padding: .5rem;
}

.add-comment{
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: .5rem;
  margin: .5rem 0;
  border-bottom: 1px solid lightgray;
  padding: .5rem;

}

.add-comment input{
  width: 98%;
  height: 4rem;
  outline: none;
  border: 1px solid lightgray;
  border-radius: .3rem;
  padding-left: 10px;
}

.submit-btn{
  background-color: #ff7810;
  color: #fff;
  outline: none;
  border: none;
  height: 2rem;
  width: 4rem;
  border-radius: 10px;
  cursor: pointer;
}

.comment-title-wrapper img{
  cursor: pointer;
}

</style>