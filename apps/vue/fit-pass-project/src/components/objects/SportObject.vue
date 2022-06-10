<template>
    <div class="object-wrapper">
      <div class="logo-wrapper">
        <img v-bind:src="'src/assets/imgs/' + sportObjectChild.logo" class="logo">
      </div>
      <div class="object-info">
        <div class="name-and-grade">
          <label for="" class="grade">{{sportObjectChild.averageGrade}}</label>
          <label for="" class="name">{{sportObjectChild.name}}</label>
        </div>
        <div class="address">
          <label for="">{{sportObjectChild.location}}</label>
        </div>
        <div class="type">
          <label for="">{{sportObjectChild.type}}</label>
        </div>
        <div class="status-and-time">
          <label class="status" for="">{{sportObjectChild.status}}</label>
          <label for="">{{sportObjectChild.workingTime}}</label>
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
  border: 1px solid lightgray;
  border-radius: 7px;
  padding: 10px;
}

.logo-wrapper{
  display:flex;
  justify-content: center;
}

.logo{
  height: 70%;
  width: 90%;
  border-radius: 5px;
}

.name-and-grade{
  display: flex;
  justify-content: flex-start;
  align-items: center;
  padding: 10px 0;
  gap: 20px;
}

.name{
  font-size: 24px;
}

.grade{
  background-color: #ff7810;
  padding: 5px;
  border-radius: 10px;
  color: #fff;
  font-size: 18px;
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