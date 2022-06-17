<script setup>
import { sportObjectsStore } from "@/stores/objects-store.js"
import {mapState} from "pinia";
import ObjectSliderItem from "@/components/custom/ObjectSliderItem.vue"
</script>

<template>
  <div class="objects-slider-wrapper">
    <button class="left-slider" @click="moveSliderLeft" :disabled=leftSliderDisabled>
      Left
    </button>
    <div class="objects-slider" ref="objectsSlider" >
      <ObjectSliderItem v-for="sportObject in sportObjects" :sportObject="sportObject"></ObjectSliderItem>
    </div>
    <button class="right-slider" @click="moveSliderRight" :disabled=rightSliderDisabled>
      Right
    </button>
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
      if(this.getTranslateX() - 25 < -(this.sportObjects.length - 4) * 25) return
      this.$refs.objectsSlider.style.transform = `translateX(${this.getTranslateX() - 25}vw)`
      this.toggleSliderButtonsDisabled()
    },
    moveSliderRight: function() {
      if(this.getTranslateX() + 25 > 0) return;
      this.$refs.objectsSlider.style.transform = `translateX(${this.getTranslateX()  + 25}vw)`
      this.toggleSliderButtonsDisabled()
    },
    getTranslateX: function () {
      try {
        let style = window.getComputedStyle(this.$refs.objectsSlider);
        let matrix = new WebKitCSSMatrix(style.transform);
        return (matrix.m41/window.innerWidth) * 100
      } catch(e) {
        return 0
      }
    },
    toggleSliderButtonsDisabled() {
      this.rightSliderDisabled = this.getTranslateX() + 25 > 0
      this.leftSliderDisabled = this.getTranslateX() - 25 < -(this.sportObjects.length - 4) * 25
    }
  }
}
</script>

<style scoped>

.objects-slider-wrapper {
  margin-top: 100px;
  position: relative;
  max-width: 100vw;
  overflow: hidden;
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

}

.right-slider {
  position: absolute;
  top: 50%;
  right: 10px;
  z-index: 100;
}

button:disabled {
  background: orange;
}
</style>