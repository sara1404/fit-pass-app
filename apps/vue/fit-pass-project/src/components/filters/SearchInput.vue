<template>
  <div class="search-input" :style="customStyle">
    <input type="text" :placeholder="placeholder" v-model="val" @change="$emit('searched', val)">
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
  .search-input input {
    height: 2.5rem;
    min-width: 300px;
    padding-left: 5px;
    font-size: 1rem;
  }
</style>