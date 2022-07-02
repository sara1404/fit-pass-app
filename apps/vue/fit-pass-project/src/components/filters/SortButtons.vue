<template>
  <div class="sort-direction">
    <img src="../../assets/imgs/ascending.png" alt="" height="26" width="26" @click="$emit('sort-direction', 'ascending')">
  </div>
  <div class="sort-direction" >
    <img src="../../assets/imgs/descending.png" alt="" height="26" width="26" @click="$emit('sort-direction', 'descending')">
  </div>
</template>

<script>
export default {
  name: "SortButtons"
}
</script>

<style scoped>
  .sort-direction {
    cursor: pointer;
  }
</style>