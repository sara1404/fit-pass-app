<script setup>
import Slider from "../../../node_modules/@vueform/slider"
</script>

<template>
  <div class="dual-range-slider">
    <label class="slider-header" for="">{{label}}</label>
    <Slider style="min-width: 15rem; --slider-connect-bg:orange; --slider-tooltip-bg:orange" v-model="value"
      :min="minimum" :max="maximum" :showTooltip="'drag'" @change="updateValues" />
  </div>

</template>
<script>
export default {
  name: "DualRangeSlider",
  props: {
    minimum: {
      type: Number,
      default: 1000
    },
    maximum: {
      type: Number,
      default: 100000
    },
    step: {
      type: Number,
      default: 1000
    },
    label : {
      type: String,
      default: "Price"
    }
  },
  data: function () {
    return {
      value: [this.minimum, this.maximum],
      barMinValue: 10,
      barMaxValue: 90
    }
  },
  methods: {
    updateValues(val) {
      this.$emit("changed", { minValue: val[0], maxValue: val[1] })
    }
  }
}
</script>

<style src="@vueform/slider/themes/default.css">
</style>

<style scoped>

.dual-range-slider {
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  gap: 1rem;
}
.slider-header {
  text-align: center;
  font-size: 1rem;
  font-weight: bold;
  min-width: 100%;
}
</style>