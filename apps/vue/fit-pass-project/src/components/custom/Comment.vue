<template>
    <div class="wrapper">
        <div class="header">
            <div class="grade">
                {{comment.grade}}
            </div>
            <div class="username">
                {{comment.buyerUsername}}
            </div>
        </div>
        <div class="comment-content">
            "{{comment.comment}}"
        </div>        
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
.wrapper{
    display: flex;
    flex-direction: column;
    min-height: 4rem;
    justify-content: space-around;
    align-items: flex-start;
    border-bottom: 1px solid lightgray;
    padding: .5rem;
}

.header{
    display: flex;
    gap: .7rem;
}

.grade{
    background-color: #ff7810;
    padding: 3px 7px;
    color: #fff;
    border-radius: 7px;
}

.username{
    font-size: 1.2rem;
    color: rgb(104, 104, 104);
}

.comment-content{
    color: rgb(99, 99, 99);
}
</style>