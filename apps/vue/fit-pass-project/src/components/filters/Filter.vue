<script setup>

</script>

<template>
    <div class="container">
        <div class="name-filter-wrapper">
            <input class="name-filter" type="text" placeholder="Search by name">
            <img src="../../assets/imgs/search-icon.png">
        </div>
        <div class="city-filter-wrapper">
            <input class="city-filter" type="text" placeholder="Search by city">
        </div>
    </div>
</template>

<script>
export default {
  name: "Filter",
  data: function(){
      return{
      }
  }
}
</script>

<style scoped>
@import "@/assets/base.css";
.container{
    display: flex;
    align-items: center;
    width: 100%;
    height: 7vh;
    border-bottom: 1px solid lightgray;
}

.name-filter-wrapper, .city-filter-wrapper{
    height: 70%;
    width: 200px;
    display: flex;
    align-items: center;
    border: 1px solid lightgray;
    border-radius: 15px;
    margin-left: 1.7em;
    padding: 0 7px;

}

.name-filter{
    height: 35px;
    width: 100%;
    outline: none;
    border: none;
    border-radius: 15px;
}
</style>