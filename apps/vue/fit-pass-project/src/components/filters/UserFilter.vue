<script setup>
import FilterContainer from "./FilterContainer.vue";
import SearchInput from "./SearchInput.vue";
import ReactiveCombo from "./ReactiveCombo.vue";
</script>

<template>
  <FilterContainer @sort-direction="(val) => { params.sortType = val; filter() }">
    <template v-slot:search-slot>
        <SearchInput
          @searched="(val) => { params.name = val; filter() }"
          placeholder="Type users name!"
        />
        <SearchInput
            @searched="(val) => { params.surname = val; filter()}"
            placeholder="Type users surname!"
        />
        <SearchInput
            @searched="(val) => { params.username = val; filter() }"
            placeholder="Type users username!"
        />
    </template>
    <template v-slot:filter-slot>
      <ReactiveCombo
        :items="this.roles"
        @filtered="(val) => { params.role = val; filter()}"
      />
    </template>

  </FilterContainer>

</template>

<script>
import {userFilterService} from "../../services/userFilterService";

export default {
  name: "UserFilter",
  data: function() {
    return {
    params: {
      name: "",
      surname: "",
      username: "",
      role: "",
    },
    roles: ["ADMIN", "MANAGER", "BUYER", "COACH"]
    }
  },
  methods: {
    async filter() {
      let data = await userFilterService.fetchFilteredData(this.params)
      console.log(data);
    }
  }
}
</script>

<style scoped>

</style>