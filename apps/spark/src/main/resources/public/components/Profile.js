
import axios from "https://cdn.skypack.dev/axios";

let ProfileView = Vue.component("profile-view", {

    data: function() {
        return {
        }
    },
    props: [
        "profile"
    ],

    mounted: async function() {
        // let resp = await axios.get("/api/users/me", {
        //     headers: {
        //         Authorization: "Bearer " + localStorage.getItem("auth-token")
        //     }
        // }).catch(err => {
        //     console.log("some error happened")
        //     console.log(err)
        // })
        //
        // if(resp.status <= 300) {
        //     this.profile = resp.data
        // }

    },


    template:
        `
<div>
      <p>{{profile.username}}</p>
      <p>{{profile.name}}</p>     
      <p>{{profile.surname}}</p>     
</div>
    `
})


export {
    ProfileView
}