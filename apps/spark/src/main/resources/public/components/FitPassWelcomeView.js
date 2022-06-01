import axios from "https://cdn.skypack.dev/axios"

let FitPassWelcome = Vue.component("fitpass-welcome", {
    data: function() {
        return {
            username: "",
            password: ""

        }
    },
    template: `
    <div style="display:flex; align-items: center; height: 100vh" >
        <h1 style="margin: auto;"> Welcome to FitPass Application</h1>
    </div>
`,

    methods: {
        login: async function(e) {
            e.preventDefault();
            let body = {
                username: this.username,
                password: this.password
            }

            let resp = await axios.post("/api/auth/login", body, {
                headers: {
                    "Content-Type": "application/json"
                },

            })


            console.log(resp);
            if(resp.data.token) {
                localStorage.setItem("auth-token", resp.data.token);
                this.$router.push("admin/panel")
            }

        }
    }
})



export { FitPassWelcome }