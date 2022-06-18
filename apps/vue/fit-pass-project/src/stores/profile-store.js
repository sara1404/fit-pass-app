import axios from 'axios'
import {
    defineStore
} from 'pinia'


export const useProfileStore = defineStore({
    id: 'profileStore',
    state: () => {
        return{
            loggedIn: false,
            base: "http://localhost:8000/api/",
            profile: {},
            profiles: []
        }
    },
    getters: {
        getLoggedIn: (state) => state.loggedIn,
        getBaseUrl: (state) => state.base,
        getAllProfiles: function(state) {
            if (this.mockServerForRole() !== "ADMIN") return []
            return state.profiles;
        }
    },
    actions: {
        async login (body) {
            try {
                let resp = await axios.post(this.base +  "auth/login", body, {
                    headers: {
                        contentType: 'application/json'
                    }
                })
                if(resp.status === 200) {
                    localStorage.setItem("auth-token", resp.data.token)
                    await this.getUserProfile()
                    this.loggedIn = true;
                }
            } catch (e) {
                console.log(e)
            }
        },

        async registerUserAsAdmin(body) {
            await axios.post(this.base + "admin/register", body, this.createHeadersWithToken())
        },

        async registerBuyer(body) {
            await axios.post(this.base + "auth/register", body)
        },

        async getUserProfile () {
            let token = this.createBearerToken()
            if(!token) return
            try{
                let resp = await axios.get(this.base + "users/me", this.createHeadersWithToken())
                if(resp.status === 200) {
                    this.profile = resp.data
                }
            } catch(e){
                console.log(e)
            }
        },

        async tryAlreadyLoggedIn() {
            let token = this.createBearerToken()
            if(!token) return;
            await this.getUserProfile()
            this.loggedIn = true;
        },

        async captureAllProfiles() {
            try {
                let resp = await axios.get(this.base + "users/all", this.createHeadersWithToken())
                this.profiles = resp.data
            } catch(e) {
                this.profiles = []
                console.log(e)
            }
        },

        async mockServerForRole() {
            let token = this.createBearerToken()
            if(!token) return
            try {
                let resp = await axios.get(this.base + "users/role", this.createHeadersWithToken())
                return resp.data.role
            } catch(e) {
                console.log(e)
                return null
            }
        },

        createBearerToken() {
            let token = localStorage.getItem('auth-token')
            if(!token) return null
            return "Bearer " + token
        },

        createHeadersWithToken() {
            return {
                headers: {
                    Authorization: this.createBearerToken()
                }
            }
        }


    }
})