<template>
  <TemplateFilter
    :searchItems="searchItems"
    :filterItems="filterItems"
    :sortItems="sortItems"
    :service="service"
  />
</template>

<script>
import TemplateFilter from "./TemplateFilter.vue";
import {useProfileStore} from "../../stores/profile-store";

export default {
  name: "UserFilter",
  components: {TemplateFilter},
  created: function() {
    let userProfileStore = useProfileStore();
    this.service = async (params) => {
      await userProfileStore.fetchFilteredData(params);
    }
    },
  data: function() {
    return {
      searchItems: [
        {
          field: "name",
          placeholder: "Search by name"
        },
        {
          field: "surname",
          placeholder: "Search by surname"
        },
        {
          field: "username",
          placeholder: "Search by username"
        }
      ],
      filterItems: [
        {
          field: "role",
          items: ["Choose role", "ADMIN", "MANAGER", "BUYER", "COACH"],
          emptyElement: "Choose role"
        }
      ],
      sortItems: [
        {
          field: "sort",
          items: ["Choose sort", "name", "surname", "username", "points"],
          emptyElement: "Choose sort"
        }
      ],
      service: null
    }
  }
}
</script>

<style scoped>

</style>