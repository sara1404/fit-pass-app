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
    min-width: 20vw;
    max-width: 20vw;
    height: 100%;
  }

  .object-wrapper {
    width: 60%;
    box-shadow: 0 0;
    border: 1px solid darkgray;
  }

  .object-wrapper:hover {
    transform: scale(1);
    transition-duration: 0ms;
    box-shadow: 0 0;

  }


</style>