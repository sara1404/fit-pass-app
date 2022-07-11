<script setup>
import { sportObjectsStore } from "@/stores/objects-store.js"
import { useProfileStore } from "@/stores/profile-store.js"
import axios from "axios"
import { mapState } from "pinia"
import NewProfileForm from "@/components/admin/profiles/NewProfileForm.vue"
</script>

<template>
    <div class="wrapper">
        <form action="" class="form-wrapper" v-show="displayObjectForm">
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
                    <option :value="type" v-for="type in types" :key="type">{{ type }}</option>
                </select>
                <span class="error"> {{ errors.typeError }}</span>

            </div>
            <div class="location-wrapper">
                <input class="country" type="text" placeholder="Country" v-model="country">
                <span class="error"> {{ errors.countryError }}</span>
            </div>
            <div class="location-wrapper">
                <input class="city" type="text" placeholder="City" v-model="city">
                <span class="error"> {{ errors.cityError }}</span>
            </div>
            <div class="location-wrapper">
                <input class="postal-code" type="text" placeholder="Postal code" v-model="postalCode">
                <span class="error"> {{ errors.postalCodeError }}</span>
            </div>
            <div class="location-wrapper">
                <input class="street" type="text" placeholder="Street" v-model="street">
                <span class="error"> {{ errors.streetError }}</span>
            </div>
            <div class="location-wrapper">
                <input class="number" type="text" placeholder="Number" v-model="number">
                <span class="error"> {{ errors.numberError }}</span>
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
                    @click="displayRegisterManager = true; displayObjectForm = false; ">
                <span class="error"> {{ errors.managerError }}</span>
            </div>
            <button class="saveBtn" type="submit" v-on:click.prevent="addSportObject">CREATE</button>
        </form>
        <NewProfileForm v-show="displayRegisterManager" @closeRegisterForm="displayRegisterManager = false"
            @user-registered="registerUser" />
    </div>

</template>

<script>
import { useToast } from 'vue-toast-notification';

export default {
    name: "AddSportObjectForm",
    mounted: async function () {
        this.objectsStore = sportObjectsStore()
        await this.captureAllFreeManagers()
        this.toast = useToast()
    },
    computed: {

    },
    data: function () {
        return {
            base: "http://localhost:8000/api/",
            managers: [],
            displayRegisterManager: false,
            displayObjectForm: true,
            toast: null,
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
                cityError: "",
                postalCodeError: "",
                streetError: "",
                countryError: "",
                numberError: ""
            }
        }
    },
    methods: {
        registerUser: async function () {
            await this.captureAllFreeManagers()
            this.displayObjectForm = true;
        },
        addSportObject: async function () {
            this.validate()
            let lonAndLat = await this.getLongAndLatFromAddress()
            const resp = await this.objectsStore.addNewSportObject(this.createSportObjectBody(), this.manager)
            if (resp.error) {
                this.toast.error(resp.response.data)
                return;
            }
            await this.objectsStore.uploadLogo(resp.id, this.pic)
            this.toast.success("Sucessfully registered object")
            await this.resetForm()
            this.$emit('closeAddObjectForm')
        },
        resetForm: async function () {
            this.name = ""
            this.type = ""
            this.country = ""
            this.city = ""
            this.street = ""
            this.number = ""
            this.manager = ""
            this.logo = null
            await this.captureAllFreeManagers()

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
            this.required("country")
            this.required("city")
            this.required("postalCode")
            this.required("street")
            this.required("number")
            this.notNull("pic")
            this.required("manager")
        },
        required: function (field) {
            if (this[field].trim() === "") {
                this.errors[field + "Error"] = "Field should not be empty!"
                return;
            }
            this.errors[field + "Error"] = ""
        },
        notNull: function (field) {
            if (this[field] === null) {
                this.errors[field + "Error"] = "Field should not be empty!"
                return;
            }
            this.errors[field + "Error"] = ""
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
    background-color: rgba(0, 0, 0, 0.3);
    z-index: 10000000;
}

.form-wrapper {
    display: flex;
    flex-direction: column;
    position: fixed;
    min-height: 750px;
    width: 500px;
    background-color: #fff;
    z-index: 1000;
    top: 50%;
    left: 50%;
    margin-top: -400px;
    margin-left: -250px;
    padding: 1rem;
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
.location-wrapper,
.manager-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
    padding: 0;
}

.name-wrapper input,
.type-wrapper select,
.logo-wrapper input,
.location-wrapper input,

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

.manager-wrapper {
    position: relative;
}

.manager-wrapper img {
    position: absolute;
    top: 10px;
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
    margin-top: 1rem;
}

.saveBtn:hover {
    background-position: left bottom;
}
</style>