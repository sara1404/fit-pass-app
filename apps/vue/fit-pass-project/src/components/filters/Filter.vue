<script setup>

</script>

<template>
    <div class="container">
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

</template>

<script>
export default {
  name: "Filter",
  data: function(){
      return {
        cities: ["Choose City", "Belgrade", "Novi Sad", "Kragujevac", "Uzice", "Kraljevo"],
        types: ["Choose Type", "GYM", "POOL", "SAUNA"],
        countries: ["Choose Country", "Serbia", "Croatia", "Bosnia And Herzegovina"],
        selectedCity: "Choose City",
        selectedCountry: "Choose Country",
        selectedType: "Choose Type",
        searchText: ""
      }
  },
  methods: {
    refreshObjects: function() {
      console.log(this.selectedType,this.selectedCity, this.selectedCountry, this.searchText)
    }
  }
}
</script>

<style scoped>
@import "@/assets/base.css";
.container{
    display: flex;
    align-items: center;
    width: 100%;
    height: 7vh;
    border-bottom: 1px solid lightgray;
    gap: 10px;
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