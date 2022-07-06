<template>
    <TemplateFilter :search-items="searchItems" :filter-items="filterItems" :sort-items="sortItems"
        :from-to-items="fromToItems" :from-to-date-items="fromToDateItems" :service="service" />


</template>


<script>
import { useTrainingStore } from '../../stores/training-store'
import TemplateFilter from '../filters/TemplateFilter.vue'

export default {
    name: "CoachTrainingFilter",
    components: {
        TemplateFilter
    },
    created: function () {
        let trainingStore = useTrainingStore()
        this.service = async (params) => { await trainingStore.getCoachTrainingHistory(params) }
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
            service: null
        }
    }
}
</script>