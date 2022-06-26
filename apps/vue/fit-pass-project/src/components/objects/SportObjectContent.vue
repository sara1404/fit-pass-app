<template>
    <div class="container">
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
            
        }
    }
}
</script>

<style scoped>
.container{
    border: 1px solid black;
    width: 180px;
}

.info{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    gap: .5rem;
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
</style>