<script setup>
import SportObject from "@/components/objects/SportObject.vue"
</script>
<template>

  <div class="card-wrapper">
    <SportObject :sportObjectChild="sportObject"></SportObject>
  </div>

</template>

<script>
export default {
  name: "ObjectSliderItem",
  props: ['sportObject']
}
</script>

<style scoped>
.card-wrapper {
  height: 100%;
}
</style>