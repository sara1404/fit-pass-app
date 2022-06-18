<script setup>
import axios from 'axios'
import { sportObjectsStore } from "@/stores/objects-store.js"
import ButtonSwitch from "@/components/custom/ButtonSwitch.vue";
import Sort from "@/components/custom/Sort.vue"
import {defineComponent} from "vue";

defineComponent(ButtonSwitch)
defineComponent(Sort)
</script>

<template>
    <div class="container">
      <div class="search-wrapper">
        <div class="name-filter-wrapper">
            <input v-model="searchText" @change="refreshObjects" class="name-filter" type="text" placeholder="Search by name">
            <img src="../../assets/imgs/search-icon.png">
        </div>
        <div class="city-filter-wrapper filter-wrapper">
          <select v-model="selectedCity" @change="refreshObjects" name="cities" class="filter">
            <option v-for="city in cities" v-bind:key="city" :value="city">
              {{city}}
            </option>
          </select>
        </div>
      <div class="country-filter-wrapper filter-wrapper">
        <select v-model="selectedCountry" @change="refreshObjects" class="filter">
          <option v-for="country in countries" v-bind:key="country" :value="country">
            {{country}}
          </option>
        </select>
      </div>
      <div class="type-filter-wrapper filter-wrapper">
        <select v-model="selectedType" @change="refreshObjects" class="filter">
          <option v-for="type in types" v-bind:key="type" :value="type">
            {{type}}
          </option>
        </select>
      </div>
      </div>
        
      <div class="filter-and-sort-wrapper">
        <div class="sort-wrapper">
          <label for="">Sort:</label>
          <Sort @sort='refreshObjectsWithSort' @descending="descendingSort" @ascending="ascendingSort"/>
        </div>
        <div class="button-switch-wrapper">
          <label for="">Show only open:</label>
          <ButtonSwitch :isOpenOnly="isOpenOnly" @openOnly="refreshOnlyOpen" />
        </div>
      </div>
    </div>

</template>

<script>
export default {
  name: "Filter",
  data: function(){
      return {
        cities: ["Choose city", "Beograd", "Novi Sad", "Kragujevac", "Uzice", "Kraljevo"],
        types: ["Choose discipline", "GYM", "POOL", "SAUNA"],
        countries: ["Choose country", "Serbia", "Croatia", "Bosnia And Herzegovina"],
        selectedCity: "Choose city",
        selectedCountry: "Choose country",
        selectedType: "Choose discipline",
        searchText: "",
        selectedAverageMark : "",
        isOpenOnly: false,
        sort: null,
        sortType: null,
        objectsStore: null
      }
  },
  mounted: function() {
    this.objectsStore = sportObjectsStore();
  },
  methods: {
    ascendingSort: function(){
        this.sortType = "ascending"
        this.refreshObjects()
    },
    descendingSort: function(){
        this.sortType = "descending"
        this.refreshObjects()
    },
    refreshObjectsWithSort: function(sort){
      this.sort = sort
      console.log(sort)
      this.refreshObjects()
    },
    refreshOnlyOpen: function(value) {
      this.isOpenOnly = value
      this.refreshObjects()
    },
    refreshObjects: async function() {
      this.objectsStore.getSportObjects({
        name: this.searchText,
        city: this.selectedCity,
        type: this.selectedType,
        country: this.selectedCountry,
        status: this.isOpenOnly,
        sort: this.sort,
        sortType: this.sortType
      })
       
    }
  }
}
</script>

<style scoped>
@import "@/assets/base.css";
.container{
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 100%;
    height: 7vh;
    border-bottom: 1px solid lightgray;
}

.search-wrapper{
    display: flex;
    align-items: center;
    width: 70%;
    height: 7vh;
    border-bottom: 1px solid lightgray;
    gap: 10px;
}

.filter-and-sort-wrapper{
    display: flex;
    align-items: center;
    padding-right: 2em;
}

.sort-wrapper{
  display: flex;
  align-items: center;
  gap: 0.5em;
}

.button-switch-wrapper{
  display: flex;
  gap: 0.5em;
}

.name-filter-wrapper {
    height: 37.5px;
    width: 200px;
    display: flex;
    align-items: center;
    border: 1px solid lightgray;
    border-radius: 10px;
    margin-left: 1.7em;
    padding: 0 7px;
}

.filter-wrapper {
  height: 40px;
  width: 200px;
  border-radius: 10px;
  overflow: hidden;
  cursor: pointer;
}

.filter {
  height: 100%;
  width: 100%;
  border: 1px solid lightgray;
  background-color: transparent;
  resize: none;
  outline: none;
  text-align: center;
  border-radius: 10px;
  cursor: pointer;
}

.filter:hover {
  border-radius: 10px;
}

.filter option {
  height: 30px;
}

.name-filter{
    height: 35px;
    width: 100%;
    outline: none;
    border: none;
    border-radius: 10px;
}
</style>