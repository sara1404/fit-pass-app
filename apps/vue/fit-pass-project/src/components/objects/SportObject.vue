<template>
    <p>{{objectForChild.name}}</p>
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
.objects-container{
    width: 100%;
    height: 100%;
}
</style>