import axios from "https://cdn.skypack.dev/axios";

let loginComponent = Vue.component("login-form", {
    data: function () {
        return {
            username:"",
            password:""
        }
    },
    methods: {
        closeLoginForm: function () {
            this.$emit("form-closed");
        },

        login: async function() {
            let body = {
                username: this.username,
                password: this.password
            }

            let resp = await axios.post("/api/auth/login", body, {
                headers: {
                    "Content-Type": "application/json"
                },

            })


            if(resp.data.token) {
                localStorage.setItem("auth-token", resp.data.token);
                this.$emit("form-closed");
                this.$emit("user-logged", await this.loadProfileData())
            }

        },

        loadProfileData: async function() {
            let resp = await axios.get("/api/users/me", {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem("auth-token")
                }
            })

            if(resp.data) {
                console.log(resp.data)
                return resp.data
            }
            return {
                username: "NO_DATA",
                name: "NO_DATA",
                surname: "NO_DATA",
            }
        }
    },
    template: `
<div class="login-wrapper">
    <div class="loginCloseBtn" v-on:click="closeLoginForm">
        <img src="../imgs/icons8-close-24.png"/>
    </div>
    <label>Login</label>
    <div class="username-wrapper">
        <input type="text" placeholder="Username" v-model="username"/>
    </div>
    <div class="password-wrapper">
        <input type="password" placeholder="Password" v-model="password"/>
    </div>
    <div class="loginBtn-wrapper">
        <button v-on:click="login">LOGIN</button>
    </div>
</div>   
    
`
})


window.loginComponent = loginComponent