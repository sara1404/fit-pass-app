import axios from "https://cdn.skypack.dev/axios";

let registerComponent = Vue.component("register-form", {
    data: function () {
        return {
            username:"",
            password:"",
            name:"",
            surname:"",
            birthDate:"",
            sex: "MALE"
        }
    },
    methods: {
        closeRegisterForm: function () {
            this.$emit("form-closed");
        },

        register: function() {

        }

        // register: async function() {
        //     let body = {
        //         username: this.username,
        //         password: this.password
        //     }

        //     let resp = await axios.post("/api/auth/login", body, {
        //         headers: {
        //             "Content-Type": "application/json"
        //         },

        //     })


        //     console.log(resp.data);
        //     if(resp.data.token) {
        //         localStorage.setItem("auth-token", resp.data.token);
        //         this.$emit("form-closed");
        //         this.$emit("user-logged", await this.loadProfileData())
        //         this.$router.push("admin/panel")
        //     }

        // },

        // loadProfileData: async function() {
        //     let resp = await axios.get("/api/users/me", {
        //         headers: {
        //             Authorization: "Bearer " + localStorage.getItem("auth-token")
        //         }
        //     })

        //     if(resp.data) {
        //         return resp.data
        //     }
        //     return {}
        // }
    },
    template: `
<div class="register-wrapper">
    <div class="registerCloseBtn" v-on:click="closeRegisterForm">
        <img src="../imgs/icons8-close-24.png"/>
    </div>
    <label class="title">Register</label>
    <div class="username-wrapper">
        <input type="text" placeholder="Username" v-model="username"/>
    </div>
    <div class="name-wrapper">
        <input type="text" placeholder="Name" v-model="name"/>
    </div>
    <div class="surname-wrapper">
        <input type="text" placeholder="Surname" v-model="surname"/>
    </div>
    <div class="birthDate-wrapper">
        <input type="date" placeholder="Birth date" v-model="birthDate"/>
    </div>
    <div class="password-wrapper">
        <input type="password" placeholder="Password" v-model="password"/>
    </div>
    <div class="sex-wrapper">
        <div>
            <input type="radio" name="sex" value="MALE" v-model="sex"/>
            <label>Male</label>
        </div>
        <div>
            <input type="radio" name="sex" value="FEMALE" v-model="sex"/>
            <label>Female</label>
        </div>
    </div>

    <div class="registerBtn-wrapper">
        <button v-on:click="register">SIGN UP</button>
    </div>
</div>   
    
`
})


window.registerComponent = registerComponent