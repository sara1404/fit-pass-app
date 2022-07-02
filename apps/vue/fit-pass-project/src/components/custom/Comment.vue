<template>
    <div class="container">
        <div class="header">
            <div class="grade">

            </div>
            <div class="date">

            </div>
        </div>
        <div class="comment-content"></div>        
    </div>    
</template>

<script>
export default {
    name:"Comment",
    props:{
        comment: Object,
    }, 
    data:function(){
        return{

        }
    }
   
}
</script>

<style scoped>
@import "@/assets/base.css";
.container{
    display: flex;
    flex-direction: column;
}

.header{
    display: flex;
}
</style>