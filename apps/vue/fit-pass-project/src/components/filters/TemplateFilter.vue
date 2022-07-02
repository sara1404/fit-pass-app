<script setup>
import FilterContainer from "./FilterContainer.vue";
import SearchInput from "./SearchInput.vue";
import ReactiveCombo from "./ReactiveCombo.vue";
</script>

<template>
  <FilterContainer @sort-direction="(val) => { params.sortType = val; filter() }">

    <template v-slot:search-slot>
      <SearchInput v-for="{ field, placeholder} in searchItems"
        @searched="(val) => { params[field] = val; filter()}"
        :placeholder="placeholder"
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
      // await this.service.fetchFilteredData(this.params)
    }
  }
}
</script>

<style scoped>

</style>