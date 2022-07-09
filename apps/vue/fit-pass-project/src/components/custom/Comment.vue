<template>
    <div class="wrapper">
        <div class="delete-icon" v-if="profile.role === 'ADMIN'">
            <img src="../../assets/imgs/delete.png" @click="deleteComment" alt="">
        </div>
        <div class="header">
            <div class="grade">
                {{ comment.grade }}
            </div>
            <div class="username">
                {{ comment.buyerUsername }}
            </div>
        </div>
        <div class="comment-content">
            "{{ comment.comment }}"
        </div>
    </div>
</template>

<script>
import { mapState } from 'pinia'
import { useProfileStore } from '../../stores/profile-store'
import { sportObjectsStore } from '../../stores/objects-store'
import { useToast } from 'vue-toast-notification'

export default {
    name: "Comment",
    created: function () {
        this.objectsStore = sportObjectsStore()
        this.toast = useToast()
    },
    props: {
        comment: Object,
    },
    data: function () {
        return {
            objectsStore: null,
            toast: null
        }
    },
    computed: {
        ...mapState(useProfileStore, ['profile'])
    },
    methods: {
        deleteComment: async function () {
            let resp = await this.objectsStore.deleteComment(this.comment.id)
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }
            this.toast.success("Sucessfully deleted comment!")
        }
    }

}
</script>

<style scoped>
@import "@/assets/base.css";

.wrapper {
    position: relative;
    display: flex;
    flex-direction: column;
    min-height: 4rem;
    justify-content: space-around;
    align-items: flex-start;
    border-bottom: 1px solid lightgray;
    padding: .5rem;
}

.header {
    display: flex;
    gap: .7rem;
}

.grade {
    background-color: #ff7810;
    padding: 3px 7px;
    color: #fff;
    border-radius: 7px;
}

.username {
    font-size: 1.2rem;
    color: rgb(104, 104, 104);
}

.comment-content {
    color: rgb(99, 99, 99);
}

.delete-icon img {
    max-width: 100%;
    max-height: 24px;
    cursor: pointer;
}

.delete-icon {
    align-self: center;
    align-items: center;
    justify-content: center;
    display: flex;
    flex: 1;
    height: 100%;
    width: 24px;
    position: absolute;
    top: 0;
    right: 24px;
}
</style>