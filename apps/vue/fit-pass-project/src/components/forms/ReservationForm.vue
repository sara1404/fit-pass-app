<template>
    <div class="form-wrapper-outer">
        <div class="form-wrapper">
            <div @click="$emit(`close${emitName}Form`)" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>

            <form action="">
                <p class="title">Reserve training</p>

                <div class="objects-wrapper wrapper">
                    <select required name="" id="" v-model="object" @change="loadPrivateContent"
                        placeholder="Sport Object">
                        <option value="" disabled selected hidden>Choose Sport Object</option>
                        <option :value="obj.id" v-for="obj in sportObjects">
                            {{ obj.name }}
                        </option>
                    </select>
                </div>

                <div class="content-wrapper wrapper">
                    <select name="" id="" v-model="content">
                        <option value="" disabled selected hidden>Choose Training</option>
                        <option :value="cont" v-for="cont in privateContent">
                            {{ cont.name }} ({{ cont.price }}RSD)
                        </option>
                    </select>
                </div>

                <div class="reservation-application-date wrapper">
                    <input type="number" v-model="duration" placeholder="Duration">
                </div>
                <div class="reservation-application-date wrapper">
                    <input type="datetime-local" v-model="time">
                </div>

                <button class="submitButton" @click.prevent="reserve">
                    Reserve
                </button>
            </form>
        </div>
    </div>
</template>


<script>
import { mapState } from 'pinia'
import { sportObjectsStore } from '../../stores/objects-store'
import { useToast } from 'vue-toast-notification'


export default {
    name: "ReservationForm",
    props: {
        captureContent: {
            type: Function,
            default: (objectId) => { console.log(objectId) }
        },
        submitReservation: {
            type: Function,
            default: (content) => { console.log("Submit reservation") }
        },
        emitName: {
            type: String,
            default: "reservation"
        }
    },
    created: async function () {
        this.objectsStore = sportObjectsStore()
        this.toast = useToast()
        this.objects = await this.objectsStore.getSportObjects()
    },
    data: function () {
        return {
            privateContent: [],
            content: null,
            object: "",
            objectsStore: null,
            time: null,
            toast: null,
            duration: null
        }
    },
    computed: {
        ...mapState(sportObjectsStore, ['sportObjects'])
    },
    methods: {
        loadPrivateContent: async function () {
            this.privateContent = await this.captureContent(this.object)
        },
        reserve: async function () {
            if (this.object === "" || this.content === null) {
                this.toast.error("Some data not provided!");
                return;
            }
            let body = {
                contentName: this.content.name,
                sportObjectId: this.object,
                reservedAt: this.formatDate(),
                type: this.content.type,
                duration: this.duration
            }

            let resp = await this.submitReservation(body)
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }
            this.toast.success("Sucessfully reserved training!")

            this.$emit(`close${this.emitName}Form`)
        },
        formatDate() {
            //dd-MM-yyyy HH:mm:ss
            let date = new Date(this.time)
            return `${date.getDate()}-${date.getMonth() + 1}-${date.getFullYear()} ${this.formatHours(date)}`
        },
        formatHours(date) {
            return `${date.getHours() < 10 ? '0' + date.getHours() : date.getHours()}:${date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()}:00`
        }
    }
}
</script>


<style scoped>
.form-wrapper-outer {
    position: absolute;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    display: flex;
    align-items: center;
    justify-content: center;
    background-color: rgba(0, 0, 0, 0.3);
    z-index: 999999999;
}

.form-wrapper {
    position: relative;
    width: 500px;
    ;
    min-height: 200px;
    padding: 1rem;
    background: white;
    border: 1px solid lightgray;
    border-radius: 5px;
}

.form-wrapper .close-icon {
    position: absolute;
    top: 5px;
    right: 5px;
    width: 20px;
    height: 20px;
    display: flex;
    align-items: center;
    justify-content: center;
    cursor: pointer;
}

.form-wrapper .close-icon img {
    height: 20px;
    width: 20px;
    flex: 1;
}

.wrapper {
    display: flex;
    align-items: center;
    justify-content: center;
    width: 100%;
    height: 60px;
}

.wrapper select {
    width: calc(80% + 5px);
    min-height: 45px;
    font-size: 1rem;
    outline: none;
    border: 1px solid lightgray;
    border-radius: 5px;
}

.form-wrapper form {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form-wrapper form input {
    min-height: 45px;
    width: 80%;
    border: none;
    padding-left: 5px;
    outline: 1px solid lightgray;
    border-radius: 5px;
}

.title {
    font-size: 2rem;
}

.submitButton {
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

.submitButton:hover {
    background-position: left bottom;
}
</style>