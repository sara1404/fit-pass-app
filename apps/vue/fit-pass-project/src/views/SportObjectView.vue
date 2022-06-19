<script setup>
    import axios from 'axios'
</script>


<template>
    <p>lol</p>
</template>

<script>
export default {
  name: "SportObjectView",
  data: function(){
    return{
        sportObject : Object,
    }
  },
  mounted: async function(){
    try{
        let resp = await axios.get('http://localhost:8000/api/objects/' + this.$route.params.id)
        if(resp.status == 200)
            this.sportObject = resp.data
    }catch(e){
        console.log(e.message)
    }
  }
}

</script>

<style scoped>
@import "@/assets/base.css";

</style>