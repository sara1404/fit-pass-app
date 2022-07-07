<script setup>
import axios from 'axios'
import SportObjectWorkTime from '@/components/objects/SportObjectWorkTime.vue'
import SportObjectsMap from "@/components/objects/SportObjectsMap.vue"
import SportObjectInfo from "@/components/custom/SportObjectInfo.vue"
import SingleSportObjectContent from "@/components/custom/SingleSportObjectContent.vue"
import SportObjectSchedule from "@/components/custom/SportObjectSchedule.vue"
import CommentSection from "@/components/custom/CommentSection.vue"

import { useProfileStore } from "@/stores/profile-store.js"
import { mapState } from "pinia"
import { sportObjectsStore } from '../stores/objects-store'
</script>


<template>
    <div class="container">
      <SportObjectInfo :sportObject="sportObject"/>
      <SingleSportObjectContent :sportObject="sportObject"/>
      <div class="time-and-map">
        <SportObjectWorkTime :workTimeStyle="workTimeStyle" :singleWorkTimeStyle="singleWorkTimeStyle" :titleStyle="titleStyle" class="work-time" :objectsWorkTime='sportObject.workTime'/>
        <div class="map-wrapper">
          <SportObjectsMap :sportObjects="[sportObject]"/>
          <label for="">{{sportObject.location.address.street}} {{sportObject.location.address.number}}
            , {{sportObject.location.address.city}}, {{sportObject.location.address.country}}</label>
        </div>
      </div>
      <SportObjectSchedule :sportObject="sportObject"/>
      <CommentSection :sportObject="sportObject" :comments="comments"/>
    </div>
</template>

<script>
export default {
  name: "SportObjectView",
  data: function(){
    return{
        sportObject : {
          location: {
            address: {}
          },
          content: []
        },
        comments:[],
        base: "http://localhost:8000/api/",
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
        console.log('aaa', resp)
        if(resp.status == 200)
            this.sportObject = resp.data
        console.log('eee', this.sportObject)
        let resp1 = await axios.get('http://localhost:8000/api/objects/' + this.sportObject.id+ "/comments/all",
        {
            headers:{
                Authorization: "Bearer " + localStorage.getItem("auth-token")
        }})
        if(resp1.status == 200){
            this.comments = resp1.data
        }
    }catch(e){
        console.log(e.message)
    }
  },
  methods:{
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

.time-and-map{
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 60%;
  height: 500px;
}
.map-wrapper{
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 15px;
  height: 400px;
  width: 60%;
  color: gray;
}

</style>