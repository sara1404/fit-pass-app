<script setup>
import { sportObjectsStore} from "@/stores/objects-store.js"
import { useProfileStore} from "@/stores/profile-store.js"
import { mapState} from "pinia"
import axios from "axios"
</script>

<template>
    <div class="wrapper">
        <form action="" class="form-wrapper">
            <div @click="$emit('closeAddForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>
            <p class="title">Add content</p>
            <div class="option-wrapper">
                <div>
                    <input type="radio" name="contentType" value="RELAXATION" @click="chosenChange(false)" v-model="typeOfContent">
                    <label for="">RELAXATION</label>
                </div>
                <div>
                    <input type="radio" name="contentType" value="TRAINING" @click="chosenChange(true)" v-model="typeOfContent">
                    <label for="">TRAINING</label>
                </div>
            </div>
            <div class="name-wrapper">
                <input type="text" placeholder="Name" v-model="name">
            </div>
            <span class="error">{{error}}</span>
            <div class="type-wrapper">
                <input type="text" placeholder="Type" v-model="type">
            </div>
            <div class="pic-wrapper">
                <input type="file" ref="fileInput" @change="updateImage" >
            </div>
            <div class="description-wrapper" v-show="chosen">
                <input type="text" placeholder="description" v-model="description">
            </div>
            <div class="object-wrapper" v-show="chosen">
                <select name="" id="" v-model="object">
                    <option :value="obj.id" v-for="obj in sportObjects" :key="obj.id">{{obj.name}}</option>
                </select>
            </div>
            <div class="coach-wrapper" v-show="chosen">
                <select name="" id="" v-model="coach">
                    <option :value="coach.username" v-for="coach in coaches" :key="coach.username">{{coach.name + coach.surname}}</option>
                </select>
            </div>
            <div class="duration-wrapper" v-show="chosen">
                <input  type="number" placeholder="Training duration in minutes" min="0" max="120" v-model="duration">
            </div>
            <button class="addBtn" type="submit" v-on:click.prevent="addContent">SAVE</button>
        </form>
    </div>
</template>

<script>
export default {
  name: "AddSportObjectContentForm",

  data: function() {
    return{
      sportObjectsStore : null,
      profileStore : null,
      chosen: false,
      typeOfContent: null,
      name: null,
      type: null,
      description: null,
      object: null,
      coach: null,
      duration: null,
      image: null,
      error: ""
    }
  },
  methods:{
    chosenChange: function(value){
        this.chosen = value
    },
    updateImage: function() {
        this.image = this.$refs.fileInput.files[0]
    },
    addContent: async function(e){

        let formData = new FormData();
        formData.append("file", this.image);

        const headers = { 'Content-Type': 'multipart/form-data', 'Authorization': "Bearer " + localStorage.getItem("auth-token") };
        let resp = await axios.post('http://localhost:8000/api/objects/content/upload/' + this.name, formData, { headers });

        let body = {
            name: this.name,
            type: this.type,
            flag: this.typeOfContent,
            objectId: this.object,
            trainingDuration: this.duration,
            coach: this.coach,
            description: this.description,
            pictureUrl: resp.data
        }
        let resp1 = await this.sportObjectsStore.addNewSportObjectContent(body)
        if (resp1.error) {
            this.error = resp1.error
            return
        }

        this.$emit('contentAdded')
    }
  },
  mounted: async function(){
     this.sportObjectsStore = sportObjectsStore()
     this.profileStore = useProfileStore()
     await this.sportObjectsStore.getSportObjects();
     await this.profileStore.captureAllCoaches()
  },

  computed:{
     ...mapState(sportObjectsStore, ['sportObjects']),
     ...mapState(useProfileStore, ['coaches'])
  }

}
</script>

<style scoped>
@import "@/assets/base.css";

.close-icon{
    display: flex;
    justify-self: flex-end;
    align-self: flex-end;
    height: 20px;
    width: 20px;
    padding: 5px;
    cursor: pointer;
}

.close-icon img{
    height: 20px;
    width: 20px;
}

.wrapper{
    display: flex;
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-color: rgba(255,255,255,0.5);
    z-index: 100000000000;
}

.form-wrapper{
    display: flex;
    flex-direction: column;
    position: fixed;
    width: 500px;
    min-height: 500px;
    max-height: 1000px;
    background-color: #fff;
    z-index: 1000;
    left: 50%;
    margin-left: -250px;
    border: 1px solid lightgray;
    border-radius: 10px;
    padding-bottom: 1rem;
}

.title{
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.option-wrapper{
    gap: 1.2rem;
}

.name-wrapper, .type-wrapper, .pic-wrapper, .description-wrapper,
.duration-wrapper, .object-wrapper, .coach-wrapper, .option-wrapper{
    display: flex;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
}

.name-wrapper input, .type-wrapper input, .description-wrapper input, .duration-wrapper input,
.object-wrapper select, .coach-wrapper select{
    height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
}

.addBtn{
    width: 80%;
    height: 50px;
    border-radius: 5px;
    outline: none;
    border: none;
    background: #ff7810;
    color: #fff;
    font-size: 16px;
    cursor: pointer;
    background: linear-gradient(to right, black 50%, #ff7810 50%);
    background-size: 200% 100%;
    background-position: right bottom;
    transition: all .3s ease-out;
    margin: 1rem auto;
}

.addBtn:hover{
    background-position: left bottom;
}

.error{
    display: flex;
    justify-content: center;
    color: red;
}

</style>