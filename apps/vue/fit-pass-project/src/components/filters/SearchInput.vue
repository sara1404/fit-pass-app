<template>
  <div class="search-input" :style="customStyle">
    <input type="text" :placeholder="placeholder" v-model="val" @change="$emit('searched', val)">
    <img src="../../assets/imgs/search-icon.png" height="24" width="24">
  </div>
</template>

<script>
export default {
  name: "SearchInput",
  props: {
    customStyle: {
      type: Object,
      default: {}
    },
    placeholder: "",
    val: ""
  }
}
</script>

<style scoped>

  .search-input {
    display: flex;
    align-items: center;
    border-radius: 10px;
    border: 1px solid lightgray;
  }
  .search-input input {
    height: 2.5rem;
    min-width: 200px;
    padding-left: 5px;
    font-size: 1rem;
    border: none;
    outline: none;
    border-radius: 10px;
  }

  .search-input input:focus {
    outline: none;
  }
</style>