import axios from "https://cdn.skypack.dev/axios";

let AdminRegister = Vue.component("admin-register", {
    data: function() {
        return {
            isManager: true,
            role: "MANAGER",
            username: "",
            name: "",
            surname: "",
            password: "",
            sex: "MALE",
            sportObject: "",
            birthDate: null
        }
    },
    template:
        `
        <div class="admin-register-form">
            <h2>Register {{role}}!</h2>
            <form action="api/admin/register" method="POST">
                <div class="username-wrapper wrapper">
                    <input type="text" name="username" placeholder="Username" v-model="username">
                </div>
                <div class="name-wrapper wrapper">
                    <input type="text" name="name" placeholder="Name" v-model="name">
                </div>
                <div class="surname-wrapper wrapper">
                    <input type="text" name="surname" placeholder="Surname" v-model="surname">
                </div>
                <div class="password-wrapper wrapper">
                    <input type="password" name="password" placeholder="Password" v-model="password">
                </div>
                <div class="sex-wrapper wrapper">
                    <select name="sex" v-model="sex">
                        <option value="MALE">MALE</option>
                        <option value="FEMALE">FEMALE</option>
                    </select>
                </div>
                <div class="birthDate-wrapper wrapper">
                    <input type="date" name="birthDate">
                </div>
                <div class="role-wrapper wrapper" >
                    <select v-model="role" name="role">
                        <option value="MANAGER">MANAGER</option>
                        <option value="COACH">COACH</option>
                    </select>
                </div>
                <div class="sport-object-wrapper wrapper" v-show="role=='MANAGER'">
                    <input type="text" name="sportObject" placeholder="Sport Object Username" v-model="sportObject">
                </div>
                
                <button type="submit" v-on:click="register">Submit</button>
            </form>
            
        </div>
        `,
        methods: {
            register: async function(e) {
                e.preventDefault();

                let body = {
                    username: this.username,
                        name: this.name,
                        surname: this.surname,
                        password: this.password,
                        sex: this.sex,
                        role: this.role,
                        birthDate: this.birthDate,
                        sportObject: this.sportObject
                }

                let resp = await axios.post("api/admin/register", body, {
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })

                if(resp.status < 400) {
                    alert("Successfully registered!");
                }
            }
        }


})




export {
    AdminRegister
}