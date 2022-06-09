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
    let body = {
      username: "srdjan123",
      password: "123"
    }
      fetch("http://localhost:8000/api/auth/login", {
        body: JSON.stringify(body),
        method: "POST",
        headers: {
          contentType: "application/json"
        }
      }).then(res => res.json())
      .then(data => {
        console.log(data);
      })
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