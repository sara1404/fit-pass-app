<script setup>
import Comment from '@/components/custom/Comment.vue'
import { useProfileStore } from "@/stores/profile-store.js"
import { mapState } from "pinia"
</script>

<template>
    <section class="comments">
    <div class="comment-title-wrapper">
        <label class="comment-title" for="">Comments and grades ({{commentsSize}})</label>
        <img src="../../assets/imgs/add-comment.png" alt="" height="40" width="40" @click="addCommentClicked=true;" v-show="visited">
    </div>
    <div class="add-comment" v-show="addCommentClicked">
        <input type="text" placeholder="Your comment..." v-model="comment">
        <input type="number" min="1" max="5" v-model="grade">
        <button class="submit-btn" @click="submitCommentAndGrade">Submit</button>
    </div>
    <div class="average-grade">
        <img src="../../assets/imgs/star.png" alt="" height="50" width="50">
        <label for="">{{sportObject.averageMark}}</label>

    </div>
    <Comment :comment="com" v-for="com in comments" :key="com.id"/>
    </section>
</template>

<script>
export default {
    name: "CommentSection",
    props: {
        sportObject: {},
        comments: [],
    },
    data: function(){
        return{
            commentsSize: "0",
            addCommentClicked: false,
            visited: false,
            comment:"", 
            grade:"",
        }
    },
    mounted:function(){
        this.commentsSize = this.comments.length
        this.setVisited()
    },
    computed: {
      ...mapState(useProfileStore, ['profile'])
    },
    methods:{
        calculateAverageGrade: function(){
            let total = 0 
            for(comment in this.comments){
                total += comment.grade
            }
            return total/this.comments.length
        },

        setVisited: function(){
            if(this.profile.role !== "BUYER"){
                this.visited = false
                return
            }
            for(let obj of this.profile.visitedObjects){
                if(obj.id === this.sportObject.id){
                    this.visited = true
                }
            }
        },
    }
}
</script>

<style scoped>

.comments{
  display: flex;
  flex-direction: column;
  padding: .5rem 0;
  width: 60%;
}

.comment-title-wrapper{
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid lightgray;
  font-size: 1.5rem;
  padding: .5rem;
}

.add-comment{
  display: flex;
  flex-direction: column;
  align-items: flex-end;
  gap: .5rem;
  margin: .5rem 0;
  border-bottom: 1px solid lightgray;
  padding: .5rem;

}

.add-comment input{
  width: 98%;
  height: 4rem;
  outline: none;
  border: 1px solid lightgray;
  border-radius: .3rem;
  padding-left: 10px;
}

.submit-btn{
  background-color: #ff7810;
  color: #fff;
  outline: none;
  border: none;
  height: 2rem;
  width: 4rem;
  border-radius: 10px;
  cursor: pointer;
}

.comment-title-wrapper img{
  cursor: pointer;
}

.average-grade{
  display: flex;
  align-items: center;
  border-bottom: 1px solid lightgray;
  font-size: 2rem;
  gap: 1rem;
  padding: .5rem 0;
}

</style>