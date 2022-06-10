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
        getLoggedIn: (state) => state.loggedIn
    },
    actions: {
        async login (body) {
            this.profile = {
                username: "srdjan123",
                password: "123",
            
            }
            this.loggedIn = true;
            // console.log(body)
            // try {
            //     let resp = await axios.post("http://localhost:8000/api/auth/login", body, {
            //         headers: {
            //             contentType: 'application/json'
            //         }
            //     })
            //     if(resp.status == 200) {
            //         localStorage.setItem("auth-token", resp.data.token)
            //         // this.getUserProfile()

            //     }

            // } catch (e) {
            //     console.log(e)
            // }
        },

        async getUserProfile () {
            let token = localStorage.getItem('auth-token')
            if(!token) return
            try{
                let resp = await axios.get("http://localhost:8000/api/users/me", {
                    headers: {
                        Authorization: "Bearer " + token
                    }
                })
                if(resp.status == 200) {
                    this.profile = resp.data
                }
            }catch(e){
                console.log(e)
            }
        }

    }
})