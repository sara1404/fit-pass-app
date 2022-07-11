<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
import { sportObjectsStore } from "@/stores/objects-store.js"
import { mapState } from "pinia"
import axios from 'axios'

</script>

<template>
    <div class="wrapper">
        <form action="" class="form-wrapper">
            <div @click="$emit('closeEditForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>
            <p class="title">Edit training</p>
            <div class="name-wrapper">
                <input ref="nameField" type="text" placeholder="Name" :value="content.name" disabled>
            </div>
            <div class="type-wrapper">
                <input ref="typeField" type="text" :value="content.type">
            </div>
            <div class="pic-wrapper">
                <input type="file" ref="pictureField" @change="updatePicture">
            </div>
            <div class="description-wrapper">
                <input ref="descriptionField" type="text" :value="content.description">
            </div>
            <div class="coach-wrapper">
                <select ref="coachField" name="" id="" v-model="selected">
                    <option :value="coach.username" v-for="coach in coaches" :key="coach.username">{{ coach.name +
                            coach.surname
                    }}</option>
                </select>
            </div>
            <div class="duration-wrapper">
                <input ref="durationField" type="number" min="0" max="120" :value="content.trainingDuration">
            </div>
            <button class="editBtn" type="submit" v-on:click.prevent="editContent">SAVE</button>
        </form>
    </div>
</template>

<script>
export default {
    name: "SportObjectContentForm",
    props: {
        content: {
            type: Object,
            default: {}
        },
    },
    data: function () {
        return {
            sportObjectsStore: null,
            profileStore: null,
            picture: null
        }
    },
    methods: {
        updatePicture: function () {
            this.picture = this.$refs.pictureField.files[0]
        },
        editContent: async function () {
            await this.uploadPicture();
            let body = {
                name: this.$refs.nameField.value,
                type: this.$refs.typeField.value,
                flag: this.content.flag,
                objectId: this.content.objectId,
                trainingDuration: this.$refs.durationField.value,
                coach: this.$refs.coachField.value,
                description: this.$refs.descriptionField.value,
            }
            await this.sportObjectsStore.editSportObjectContent(body, this.content.name)
            this.$emit('contentEdited')
        },

        uploadPicture: async function () {
            let formData = new FormData();
            if (this.picture == null) {
                console.log("no picture")
                return
            }
            formData.append("file", this.picture);

            const headers = { 'Content-Type': 'multipart/form-data', 'Authorization': "Bearer " + localStorage.getItem("auth-token") };
            let resp = await axios.post('http://localhost:8000/api/objects/content/upload/' + this.$refs.nameField.value, formData, { headers });
            return resp.data
        }
    },
    mounted: async function () {
        this.profileStore = useProfileStore()
        this.sportObjectsStore = sportObjectsStore()
        await this.profileStore.captureAllCoaches()
    },
    computed: {
        ...mapState(useProfileStore, ['coaches']),
        selected: function () {
            return this.content.coachUsername
        }
    }
}
</script>

<style scoped>
@import "@/assets/base.css";

.close-icon {
    display: flex;
    justify-self: flex-end;
    align-self: flex-end;
    height: 20px;
    width: 20px;
    padding: 5px;
    cursor: pointer;
}

.close-icon img {
    height: 20px;
    width: 20px;
}

.wrapper {
    display: flex;
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-color: rgba(255, 255, 255, 0.5);
    z-index: 100000000000;
}

.form-wrapper {
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
    background-color: rgba(0, 0, 0, 0.3);
    border-radius: 10px;
    padding-bottom: 1rem;
}

.title {
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.option-wrapper {
    gap: 1.2rem;
}

.name-wrapper,
.type-wrapper,
.pic-wrapper,
.description-wrapper,
.duration-wrapper,
.object-wrapper,
.coach-wrapper,
.option-wrapper {
    display: flex;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
}

.name-wrapper input,
.type-wrapper input,
.description-wrapper input,
.duration-wrapper input,
.object-wrapper select,
.coach-wrapper select {
    height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
}

.editBtn {
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

.editBtn:hover {
    background-position: left bottom;
}
</style>