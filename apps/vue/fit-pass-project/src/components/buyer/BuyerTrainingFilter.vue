<script setup>
import TemplateFilter from "../filters/TemplateFilter.vue";
import { useTrainingStore } from "@/stores/training-store"
</script>
<template>
  <TemplateFilter :searchItems="searchItems" :filterItems="filterItems" :sortItems="sortItems"
    :from-to-items="fromToItems" :fromToDateItems="fromToDateItems" :service="service" />
</template>

<script>
export default {
  name: "BuyerTrainingFilter",
  created: async function () {
    let trainingStore = useTrainingStore()
    this.service = async (params) => { await trainingStore.filterBuyerTrainings(params) }
  },
  data: function () {
    return {
      searchItems: [
        {
          field: "object",
          placeholder: "Search by object name"
        }
      ],
      filterItems: [
        {
          field: "objectType",
          items: ["Choose object type", "POOL", "GYM", "OTHER"],
          emptyElement: "Choose object type"
        },
        {
          field: "trType",
          items: ["Choose training type", "GROUP_TRAINING", "PERSONAL_TRAINING", "OTHER"],
          emptyElement: "Choose training type"
        }
      ],
      sortItems: [
        {
          field: "sort",
          items: ["Choose sort", "name", "price", "date"],
          emptyElement: "Choose sort"
        }
      ],
      fromToItems: [
        {
          fieldMin: "priceFrom",
          fieldMax: "priceTo",
          minimum: 0,
          maximum: 20000
        }
      ],
      fromToDateItems: [
        {
          fieldFrom: "dateFrom",
          fieldTo: "dateTo",
          placeholderFrom: "Start Date",
          placeholderTo: "End Date"
        }
      ],
      service: (params) => {
        console.log(params)
      }
    }
  }
}
</script>

<style scoped>
</style>