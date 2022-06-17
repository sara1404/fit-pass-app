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
        <div class="status-and-time">
          <label class="status" for="">{{sportObjectChild.status}}</label>
          <label class="work-time" for="">{{sportObjectChild.workTime}}</label>
        </div>
      </div>
    </div>
</template>

<script>
export default {
  name: "SportObject",
  props: {
    sportObjectChild: Object
    },
  data: function(){
    return{
    }
  },
  methods: {
    printObject: function() {
      console.log(this.sportObjectChild)
    }
  },
  mounted: function(){
  
  }
}

</script>

<style scoped>
@import "@/assets/base.css";
.object-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: center;
  height: 80%;
  width: 250px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  border-radius: 7px;
  padding: 10px;
}

.object-wrapper:hover{
  transform: scale(1.02);
  transition-duration: 250ms ;
  cursor: pointer;
  border: 1px solid #ff7810;
  box-shadow: #ff7810;
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
  color: rgb(75, 74, 74);
}
</style>