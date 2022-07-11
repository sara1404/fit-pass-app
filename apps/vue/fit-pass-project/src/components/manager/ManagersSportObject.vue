<script setup>
import SportObjectContent from "@/components/objects/SportObjectContent.vue"
import SportObjectContentForm from '@/components/forms/SportObjectContentForm.vue'
import AddSportObjectContentForm from '@/components/forms/AddSportObjectContentForm.vue'
import SportObjectView from "@/views/SportObjectView.vue"
import UserProfile from "@/components/admin/profiles/UserProfile.vue"
import SportObjectWorkTime from '@/components/objects/SportObjectWorkTime.vue'
import SportObjectsMap from "@/components/objects/SportObjectsMap.vue"
import SportObjectInfo from "@/components/custom/SportObjectInfo.vue"
import SingleSportObjectContent from "@/components/custom/SingleSportObjectContent.vue"
import SportObjectSchedule from "@/components/custom/SportObjectSchedule.vue"
import CommentSection from "@/components/custom/CommentSection.vue"
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState } from "pinia"
import { defineComponent } from "vue";
import axios from "axios"

defineComponent(SportObjectContent)
defineComponent(SportObjectContentForm)
defineComponent(AddSportObjectContentForm)
</script>

<template>
    <div class="container">
        <SportObjectContentForm v-show="displayEdit" :content="clickedContent" @closeEditForm="closeEditForm"
            @contentEdited='refresh' />

        <AddSportObjectContentForm @closeAddForm="displayAddContentForm" v-show="displayAdd" @contentAdded='refresh' />
        <div class="inner-container">
            <SportObjectInfo :sportObject="profile.sportObject" />
            <SingleSportObjectContent :sportObject="profile.sportObject" />
            <div class="time-and-map">
                <SportObjectWorkTime :workTimeStyle="workTimeStyle" :singleWorkTimeStyle="singleWorkTimeStyle"
                    :titleStyle="titleStyle" class="work-time" :objectsWorkTime='profile?.sportObject?.workTime' />
                <div class="map-wrapper">
                    <SportObjectsMap :sportObjects="[profile.sportObject]" />
                    <label for="">{{ profile?.sportObject?.location?.address?.street }}
                        {{ profile?.sportObject?.location?.address?.number }}
                        , {{ profile?.sportObject?.location?.address?.city }},
                        {{ profile?.sportObject?.location?.address?.country }}</label>
                </div>
            </div>
            <SportObjectSchedule :sportObject="profile.sportObject" />
            <CommentSection :sportObject="profile.sportObject" :comments="comments" />
        </div>
        <div class="content-separator">
            <label for="">Available content</label>
            <div class="arrow-down" @click="showContent = !showContent" :class="[showContent ? 'rotate' : '']">
            </div>
        </div>

        <div class="content-wrapper" v-show="showContent">
            <SportObjectContent v-for="content in profile.sportObject?.content" :key="content.name"
                :contentData="content" @displayEditForm='displayEditForm' />
            <div class="add-new-content">
                <img src="../../assets/imgs/add-content.png" alt="" width="140" @click="displayAddContentForm">
            </div>
        </div>
        <div class="user-separator">
            <label for="">Users who visited</label>
            <div class="arrow-down" @click="showUsers = !showUsers" :class="[showUsers ? 'rotate' : '']">
            </div>
        </div>
        <div class="content-wrapper" v-show="showUsers">
            <UserProfile v-for="user in usersVisited" :key="user.username" :profile="user" />

        </div>
        <div class="coach-separator">
            <label for=""> Coaches at this sport object</label>
            <div class="arrow-down" @click="showCoaches = !showCoaches" :class="[showCoaches ? 'rotate' : '']">
            </div>
        </div>
        <div class="content-wrapper" v-show="showCoaches">
            <UserProfile v-for="coach in coaches" :key="coach.username" :profile="coach" />
        </div>

        <div class="training-separator">
            <label for="">Trainings</label>
            <div class="arrow-down" @click="showTrainings = !showTrainings" :class="[showTrainings ? 'rotate' : '']">
            </div>
        </div>

        <section class="trainings-wrapper" v-show="showTrainings">
            <div v-for="training in trainings" :key="training.id" class="single-training">
                <label class="training-title" for="">{{ training.contentName }}</label>
                <label class="training-type" for="">{{ training.type }}</label>
                <label class="training-coach" for="">Coach: {{ training.coachUsername }}</label>
                <label class="training-buyer" for="">Buyer: {{ training.buyerUsername }}</label>
                <label class="training-date" for="">Date: {{ training.reservedAt }}</label>
                <label class="training-duration" for="">Duration: {{ training.duration }}min</label>
            </div>
        </section>

    </div>
