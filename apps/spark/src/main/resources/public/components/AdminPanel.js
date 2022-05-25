
import axios from "https://cdn.skypack.dev/axios";

let AdminPanelView = Vue.component("admin-panel", {

    data: function() {
        return {
            profile: {
                type: Object
            },
            users: []
        }
    },

    mounted: async function() {
        let resp = await axios.get("/api/users/me", {
            headers: {
                Authorization: "Bearer " + localStorage.getItem("auth-token")
            }
        })

        if(resp.status <= 300) {
            this.profile = resp.data
        }

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
    AdminPanelView
}