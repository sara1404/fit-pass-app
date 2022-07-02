<template>
  <select name="" id="" v-model="val" @change="$emit('filtered', val)">
    <option :value="item" v-for="item in items" :key="index">
        {{item}}
    </option>
  </select>
</template>

<script>
export default {
  name: "ReactiveCombo",
  props: {
    customStyle: {},
    items: {
      type: Array(String),
      default: []
    },
    val: ""
  },
}
</script>

<style scoped>
  select {
    min-width: 300px;
    height: 2.5rem;
    text-align: center;
    font-size: 1rem;
  }
</style>