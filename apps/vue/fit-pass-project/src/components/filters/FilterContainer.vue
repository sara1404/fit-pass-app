<script setup>
import SortButtons from "./SortButtons.vue";
</script>
<template>
    <div class="filter-container">
      <div class="search-wrapper wrapper">
          <slot name="search-slot"></slot>
      </div>
      <div class="filter-wrapper wrapper">
        <slot name="filter-slot"></slot>
      </div>
      <div class="sort-wrapper wrapper">
        <slot name="sort-slot"></slot>
        <SortButtons @sort-direction="(val) => this.$emit('sort-direction', val)"/>
      </div>
      <div class="show-only wrapper">
        <slot name="show-only-slot"></slot>
      </div>
    </div>
</template>

<script>
export default {
  name: "FilterContainer"
}
</script>

<style scoped>
.filter-container {
  height: 7vh;
  width: 100%;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
  outline: 1px solid gray;
}

.wrapper {
  display: flex;
  gap: 10px;
}
</style>