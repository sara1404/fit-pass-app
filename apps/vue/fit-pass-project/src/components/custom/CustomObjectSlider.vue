<template>
    <div class="slider-container">
        <div class="btn-left">
            <img @click="moveLeft" src="../../assets/imgs/left-arrow.png" />
        </div>
        <div class="btn-right">
            <img @click="moveRight" src="../../assets/imgs/right-arrow.png" />
        </div>
        <div ref="sliderContent" class="slide-content">
            <ObjectSliderItem v-for="(item, index) in sportObjects" :key="index" ref="cards" :sportObject="item" />
        </div>
    </div>

</template>



<script>
import { mapState } from 'pinia'
import { sportObjectsStore } from '../../stores/objects-store'
import ObjectSliderItem from './ObjectSliderItem.vue'
export default {
    name: "CustomObjectSlider",
    components: { ObjectSliderItem },
    created: function () {
        let objectsStore = sportObjectsStore()
        objectsStore.getSportObjects()

    },

    mounted: function () {
    },
    computed: {
        ...mapState(sportObjectsStore, ['sportObjects'])
    },
    data: function () {
        return {
            elementIndex: 0,
            slided: 0
        }
    },
    methods: {
        moveRight: function () {
            let cards = document.querySelectorAll('.card-wrapper')
            if (this.elementIndex == 0) return;
            let firstCard = cards[this.elementIndex]

            if (this.elementIndex == cards.length - 1)
                this.slided += cards[this.elementIndex - 1].clientWidth
            else
                this.slided += Math.abs(cards[this.elementIndex - 1].getBoundingClientRect().left) + firstCard.getBoundingClientRect().left
            this.$refs.sliderContent.style.transform = `translateX(${this.slided}px)`;
            this.elementIndex -= 1
        },
        moveLeft: function () {
            let cards = document.querySelectorAll('.card-wrapper')
            if (this.elementIndex == cards.length - 1) return;
            let firstCard = cards[this.elementIndex]

            if (this.elementIndex == 0)
                this.slided -= firstCard.offsetLeft + firstCard.clientWidth
            else
                this.slided = -(firstCard.offsetLeft + firstCard.clientWidth)
            this.$refs.sliderContent.style.transform = `translateX(${this.slided}px)`;
            this.elementIndex += 1
        }
    }

}

</script>


<style scoped>
.slider-container {
    position: relative;
    max-width: 100%;
    min-width: 100%;
    min-height: 300px;
    position: relative;
    overflow-x: hidden;
    padding-top: 2rem;
    padding-bottom: 2rem;
    margin-left: 1rem;
}

.slide-content {
    display: flex;
    column-gap: 10px;
    transition: transform 0.4s ease-out;
    margin-left: 1px;
}

.card-wrapper {
    padding: 10px;
}


.btn-left,
.btn-right {
    display: flex;
    flex: 1;
    min-height: 32px;
    min-width: 32px;
    position: absolute;
    z-index: 999999999;
    cursor: pointer;
}

.btn-left img,
.btn-right img {
    width: 100%;
    height: 100%;
}

.btn-left {
    top: 50%;
}

.btn-right {
    top: 50%;
    right: 1rem;
}
</style>