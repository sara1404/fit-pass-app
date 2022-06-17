<script setup>
import SportObject from "@/components/objects/SportObject.vue"
</script>
<template>

  <span class="object-slider-item">
    <SportObject :sportObjectChild="sportObject"></SportObject>
  </span>

</template>

<script>
export default {
  name: "ObjectSliderItem",
  props: ['sportObject']
}
</script>

<style scoped>
  .object-slider-item {
    display: flex;
    align-items: center;
    justify-content: center;
    min-width: 25vw;
    max-width: 25vw;
    height: 100%;
  }

  .object-wrapper {
    width: 100%;
  }
</style>