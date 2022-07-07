<template>
    <div class="basic-info">
        <div>
            <img v-bind:src="sportObject.logoUrl" alt="" height="100" width="100">
        </div>
        <div class="name-grade-wrapper">
            <label class="title" for="">{{sportObject.name + " - " + sportObject.type}}</label>
            <div class="grade-wrapper">
            <label for="">Grade: </label>
            <label class="grade" for="">{{sportObject.averageMark}} </label>
            <label for="">{{sportObject.status}}</label>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: "SportObjectInfo",
    props:{
        sportObject: {}
    },
    data: function(){
        return{

        }
    }
}
</script>

<style scoped>

.basic-info{
  display: flex;
  align-items: center;
  gap: 1rem;
  margin-bottom: 1rem;
  width: 60%;
}

.name-grade-wrapper{
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: .8rem;
}

.title{
  font-size: 1.8rem;
  font-weight: 500;
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



</style>