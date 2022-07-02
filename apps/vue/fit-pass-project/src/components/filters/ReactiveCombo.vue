<template>
  <select name="" id="" v-model="val" @change="emitChange" :style="customStyle">
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
    emptyElement: ""
  },
  data: function() {
    return {
      val: ""
    }
  },
  mounted: function() {
    if(this.items.length === 0) return;
    this.val = this.items[0]
  },
  methods: {
    emitChange: function() {
      if(this.val === this.emptyElement) {
        this.$emit('filtered', "")
        return;
      }
      this.$emit('filtered', this.val)
    }
  }
}
</script>

<style scoped>
  select {
    min-width: 200px;
    height: 2.5rem;
    text-align: center;
    font-size: 1rem;
    outline: none;
    border-radius: 10px;
    cursor: pointer;
    border: 1px solid lightgray;

  }

  select option {
    height: 1.8rem;
  }
</style>