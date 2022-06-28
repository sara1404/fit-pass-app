<template>
    <div class="container" @mouseenter="displayEditBtn" @mouseleave="displayEditBtn">
        <div class="edit-btn">
            <img src="../../assets/imgs/edit.png" alt="" height="23" width="23" v-show="hovered && contentData.flag==='training'" @click="$emit('displayEditForm', contentData)">
        </div>
        <section class="info">
            <label for="" class="name">{{contentData.name}}</label>
            <label for="" class="type">{{contentData.type}}</label>
        </section>
        <div class="pic">
            <img v-bind:src="contentData.pictureUrl" alt="" height="150" width="150">
        </div>
    </div>
</template>

<script>
export default {
    name: "SportObjectContent",
    props: {
    contentData: Object
    },
    data: function(){
        return{
            hovered: false,
        }
    },
    methods:{
        displayEditBtn: function(){
            this.hovered = !this.hovered
        },
    }
}
</script>

<style scoped>
.container{
    display: flex;
    flex-direction: column;
    position: relative;
    border: 1px solid black;
    width: 180px;
}

.info{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    gap: .5rem;
    z-index: 1;
}

.name{
    font-size: 1.5rem;
}

.type{
    font-size: 1rem;
    color: rgb(116, 115, 115);
}

.pic{
    display: flex;
    justify-content: center;
}

.edit-btn{
    height: 25px;
    width: 25px;
    position: absolute;
    right: .8rem;
    cursor: pointer;
    z-index: 2000;
}

.edit-btn img{
    padding: 3px;
    border-radius: 7px;
}

.edit-btn img:hover{
    background-color: rgb(231, 229, 229);
    cursor: pointer;
}

</style>