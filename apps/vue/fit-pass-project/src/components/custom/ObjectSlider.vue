<script setup>
import { sportObjectsStore } from "@/stores/objects-store.js"
import {mapState} from "pinia";
import ObjectSliderItem from "@/components/custom/ObjectSliderItem.vue"
</script>

<template>
  <div class="objects-slider-wrapper">
      <img class="left-slider" src="../../assets/imgs/slider-left-black.ico" height="48" width="48" alt="" :disabled=leftSliderDisabled @click="moveSliderLeft">
    <div class="objects-slider" ref="objectsSlider" >
      <ObjectSliderItem v-for="sportObject in sportObjects" :sportObject="sportObject"></ObjectSliderItem>
    </div>
      <img class="right-slider" src="../../assets/imgs/slider-right-black.ico" height="48" width="48" @click="moveSliderRight" :disabled=rightSliderDisabled alt="">
  </div>
</template>

<script>


export default {
  name: "ObjectSlider",
  data: function() {
    return {
      objectsStore: null,
      leftSliderDisabled: false,
      rightSliderDisabled: true
    }
  },
  computed: {
    ...mapState(sportObjectsStore, ['sportObjects'])
  },
  mounted: function() {
    this.objectsStore = sportObjectsStore();
    this.objectsStore.getSportObjects()
  },
  methods: {
    moveSliderLeft: function() {
      if(this.getTranslateX() - 20 < -(this.sportObjects.length - 5) * 20) return
      this.$refs.objectsSlider.style.transform = `translateX(${this.getTranslateX() - 20}vw)`
      this.toggleSliderButtonsDisabled()
    },
    moveSliderRight: function() {
      if(this.getTranslateX() + 20 > 0) return;
      this.$refs.objectsSlider.style.transform = `translateX(${this.getTranslateX()  + 20}vw)`
      this.toggleSliderButtonsDisabled()
    },
    getTranslateX: function () {
      try {
        let style = window.getComputedStyle(this.$refs.objectsSlider);
        let matrix = new WebKitCSSMatrix(style.transform);
        return (matrix.m51/window.innerWidth) * 100
      } catch(e) {
        return 0
      }
    },
    toggleSliderButtonsDisabled() {
      this.rightSliderDisabled = this.getTranslateX() + 20 > 0
      this.leftSliderDisabled = this.getTranslateX() - 20 < -(this.sportObjects.length - 5) * 20
    }
  }
}
</script>

<style scoped>

.objects-slider-wrapper {
  margin: 100px 0;
  position: relative;
  max-width: 100vw;
  overflow-x: hidden;
  overflow-y: auto;
}
.objects-slider {
  display: flex;
  max-width: 100%;
  transition: transform 1500ms;
}

.objects-slider::-webkit-scrollbar{
  display: none;
}

.left-slider {
  position: absolute;
  top: 50%;
  left: 10px;
  z-index: 100;
  cursor: pointer;
}

.right-slider {
  position: absolute;
  top: 50%;
  right: 10px;
  z-index: 100;
  cursor: pointer;
}

button:disabled {
  background: orange;
}
</style>