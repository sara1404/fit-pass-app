<template>
  <div class="schedule-wrapper">
    <div class="schedule-header">
      <img src="../../assets/imgs/left-arrow.png" alt="" @click="displayContentDayBefore">
      <div class="date-input">{{ chosenDate }}</div>
      <img src="../../assets/imgs/right-arrow.png" alt="" @click="displayContentDayAfter">
    </div>
    <div class="schedule-content">
      <div class="single-content" v-for="content in sportObject.content" :key="content.name">
        <div class="content-name">
          <img v-bind:src="content.pictureUrl" alt="" height="75" width="75">
          <div class="training-info">
            <label for="">{{ content.name }}</label>
            <label for="" v-show="content.flag === 'training'">{{ content.description }}</label>
            <label for="" v-show="content.flag === 'training'">{{ content.coachUsername }}</label>
          </div>
          <label for="" v-show="content.flag === 'training'">{{ content.appointments }}</label>
        </div>
        <label for="">{{ displayAdditionalFees(content) }}</label>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "SportObjectSchedule",
  props: {
    sportObject: {
      content: {
        type: Array,
        default: []
      }
    }
  },
  data: function () {
    return {
      chosenDate: this.getNow(),
    }
  },
  methods: {
    getNow: function () {
      let now = Date.now()
      let today = new Date(now);
      return today.toDateString()
    },

    displayContentDayBefore: function () {
      let date = new Date(this.chosenDate);
      date.setDate(date.getDate() - 1);
      this.chosenDate = date.toDateString()
    },

    displayContentDayAfter: function () {
      let date = new Date(this.chosenDate);
      date.setDate(date.getDate() + 1);
      this.chosenDate = date.toDateString()
    },

    mapNumToDay: function (num) {
      switch (num) {
        case 0: return "Sunday"
        case 1: return "Monday"
        case 2: return "Tuesday"
        case 3: return "Wednesday"
        case 4: return "Thursday"
        case 5: return "Friday"
        case 6: return "Saturday"
      }
    },

    displayAdditionalFees: function (content) {
      if (content.type === "OTHER")
        return "WITHOUT ADDITIONAL PAY"
      return content.price
    },
  },
}
</script>

<style scoped>
.schedule-wrapper {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  width: 60%;
}

.schedule-header {
  display: flex;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 3rem;
  gap: 10px;
}

.date-input {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 2rem;
  width: 70%;
  border: 1px solid lightgray;
  border-radius: 1rem;
  font-size: 1.2rem;
}

.schedule-header img {
  cursor: pointer;
}

.schedule-content {
  display: flex;
  flex-direction: column;
  width: 80%;
}

.content-name {
  display: flex;
  gap: 10px;
}

.single-content {
  display: flex;
  justify-content: space-between;
  padding: .5rem;
  border-bottom: 1px solid rgb(224, 224, 224);
  color: gray;
}

.training-info {
  display: flex;
  flex-direction: column;
  gap: 5px;
}
</style>