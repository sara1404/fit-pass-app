<script setup>
import { useProfileStore } from "@/stores/profile-store.js"
import axios from "axios"
import { mapState } from "pinia"
</script>

<template>
    <div class="wrapper">
        <form action="" class="form-wrapper">
            <div @click="$emit('closePromoCodeForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>
            <p class="title">Create promo code</p>
            <label for="" class="errorLabel">{{ error }}</label>
            <div class="id-wrapper">
                <input v-model="id" type="text" placeholder="Promo code id">
            </div>
            <div class="valid-from-wrapper">
                <input v-model="validFrom" type="date">
            </div>
            <div class="valid-to-wrapper">
                <input v-model="validTo" type="date">
            </div>
            <div class="times-wrapper">
                <input v-model="timesToUse" type="number" min="0" placeholder="Times allowed to use">
            </div>
            <div class="discount-wrapper">
                <input v-model="salePercentage" type="number" placeholder="Discount">
            </div>
            <button class="saveBtn" type="submit" v-on:click.prevent="addPromoCode">CREATE</button>
        </form>
    </div>


</template>

<script>
export default {
    name: "AddPromoCodeForm",
    mounted: function () {
        this.profileStore = useProfileStore()
    },
    computed: {
        ...mapState(useProfileStore, ['profile'])
    },
    data: function () {
        return {
            profileStore: null,
            id: "",
            validFrom: "",
            validTo: "",
            timesToUse: "",
            salePercentage: "",
            error: ""
        }
    },
    methods: {
        addPromoCode: async function () {
            let body = {
                id: this.id,
                validFrom: this.validFrom,
                validTo: this.validTo,
                timesToUse: this.timesToUse,
                salePercentage: this.salePercentage
            }
            const resp = await this.profileStore.addPromoCode(body)
            if (resp.error) {
                this.error = resp.error
            } else {
                this.error = ""
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
    background-color: rgba(0, 0, 0, 0.3);
    z-index: 10000000;
}

.form-wrapper {
    display: flex;
    flex-direction: column;
    position: fixed;
    height: 570px;
    width: 500px;
    background-color: #fff;
    z-index: 1000;
    top: 50%;
    left: 50%;
    margin-top: -250px;
    margin-left: -250px;
    border: 1px solid lightgray;
    border-radius: 10px;
}

.title {
    display: flex;
    justify-content: center;
    font-size: 30px;

}

.id-wrapper,
.valid-from-wrapper,
.valid-to-wrapper,
.times-wrapper,
.discount-wrapper {
    display: flex;
    justify-content: center;
    height: 50px;
    margin-top: 20px;
}

.id-wrapper input,
.valid-from-wrapper input,
.valid-to-wrapper input,
.times-wrapper input,
.discount-wrapper input {
    height: 40px;
    width: 80%;
    border-radius: 5px;
    outline: none;
    border: 1px solid lightgray;
    padding-left: 5px;
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