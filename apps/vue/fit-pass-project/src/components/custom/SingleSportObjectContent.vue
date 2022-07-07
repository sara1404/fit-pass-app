<template>
    <div class="content-wrapper-single">
        <div v-for="content in sportObject.content" :key="content.name" class="objContent">
            {{content.name}}
        </div>
    </div>
</template>

<script>
export default {
    name:"SingleSportObjectContent",
    props:{
        sportObject:{}
    },
    data: function(){
        return{

        }
    },
}
</script>

<style scoped>
.content-wrapper-single{
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

</style>