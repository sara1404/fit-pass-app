<template>
  <div class="user-profile-card" :class="[addProfile === true ? 'profile-wrapper-full' : '']"
    @click="emitRegisterProfile">
    <div class="user-image-wrapper">
      <img v-if="profile.sex === 'MALE'" src="@/assets/imgs/avatar-man.jpg" alt="" width="100%" height="100%">
      <img v-else-if="profile.sex === 'FEMALE'" src="@/assets/imgs/avatar-woman.jpg" width="100%" height="100%" alt="">
      <img v-else src="@/assets/imgs/add-profile.png" width="100%" height="100%" alt="">
    </div>
    <div class="user-profile-data" v-show="!addProfile">
      <div class="user-profile-username">
        <span>{{ profile.username }}</span>
      </div>
      <div class="user-profile-name-surname">
        <span>{{ profile.name + " " + profile.surname }}</span>
      </div>
      <div class="user-profile-role">
        <span>{{ profile.role }}</span>
      </div>
    </div>
    <div class="overlay" v-if="showOverlay">
      <button @click="$emit('overlay-pressed')">{{ btnOverlayText }}</button>
    </div>
  </div>
</template>

<script>


export default {
  name: "UserProfile",
  props: {
    addProfile: Boolean,
    profile: {
      type: Object,
      default: {
        username: {
          type: String,
          default: ""
        },
        name: {
          type: String,
          default: "New User"
        },
        surname: {
          type: String,
          default: ""
        },
        role: {
          type: String,
          default: "None"
        },
        sex: {
          type: String,
          default: ""
        },
      }
    },
    btnOverlayText: {
      type: String,
      default: ""
    },
    showOverlay: {
      type: Boolean,
      default: false
    }
  },
  data: function () {
    return {
    }
  },
  methods: {
    emitRegisterProfile: function () {
      if (this.addProfile) {
        this.$emit("registerProfile")
      }
    }
  },
  mounted: function () {
    console.log(this.profile.sex)
  }
}
</script>

<style scoped>
.user-profile-card {
  position: relative;
  display: flex;
  flex-direction: column;
  padding: 10px;
  max-height: 25rem;
  max-width: 20rem;
  box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
  cursor: pointer;
}

.user-image-wrapper {
  display: flex;
  max-width: 15rem;
  max-height: 15rem;
  height: 15rem;
  width: 15rem;
}

.user-image-wrapper img {
  flex-shrink: 0;
  min-width: 100%;
  min-height: 100%
}

.user-profile-data {
  display: flex;
  flex-direction: column;
}

.user-profile-data div {
  padding-top: 0.6rem;
  font-weight: bold;
  font-size: 1.2rem;
  text-align: center;
}

.profile-wrapper-full {
  justify-content: center;
}

.user-profile-card .overlay {
  position: absolute;
  display: none;
  justify-content: center;
  align-items: center;
  width: 100%;
  height: 100%;
  background: rgba(255, 255, 255, 0.6)
}

.user-profile-card .overlay button {
  height: 40px;
  width: 50%;
  background-color: red;
  font-size: 1rem;
  border: none;
  outline: none;
  color: white;
  cursor: pointer;
}

.user-profile-card:hover .overlay {
  display: flex;
}
</style>