<script setup>
import SportObjectContent from "@/components/objects/SportObjectContent.vue"
import SportObjectContentForm from '@/components/forms/SportObjectContentForm.vue'
import AddSportObjectContentForm from '@/components/forms/AddSportObjectContentForm.vue'
import { useProfileStore } from "@/stores/profile-store.js"
import {mapState} from "pinia"
import {defineComponent} from "vue";

defineComponent(SportObjectContent)
defineComponent(SportObjectContentForm)
defineComponent(AddSportObjectContentForm)
</script>

<template>
    <div class="container">
        <SportObjectContentForm v-show="displayEdit" :content = "clickedContent"/>
        <AddSportObjectContentForm @closeAddForm="displayAddContentForm" v-show="displayAdd" @contentAdded='refresh'/>
        <div class="title">{{profile.sportObject.name}} - {{profile.sportObject.location.address.street}} {{profile.sportObject.location.address.number}}</div>
        <div class="content-wrapper">
            <SportObjectContent v-for="content in profile.sportObject.content" :key="content.name" :contentData = "content" @displayEditForm='displayEditForm'/>
            <div class="add-new-content">
                <img src="../../assets/imgs/add-content.png" alt="" width="140" @click="displayAddContentForm">
            </div>
        </div>
       
        
    </div>
</template>


<script>
 export default {
  name: "ManagersSportObject",
  data: function() {
    return {
        profileStore: null,
        displayEdit: false,
        displayAdd: false,
        clickedContent: null,
    }
  },
  mounted: function(){
     this.profileStore = useProfileStore()
  },
  computed: {
      ...mapState(useProfileStore, ['profile'])
  },
  methods:{
    displayEditForm: function(value){
        this.displayEdit = true;
        this.clickedContent = value
        console.log(value)
    },
    displayAddContentForm: function(){
        this.displayAdd = !this.displayAdd
    },
    refresh: async function(){
      await this.profileStore.getUserProfile();
      this.displayAddContentForm()
    },
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
    border: 1px solid black;
    width: 180px;
    padding: 16px;
}

.add-new-content img:hover{
    cursor: pointer;
}

.title{
    font-size: 1.5rem;
}
</style>