<script setup>

</script>


<template>
    <div class="type-filter-wrapper sort-wrapper">
        <select v-model="sort" v-on:change="$emit('sort', this.sort)" class="filter">
          <option v-for="sort in sorts" v-bind:key="sort" :value="sort">
            {{sort}}
          </option>
        </select>
        <div class="ascending">
          <img src="../../assets/imgs/ascending-sorting.png" alt="" height="30" width="30" @click="$emit('ascending')">
        </div>
        <div class="descending" >
          <img src="../../assets/imgs/descending-sorting.png" alt="" height="30" width="30" @click="$emit('descending')">
        </div>
    </div>
</template>

<script>
export default {
  name: "Sort",
  data: function(){
    return{
        sorts:['Name', 'Country', 'City', 'Mark'],
        sort : ""
    }
  },
  methods:{
    test:function(){
      console.log('e')
    }
  }
}

</script>

<style scoped>
@import "@/assets/base.css";
.sort-wrapper{
  height: 40px;
  width: 200px;
  border-radius: 10px;
  overflow: hidden;
  cursor: pointer;
}

.filter {
  height: 100%;
  width: 50%;
  border: 1px solid lightgray;
  background-color: transparent;
  resize: none;
  outline: none;
  text-align: center;
  border-radius: 10px;
  cursor: pointer;
}

.ascending, .descending{
  border: 1px solid gray;
  padding: 0 2px;
  border-radius: 7px;
}
</style>