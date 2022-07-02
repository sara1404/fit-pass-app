<script setup>
    import axios from 'axios'
    import SportObjectWorkTime from '@/components/objects/SportObjectWorkTime.vue'
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
      
      <SportObjectWorkTime class="work-time" :objectsWorkTime='sportObject.workTime'/>
    </div>
</template>

<script>
export default {
  name: "SportObjectView",
  data: function(){
    return{
        sportObject : Object,
        comments: [],
    }
  },
  mounted: async function(){
    try{
        let resp = await axios.get('http://localhost:8000/api/objects/' + this.$route.params.id)
        if(resp.status == 200)
            this.sportObject = resp.data
        let resp1 = await axios.get('http://localhost:8000/api/objects/' + this.sportObject.id+ "/comments",
        {
            headers:{
                Authorization: "Bearer " + localStorage.getItem("auth-token")
        }})
        if(resp1.status == 200)
            this.comments = resp1.data
        console.log(this.comments)

    }catch(e){
        console.log(e.message)
    }
  }
}

</script>

<style scoped>
@import "@/assets/base.css";

.container{
  padding: 1rem 3rem;
}

.work-time-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  position: unset;
  width: 40%;
  background-color: transparent;
  color: #000;
}

.title{
  font-size: 1.8rem;
  font-weight: 500;

}

.single-work-time{
  display: flex;
  justify-content: space-between;
}

.basic-info{
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1rem;
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
}

.objContent{
  padding: 7px;
  border-radius: 10px;
  border: 1px solid darkgray;
}
</style>