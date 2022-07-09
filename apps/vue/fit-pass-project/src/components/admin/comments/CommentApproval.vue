<template>
    <div class="comment-approveal-wrapper-inner">
        <div class="comment-approval-wrapper">
            <Comment :comment="comment.comment" />
        </div>
        <div class="sport-object-wrapper">
            {{ comment.sportObject.name }}
        </div>
        <div class="approval-wrapper">
            <span>
                <button class="approve" @click="this.$emit('approveComment', comment.comment.id)">Approve</button>
            </span>
            <span>
                <button class="decline" @click="this.$emit('declineComment', comment.comment.id)">Decline</button>
            </span>
        </div>
    </div>
</template>


<script>

import Comment from '../../custom/Comment.vue'

export default {
    name: "CommentApproval",
    components: {
        Comment
    },
    props: {
        comment: {}
    },
    data: function () {
        return {

        }
    }
}
</script>


<style scoped>
.comment-approveal-wrapper-inner {
    display: flex;
    align-items: center;
    justify-content: center;
    min-width: 400px;
    border: 1px solid lightgray;
    margin-top: 1rem;
}

.comment-approveal-wrapper-inner .sport-object-wrapper {
    font-weight: bold;
    padding: 0 1rem 0 1rem;
    font-size: 1rem;
    min-width: 150px;
}

.comment-approval-wrapper {
    width: 50%;
    min-width: 400px;
}

.approval-wrapper {
    display: flex;
}

.approval-wrapper span {
    padding: 0 1rem 0 1rem;
}

.approval-wrapper .approve {
    background: green;
    height: 50px;
    width: 200px;
    border: none;
    outline: none;
    color: white;
    cursor: pointer;
}

.approval-wrapper .decline {
    background: red;
    height: 50px;
    width: 200px;
    border: none;
    outline: none;
    color: white;
    cursor: pointer;
}
</style>