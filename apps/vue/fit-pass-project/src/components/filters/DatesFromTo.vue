<template>
  <div class="dates-from-to-wrapper">
    <div class="from-wrapper date-wrapper" >
      <label for="">From:</label>
      <input type="date" class="dateFrom" v-model="dateFrom" :placeholder="placeholderFrom" @change="emitValues">
    </div>
    <div class="to-wrapper date-wrapper">
      <label for="">To:</label>
      <input type="date" class="dateTo" v-model="dateTo" :placeholder="placeholderTo" @change="emitValues">
    </div>
  </div>

</template>

<script>
export default {
  name: "DatesFromTo",
  props: {
    placeholderFrom: "",
    placeholderTo: ""
  },
  data: function() {
    return {
      dateFrom: null,
      dateTo: null
    }
  },
  methods: {
    emitValues: function() {
      this.$emit('changed', {
        dateFrom: this.dateFrom,
        dateTo: this.dateTo
      })
    }
  }
}
</script>

<style scoped>
  .dates-from-to-wrapper {
    min-width: 200px;
    display: flex;
    flex-direction: column;
    gap: 10px;
  }

  .dates-from-to-wrapper label {
    font-weight: bold;
    font-family: Calibri;
    font-size: 1rem;
  }
  .dates-from-to-wrapper input {
    width: 70%;
    height: 30px;
    font-size: 1rem;
    border-radius: 5px;
    border: 1px solid lightgray;
    padding-left: 5px;
  }

  .dates-from-to-wrapper input:focus {
    outline: none;
  }

  .date-wrapper {
    display: flex;
    align-items: center;
    justify-content: space-between;
  }
</style>