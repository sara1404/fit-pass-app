<template>
    <div class="work-time-wrapper">
        <div class="title">Work time</div>
        <div v-for="workTime in this.objectsWorkTime" :key="workTime.day" class="single-work-time">
            <label for="">{{workTime.day}}</label>
            <label for="">{{this.formatWorkTime(workTime.time.starts) + " - " + this.formatWorkTime(workTime.time.ends)}}</label>
        </div>
    </div>
</template>

<script>
export default {
  name: "SportObjectWorkTime",
  props:{
    objectsWorkTime: [],
  },
methods: {
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
.work-time-wrapper{
    position: absolute;
    bottom: 12%;
    right: 8%;
    z-index: 100000;
    background-color: #616161;
    padding: 10px;
    border-radius: 10px;
    color: #fff;
}

.title{
    display: flex;
    justify-content: center;
    font-size: 18px;
    padding-bottom: 5px;
}

.single-work-time{
    display: flex;
    justify-content: space-between;
    width: 190px;
    border-bottom: 1px solid hsla(0,0%,59.2%,.11);
    padding: 3px 0;
}
</style>