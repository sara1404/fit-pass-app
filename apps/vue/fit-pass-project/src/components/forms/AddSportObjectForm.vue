<script setup>
import { sportObjectsStore } from "@/stores/objects-store.js"
import { useProfileStore } from "@/stores/profile-store.js"
import axios from "axios"
import { mapState } from "pinia"
import NewProfileForm from "@/components/admin/profiles/NewProfileForm.vue"
</script>

<template>
    <div class="wrapper">
        <form action="" class="form-wrapper">
            <div @click="$emit('closeAddObjectForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>
            <p class="title">Create sport object</p>
            <label for="" class="errorLabel">{{ error }}</label>
            <div class="name-wrapper">
                <input v-model="name" type="text" placeholder="Name">
                <span class="error"> {{ errors.nameError }}</span>

            </div>
            <div class="type-wrapper">
                <select name="" id="" v-model="type">
                    <option value="type" v-for="type in types" :key="type">{{ type }}</option>
                </select>
                <span class="error"> {{ errors.typeError }}</span>

            </div>
            <div class="location-wrapper">
                <input class="country" type="text" placeholder="Country" v-model="country">
                <input class="city" type="text" placeholder="City" v-model="city">
                <input class="postal-code" type="text" placeholder="Postal code" v-model="postalCode">
                <input class="street" type="text" placeholder="Street" v-model="street">
                <input class="number" type="text" placeholder="Number" v-model="number">
            </div>
            <div class="logo-wrapper">
                <input type="file" ref="logo" @change="updateFile">
                <span class="error"> {{ errors.pictureError }}</span>

            </div>
            <div class="manager-wrapper">
                <select name="" id="" v-model="manager">
                    <option :value="manager.username" v-for="manager in managers" :key="manager.username">{{
                            manager.name
                            + " " + manager.surname
                    }}</option>
                </select>
                <img src="../../assets/imgs/add-comment.png" alt="" height="25" width="25" v-show="managers.length == 0"
                    @click="displayRegisterManager = true;">
                <span class="error"> {{ errors.managerError }}</span>
            </div>
            <button class="saveBtn" type="submit" v-on:click.prevent="addSportObject">CREATE</button>
        </form>
        <NewProfileForm v-show="displayRegisterManager" @closeRegisterForm="displayRegisterManager = false"
            @user-registered="registerUser" />
    </div>

</template>

<script>
export default {
    name: "AddSportObjectForm",
    created: async function () {
        this.objectsStore = sportObjectsStore()
        await this.captureAllFreeManagers()
    },
    computed: {

    },
    data: function () {
        return {
            base: "http://localhost:8000/api/",
            managers: [],
            displayRegisterManager: false,
            objectsStore: null,
            profileStore: null,
            name: "",
            type: "",
            country: "",
            city: "",
            postalCode: "",
            street: "",
            number: "",
            latitude: "",
            longitude: "",
            pic: null,
            manager: "",
            error: "",
            types: ["GYM", "POOL", "SPORT_CENTER", "DANCE_STUDIO"],
            errors: {
                nameError: "",
                typeError: "",
                pictureError: "",
                managerError: "",
            }
        }
    },
    methods: {
        registerUser: async function () {
            await this.captureAllFreeManagers()
        },
        addSportObject: async function () {
            this.validate()
            let lonAndLat = await this.getLongAndLatFromAddress()
            const resp = await this.objectsStore.addNewSportObject(this.createSportObjectBody(), this.manager)
            if (resp.error) {
                console.log("Some error happened " + resp.error)
                return;
            }
            await this.objectsStore.uploadLogo(resp.id, this.pic)
            this.$emit('closeAddObjectForm')
        },

        updateFile: function () {
            this.pic = this.$refs.logo.files[0]
        },

        async captureAllFreeManagers() {
            let params = {
                role: "MANAGER",
                managerHasObject: false
            }
            try {
                let resp = await axios.get(this.base + "users/all", {
                    params,
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })
                this.managers = resp.data
            } catch (e) {
                this.managers = []
            }
        },

        async getLongAndLatFromAddress() {
            try {
                let resp = await axios.get('https://nominatim.openstreetmap.org/search', {
                    params: {
                        format: 'json',
                        q: `${this.country}, ${this.city}, ${this.street} ${this.number}`
                    }
                })
                this.longitude = resp.data[0].lon
                this.latitude = resp.data[0].lat
                return { latitude: this.latitude, longitude: this.longitude }
            } catch (e) {
                console.log(e)
                return {
                    longitude: 0,
                    latitude: 0
                }
            }
        },
        createSportObjectBody: function () {
            return {
                name: this.name,
                type: this.type,
                location: {
                    longitude: this.longitude,
                    latitude: this.latitude,
                    address: {
                        street: this.street,
                        number: this.number,
                        city: this.city,
                        country: this.country,
                        postalCode: this.postalCode
                    }
                }
            }
        },

        validate() {
            this.required("name")
            this.required("type")
            this.notNull("pic")
            this.required("manager")
        },
        required: function (field) {
            if (this[field].trim() === "") {
                this.errors[field + "Error"] = "Field should not be empty!"
            }
        },
        notNull: function (field) {
            if (this[field] === null) {
                this.errors[field + "Error"] = "Field should not be empty!"
            }
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

.errorLabel {
    display: flex;
    justify-content: center;
    color: red;
}

.wrapper {
    display: flex;
    position: fixed;
    height: 100vh;
    width: 100vw;
    background-color: rgba(255, 255, 255, 0.5);
    z-index: 10000000;
}

.form-wrapper {
    display: flex;
    flex-direction: column;
    position: fixed;
    height: 750px;
    width: 500px;
    background-color: #fff;
    z-index: 1000;
    top: 50%;
    left: 50%;
    margin-top: -350px;
    margin-left: -250px;
    border: 1px solid lightgray;
    border-radius: 10px;
}

.title {
    display: flex;
    justify-content: center;
    font-size: 30px;
}

.error-wrapper {
    color: red;
    width: 80%;
    height: 15px;
    margin: 0px;
    padding: 0px;
    align-self: center;
    text-align: center;
}

.error {
    color: red;
    width: 80%;
}

.name-wrapper,
.type-wrapper,
.latitude-wrapper,
.longitude-wrapper,
.logo-wrapper,
.manager-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
    padding: 0;
}

.location-wrapper {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    height: 100px;
    margin-top: 20px;
    padding: 0;
    gap: 5px;
}

.street,
.number,
.city,
.country,
.postal-code {
    display: flex;
    min-height: 40px;
    border-radius: 5px;
    outline: 1px solid lightgray;
    border: none;
    padding: 0px 0px 0px 5px;
}

.street {
    width: 35%;
}

.number {
    width: 10%;
}

.city {
    width: 30%;
}

.name-wrapper input,
.type-wrapper select,
.logo-wrapper input,
.latitude-wrapper input,
.longitude-wrapper input {
    padding: 0px 0px 0px 5px;
}


.name-wrapper input,
.type-wrapper select,
.logo-wrapper input,
.latitude-wrapper input,
.longitude-wrapper input,
.manager-wrapper select {
    display: flex;
    justify-self: center;
    min-height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: 1px solid lightgray;
    border: none;
}

.manager-wrapper select,
.type-wrapper select {
    width: calc(80% + 5px)
}

.manager-wrapper img {
    position: absolute;
    right: 15px;
    cursor: pointer;
}


.saveBtn {
    width: 80%;
    height: 50px;
    margin: auto;
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
}

.saveBtn:hover {
    background-position: left bottom;
}
</style>