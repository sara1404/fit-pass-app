<script setup>
import SportObjectContent from "@/components/objects/SportObjectContent.vue"
import SportObjectContentForm from '@/components/forms/SportObjectContentForm.vue'
import AddSportObjectContentForm from '@/components/forms/AddSportObjectContentForm.vue'
import SportObjectView from "@/views/SportObjectView.vue"
import UserProfile from "@/components/admin/profiles/UserProfile.vue"
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import {defineComponent} from "vue";
import axios from "axios"

defineComponent(SportObjectContent)
defineComponent(SportObjectContentForm)
defineComponent(AddSportObjectContentForm)
</script>

<template>
    <div class="container">
        <SportObjectContentForm v-show="displayEdit" :content = "clickedContent" @closeEditForm="closeEditForm" @contentEdited='refresh'/>

        <AddSportObjectContentForm @closeAddForm="displayAddContentForm" v-show="displayAdd" @contentAdded='refresh'/>
        <SportObjectView :sportObjectProp="profile.sportObject"/>
        
        <div class="content-separator">Available content</div>

        <div class="content-wrapper">
            <SportObjectContent v-for="content in profile.sportObject.content" :key="content.name" :contentData = "content" @displayEditForm='displayEditForm'/>
            <div class="add-new-content">
                <img src="../../assets/imgs/add-content.png" alt="" width="140" @click="displayAddContentForm">
            </div>
        </div>
        <div class="user-separator">Users who visited this sport object</div>
        <div class="content-wrapper">
            <UserProfile v-for="user in usersVisited" :key="user.username" :profile = "user"/>
     
        </div>
        <div class="coach-separator">Coaches who hold trainings at this sport object </div>
        <div class="content-wrapper">
            <UserProfile v-for="coach in coaches" :key="coach.username" :profile = "coach"/>
        </div>

        <div class="training-separator"> Trainings </div>

        <section class="trainings-wrapper">
            <div v-for="training in trainings" :key="training.id" class="single-training">
                <label class="training-title" for="">{{training.contentName}}</label>
                <label class="training-type" for="">{{training.type}}</label>
                <label class="training-coach" for="">Coach: {{training.coachUsername}}</label>
                <label class="training-buyer" for="">Buyer: {{training.buyerUsername}}</label>
                <label class="training-date" for="">Date: {{training.reservedAt}}</label>
                <label class="training-duration" for="">Duration: {{training.duration}}min</label>
            </div>
        </section>
        
    </div>
</template>


<script>
 export default {
  name: "ManagersSportObject",
  data: function() {
    return {
        base: "http://localhost:8000/api/",
        usersVisited: [],
        coaches: [],
        trainings: [],
        profileStore: null,
        displayEdit: false,
        displayAdd: false,
        clickedContent: {},

    }
  },
  mounted: async function(){
     this.profileStore = useProfileStore()
     await this.profileStore.getUserProfile();
     await this.getTrainings()
     let resp = await this.profileStore.captureManagerData()
     this.usersVisited = resp.usersVisited
     this.coaches = resp.coaches
     console.log(this.trainings)
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  },
  methods:{
    displayEditForm: function(value){
        this.clickedContent = value
        this.displayEdit = true;
    },
    displayAddContentForm: function(){
        this.displayAdd = !this.displayAdd
    },
    closeEditForm: function(){
        this.displayEdit = !this.displayEdit;
    },
    refresh: async function(){
      await this.profileStore.getUserProfile();
      this.displayAdd = false
      this.displayEdit = false
    },

    getTrainings: async function(){
        try{
            let resp  =  await axios.get(this.base + 'objects/training/manager',{
                headers:{
                    Authorization: "Bearer " + localStorage.getItem("auth-token")
                }
            })
            this.trainings = resp.data
        }catch(e){
            console.log(e)
        }
    }
  }
}
</script>

<style scoped>
@import "@/assets/base.css";
.container{
    padding: 1rem;
}

.content-wrapper{
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 1.5rem;
    margin-top: 1rem;
}

.add-new-content{
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    position: relative;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    min-height: 220px;
    width: 180px;
    padding: 16px;
}

.add-new-content img:hover{
    cursor: pointer;
}

.title{
    font-size: 1.5rem;
}

.training-separator, .user-separator, .coach-separator, .content-separator{
    display: flex;
    justify-content: center;
    font-size: 2rem;
    margin: 3rem 0;
    color: #ff7810;
}

.trainings-wrapper{
    display: flex;
    justify-content: center;

}

.single-training{
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    gap: 5px;
    max-width: 20rem;
    min-height: 200px;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    padding: 2rem;
}

.training-title{
    font-size: 20px;
    color: #000;
}

.training-type, .training-coach,
 .training-buyer, .training-date, .training-duration{
    color: gray;
    font-size: 14px;
}
</style>