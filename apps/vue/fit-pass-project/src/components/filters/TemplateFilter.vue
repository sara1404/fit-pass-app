<script setup>
import FilterContainer from "./FilterContainer.vue";
import SearchInput from "./SearchInput.vue";
import ReactiveCombo from "./ReactiveCombo.vue";
import DualRangeSlider from "../custom/DualRangeSlider.vue";
import DatesFromTo from "./DatesFromTo.vue";
</script>

<template>
  <FilterContainer @sort-direction="(val) => { params.sortType = val; filter() }">

    <template v-slot:search-slot>
      <SearchInput v-for="{ field, placeholder} in searchItems"
        @searched="(val) => { params[field] = val; filter()}"
        :placeholder="placeholder"
      />
    </template>

    <template v-slot:from-to-slot>
      <DualRangeSlider v-for="{fieldMin, fieldMax, minimum, maximum} in fromToItems"
                       @changed="(val) => {params[fieldMin] = val.minValue; params[fieldMax] = val.maxValue; filter()}"
                       :minimum="minimum"
                       :maximum="maximum"
      />
    </template>

    <template v-slot:from-to-date-slot>
        <DatesFromTo v-for="{fieldFrom, fieldTo, placeholderFrom, placeholderTo} in fromToDateItems"
                     @changed="(val) => {params[fieldFrom] = val.dateFrom; params[fieldTo] = val.dateTo; filter();}"
                     :placeholderFrom="placeholderFrom"
                     :placehoderTo="placeholderTo"
        />
    </template>

    <template v-slot:filter-slot>
      <ReactiveCombo v-for="{items, field, emptyElement, customStyle} in filterItems"
                     :items="items"
                     :empty-element="emptyElement"
                     :custom-style="customStyle"
                     @filtered="(val) => { params[field] = val; filter()}"
      />
    </template>

    <template v-slot:sort-slot>
      <ReactiveCombo v-for="{items, field, emptyElement, customStyle} in sortItems"
                     :items="items"
                     :empty-element="emptyElement"
                     :custom-style="customStyle"
                     @filtered="(val) => { params[field] = val; filter()}"

      />
    </template>

  </FilterContainer>

</template>

<script>
// import {userFilterService} from "../../services/userFilterService";

export default {
  name: "TemplateFilter",
  props: {
    searchItems: [],
    sortItems: [],
    filterItems: [],
    fromToItems: [],
    fromToDateItems: [],
    service: null
  },
  data: function() {
    return {
      params: {}
    }
  },
  methods: {
    async filter() {
      if(this.service == null) return;
      this.service(this.params);
    }
  }
}
</script>

<style scoped>

</style>