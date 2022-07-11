<template>
    <div class="form-wrapper-outer">
        <div class="form-wrapper">
            <div @click="$emit('closeExtrasForm')" class="close-icon">
                <img src="../../assets/imgs/close-icon.png" height="20px" width="20px">
            </div>

            <form action="">
                <p class="title">Buy Extras</p>

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
                            {{ cont.name }} ({{ cont.type }})
                        </option>
                    </select>
                </div>

                <button class="submitButton" @click.prevent="submitExtras">
                    Buy
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
    name: "ExtrasForm",
    created: async function () {
        this.objectsStore = sportObjectsStore()
        this.toast = useToast()
        await this.objectsStore.getSportObjects()
    },
    data: function () {
        return {
            object: "",
            privateContent: [],
            content: null,
            objectsStore: null,
            toast: null
        }
    },
    computed: {
        ...mapState(sportObjectsStore, ['sportObjects'])
    },
    methods: {
        loadPrivateContent: async function () {
            this.privateContent = await this.objectsStore.getAdditionalTrainingSessions(this.object)
        },
        submitExtras: async function () {
            if (this.content === null || this.object === "") {
                this.toast.error("Some data not provided!")
                return
            }
            let body = {
                contentName: this.content.name,
                objectId: this.object
            }
            let resp = await this.objectsStore.buyExtraTrainingPackage(body)
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }
            this.$emit('closeExtrasForm')
            this.toast.success("Sucessfully bought additional package!")
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
    width: 80%;
    min-height: 40px;
    font-size: 1rem;
    outline: none;
    border: 1px solid lightgray;
}

.form-wrapper form {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;

}

.form-wrapper form select {}

.title {
    font-size: 1.5rem;
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
}

.submitButton:hover {
    background-position: left bottom;
}
</style>