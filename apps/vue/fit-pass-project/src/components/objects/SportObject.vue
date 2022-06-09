<template>
    <div>
      <img v-bind:src="'src/assets/imgs/' + objectForChild.logo">
      <p>{{objectForChild.type}}</p>
    </div>
</template>

<script>
export default {
  name: "SportObject",
  props: {
    objectForChild: Object
    },
  data: function(){
    return{
    }
  },
  methods: {
    printObject: function() {
      console.log(this.objectForChild)
    }
  },
  mounted: function(){
  
  }
}

</script>

<style>
@import "@/assets/base.css";

</style>