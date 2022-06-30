<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState} from "pinia"
</script>

<template>
  <div class="container">
    <button v-show="profile.role === 'ADMIN'" class="add-promo" @click="$emit('displayPromoCodeForm')">
      CREATE PROMO CODE
    </button>
    <button v-show="profile.role === 'BUYER'" class="buy-sub" @click="$emit('displaySubscriptionForm')">
      BUY SUBSCRIPTION
    </button>
    <div class="profile-username" @click="displayProfile">
      {{profile.username}}
    </div>
    <div class="logout-btn">
      <img src="../../assets/imgs/logout.png" alt="" height="25" width="25" @click="logout">
    </div>
  </div>
</template>

<script>
export default {
  name: "UserProfileHeader",
  data: function(){
      return{
        profileStore: null,
      }
  },
  mounted: function() {
    this.profileStore = useProfileStore()
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  },
  methods:{
    displayProfile: function(){
      this.$router.push({path: "/users/me"})
    },
    
    logout: function(){
      this.profileStore.logout()
      this.$router.push({path: "/"})
    }
  }
}

</script>

<style scoped>

  .container{
    display: flex;
    justify-content: center;
    align-items: center;
    margin-right: 20px;
  }

  .profile-username {
    display: flex;
    gap: 10px;
    padding: 10px;
    border: 1px solid #ff7810;
    border-radius: 5px;
    font-size: 20px;
    margin-right: 20px;
    cursor: pointer;
  }

  .logout-btn{
    display: flex;
    cursor: pointer;
    height: 25px;
    width: 25px;
    padding: 7px;
    border-radius: 10px;
  }

  .logout-btn:hover{
    background: rgb(238, 237, 237);
  }

  .logout-btn img{
    flex-shrink: 1;
  }

  .add-promo, .buy-sub{
      display: flex;
      gap: 10px;
      padding: 10px;
      border: 1px solid #ff7810;
      border-radius: 5px;
      font-size: 20px;
      margin-right: 20px;
      cursor: pointer;
      outline: none;
      border: none;
      background-color: #ff7810;
      color: #fff;
  }
</style>