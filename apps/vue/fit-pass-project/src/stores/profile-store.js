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
            profile: {}
        }
    },
    getters: {
        getLoggedIn: (state) => state.loggedIn,
        getBaseUrl: (state) => state.base
    },
    actions: {
        async login (body) {
            console.log(body)
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

        async getUserProfile () {
            let token = this.createBearerToken()
            if(!token) return
            try{
                let resp = await axios.get(this.base + "users/me", {
                    headers: {
                        Authorization: token
                    }
                })
                if(resp.status == 200) {
                    this.profile = resp.data
                }
            }catch(e){
                console.log(e)
            }
        },

        createBearerToken() {
            let token = localStorage.getItem('auth-token')
            if(!token) return null
            return "Bearer " + token
        }


    }
})