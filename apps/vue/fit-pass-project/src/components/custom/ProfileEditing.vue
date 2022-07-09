<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState } from "pinia"

</script>

<template>
  <section class="profile-with-edit">
    <MedalDisplay v-if="profile.role === 'BUYER'" :profile="profile" />
    <div class="profile">
      <div>
        <img v-if="profile.sex === 'MALE'" src="../../assets/imgs/avatar-man.jpg" alt="" height="150" width="150">
        <img v-else src="../../assets/imgs/avatar-woman.jpg" alt="" height="150" width="150">
      </div>
      <div class="user-info">
        <input type="text" :value="profile.username" disabled :class="edit ? 'postEdit' : 'preEdit'">
        <input ref="nameField" type="text" :value="profile.name" :disabled="!edit"
          :class="edit ? 'postEdit' : 'preEdit'">
        <input ref="surnameField" type="text" :value="profile.surname" :disabled="!edit"
          :class="edit ? 'postEdit' : 'preEdit'">
        <input ref="dateField" type="date" :value="profile.birthDate" :disabled="!edit"
          :class="edit ? 'postEdit' : 'preEdit'">
        <select ref="sexField" name="sex" id="" :value="profile.sex" :disabled="!edit"
          :class="edit ? 'postEdit' : 'preEdit'">
          <option value="FEMALE">FEMALE</option>
          <option value="MALE">MALE</option>
        </select>
      </div>
      <div class="editBtn">
        <img src="../../assets/imgs/edit.png" alt="" height="30" width="30" @click="displayEdit">
      </div>
    </div>

    <div class="btn-wrapper">
      <button class="save-edit-btn" v-show="edit" @click="editProfile">SAVE</button>
      <button class="canced-edit-btn" v-show="edit" @click="cancelEdit">CANCEL</button>
    </div>

  </section>
</template>

<script>
import MedalDisplay from "./MedalDisplay.vue";

export default {
  name: "ProfileEditing",
  components: {
    MedalDisplay
  },
  data: function () {
    return {
      edit: false,
      profileStore: null,
    }
  },
  computed: {
    ...mapState(useProfileStore, ['profile'])
  },
  mounted: function () {
    this.profileStore = useProfileStore()
  },
  methods: {
    displayEdit: function () {
      this.edit = true
    },
    cancelEdit: function () {
      this.edit = false
    },
    editProfile: function () {
      let body = {
        name: this.$refs.nameField.value,
        surname: this.$refs.surnameField.value,
        birthDate: this.$refs.dateField.value,
        sex: this.$refs.sexField.value
      }
      this.profileStore.editUserProfile(body)
      this.edit = false
    }
  }
}
</script>

<style scoped>
@import "@/assets/base.css";

.profile-with-edit {
  position: relative;
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 50%;
  height: 70%;
  padding: 1.5em;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
  border-radius: 15px;
  gap: 2em;
}

.profile {
  display: flex;
  justify-content: center;
}


.user-info {
  display: flex;
  flex-direction: column;
  width: 25%;
  padding: 1em;
  gap: 1em;
}

.preEdit {
  outline: none;
  border: none;
  padding: .5em;
  border-radius: .8em;
  font-size: 1.5em;
  color: #000;
  background: rgb(240, 240, 240);
  -moz-appearance: none;
  -webkit-appearance: none;

}

.postEdit {
  padding: .5em;
  border-radius: .8em;
  font-size: 1.5em;
  color: #000;
  background: rgb(240, 240, 240);
}



.editBtn {
  display: flex;
  height: 30px;
  width: 30px;
  margin: 1.3em;
  padding: 5px;
  cursor: pointer;
  border-radius: 10px;
}

.editBtn img {
  flex-shrink: 0;
  min-width: 100%;
  min-height: 100%
}

.editBtn:hover {
  background: lightgray;
}

.btn-wrapper {
  display: flex;
  justify-content: center;
  gap: 2rem;
}

.save-edit-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  background: #ff7810;
  color: #fff;
  height: 3rem;
  width: 7rem;
  border: none;
  border-radius: 10px;
  font-size: 1.2em;
  padding: 10px;
  cursor: pointer;
}

.canced-edit-btn {
  display: flex;
  justify-content: center;
  align-items: center;
  background: transparent;
  color: #ff7810;
  height: 3rem;
  width: 7rem;
  border: 1px solid #ff7810;
  border-radius: 10px;
  font-size: 1.2em;
  padding: 10px;
  cursor: pointer;
}
</style>