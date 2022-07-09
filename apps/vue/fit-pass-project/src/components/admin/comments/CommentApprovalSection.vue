<template>
    <h2> Comments Approval: </h2>
    <div class="comment-approval-wrapper-outer">
        <CommentApproval v-for="(comment, index) in comments" :key="index" :comment="comment"
            @approveComment="approveComment" @declineComment="declineComment" />
    </div>
</template>


<script>
import { sportObjectsStore } from "../../../stores/objects-store"
import CommentApproval from "./CommentApproval.vue"
import { useToast } from "vue-toast-notification"
export default {
    name: "CommentApprovalSection",
    components: {
        CommentApproval
    },
    created: async function () {
        this.objectsStore = sportObjectsStore()
        this.toast = useToast()
        this.comments = await this.objectsStore.getAllUndecidedComments()
    },
    data: function () {
        return {
            comments: [],
            objectsStore: null,
            toast: null
        }
    },
    methods: {
        approveComment: async function (id) {
            let resp = await this.objectsStore.approveComment(id)
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }

            this.toast.success("Sucessfully approved comment!")
            this.comments = await this.objectsStore.getAllUndecidedComments()
        },
        declineComment: async function (id) {
            let resp = await this.objectsStore.declineComment(id)
            if (resp.error) {
                this.toast.error(resp.error)
                return
            }

            this.toast.success("Sucessfully declined comment!")
            this.comments = await this.objectsStore.getAllUndecidedComments()
        }
    }
}
</script>


<style scoped>
h2 {
    margin: 2rem 0 0 2rem;
    font-weight: bold;
    font-size: 1.5rem;
}

.comment-approval-wrapper-outer {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: center;
    padding: 1rem 2rem 2rem 2rem;
}
</style>