</template>


<script>
export default {
    name: "ManagersSportObject",
    data: function () {
        return {
            base: "http://localhost:8000/api/",
            usersVisited: [],
            coaches: [],
            trainings: [],
            profileStore: null,
            displayEdit: false,
            displayAdd: false,
            clickedContent: {},
            comments: [],
            showContent: false,
            showUsers: false,
            showCoaches: false,
            showTrainings: false,
            workTimeStyle: {
                display: "flex",
                position: "unset",
                color: "#000",
                backgroundColor: "transparent",
                flexDirection: "column",
                justifyContent: "center",
                alignItems: "flex-start",
                width: "30%",
                fontSize: "18px",
                marginTop: "1rem"
            },
            singleWorkTimeStyle: {
                display: "flex",
                justifyContent: "space-between",
                width: "100%",
                padding: "10px 0"
            },
            titleStyle: {
                fontSize: "22px",
                justifyContent: "center",
                textAlign: "center",
                width: "100%"
            }
        }
    },
    mounted: async function () {
        this.profileStore = useProfileStore()
        await this.profileStore.getUserProfile();
        await this.getTrainings()
        let resp = await this.profileStore.captureManagerData()
        this.usersVisited = resp.usersVisited
        this.coaches = resp.coaches
        let resp1 = await axios.get('http://localhost:8000/api/objects/' + this.profile.sportObject.id + "/comments/all",
            {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("auth-token")
                }
            })
        if (resp1.status == 200) {
            this.comments = resp1.data
        }
    },
    computed: {
        ...mapState(useProfileStore, ['profile'])
    },
    methods: {
        displayEditForm: function (value) {
            this.clickedContent = value
            this.displayEdit = true;
        },
        displayAddContentForm: function () {
            this.displayAdd = !this.displayAdd
        },
        closeEditForm: function () {
            this.displayEdit = !this.displayEdit;
        },
        refresh: async function () {
            await this.profileStore.getUserProfile();
            this.displayAdd = false
            this.displayEdit = false
        },

        getTrainings: async function () {
            try {
                let resp = await axios.get(this.base + 'objects/training/manager', {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })
                this.trainings = resp.data
            } catch (e) {
                console.log(e)
            }
        }
    }
}
</script>

<style scoped>
@import "@/assets/base.css";

.container {
    display: flex;
    flex-direction: column;
    padding: 1rem;
}

.inner-container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
    padding: 1rem 3rem;
}

.time-and-map {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: 60%;
    height: 500px;
}

.map-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 15px;
    height: 400px;
    width: 60%;
    color: gray;
}

.content-wrapper {
    display: flex;
    justify-content: center;
    flex-wrap: wrap;
    gap: 1.5rem;
    margin-top: 1rem;
}

.add-new-content {
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

.add-new-content img:hover {
    cursor: pointer;
}

.title {
    font-size: 1.5rem;
}

.training-separator,
.user-separator,
.coach-separator,
.content-separator {
    display: flex;
    justify-content: center;
    align-items: center;
    gap: 1rem;
    font-size: 2rem;
    margin: 3rem 0;
    color: #ff7810;
}

.trainings-wrapper {
    display: flex;
    justify-content: center;

}

.single-training {
    display: flex;
    flex-direction: column;
    justify-content: space-around;
    gap: 5px;
    max-width: 20rem;
    min-height: 200px;
    box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
    padding: 2rem;
}

.training-title {
    font-size: 20px;
    color: #000;
}

.training-type,
.training-coach,
.training-buyer,
.training-date,
.training-duration {
    color: gray;
    font-size: 14px;
}

body .rotate {
    transform: rotate(180deg);
    transition-duration: .5s;
}

.arrow-down {
    width: 0;
    height: 0;
    border-left: 20px solid transparent;
    border-right: 20px solid transparent;
    border-top: 20px solid #c1c1c1;
    cursor: pointer;
    transform: rotate(0deg);
    transition-duration: .5s;
}
</style>