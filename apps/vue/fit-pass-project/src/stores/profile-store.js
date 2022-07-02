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
            profiles: [],
            coaches:[],
            subscriptions:[],
        }
    },
    getters: {
        getLoggedIn: (state) => state.loggedIn,
        getBaseUrl: (state) => state.base,
        getLoggedProfile: (state) => state.profile,
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

        async addPromoCode(body){
            try{
                let resp = await axios.post(this.base + "admin/promos", body, this.createHeadersWithToken())
                return {}
            }catch(e){
                console.log(e)
                return {
                    error: e.response.data
                }
            }
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

        async editUserProfile(body){
            let token = this.createBearerToken()
            if(!token) return
            try{
                let resp = await axios.put(this.base + "users/edit", body, this.createHeadersWithToken())
                if(resp.status === 200){
                    console.log(this.profile)
                    this.profile = {
                        ...this.profile,
                        ...body
                    }
                    console.log(this.profile)
                }
            }catch(e){
                console.log(e.message)
            }
        },

        async tryAlreadyLoggedIn() {
            let token = this.createBearerToken()
            if(!token) {
                this.profile = {}
                this.loggedIn = false
                return
            }
            if(this.loggedIn) return
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

        async captureAllCoaches(){
            try{
                let resp = await axios.get(this.base + "users/coaches", this.createHeadersWithToken())
                this.coaches = resp.data
            } catch(e){
                this.coaches = []
                console.log(e)
            }
        },

        async captureAllSubscriptions(){
            try{
                let resp = await axios.get(this.base + "users/subscriptions", this.createHeadersWithToken())
                this.subscriptions = resp.data
                console.log(this.subscriptions)
            }catch(e){
                this.subscriptions = []
                console.log(e)
            }
        },

        async buySubscription(body){
            try{
                await axios.post(this.base + "/users/subscription", body, this.createHeadersWithToken())
            }catch(e){
                console.log(e)
            }
        },

        logout() {
            localStorage.removeItem("auth-token")
            this.loggedIn = false;
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
        async fetchFilteredData(params = {}) {
            console.log(params)
            try {
                let resp = await axios.get(this.base + "users/all", {
                        params,
                        headers: {...this.createAuthorizationHeader()}
                    }
                )
                console.log(resp)
                this.profiles = resp.data
            }catch(e) {
                console.log(e);
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
        },

        createAuthorizationHeader() {
            return {
                Authorization: this.createBearerToken()
            }
        }


    }
})