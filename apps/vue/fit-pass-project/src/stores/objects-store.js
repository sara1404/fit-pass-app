import axios from 'axios'
import {
    defineStore
} from 'pinia'


export const sportObjectsStore = defineStore({
    id: 'objectsStore',
    state: () => {
        return{
            base: "http://localhost:8000/api/",
            sportObjects : [],
        }
    },
    getters: {
        getBaseUrl: (state) => state.base
    },
    actions: {
        async getSportObjects (params = {}) {
            try {
                let resp = await axios.get(this.base +  "objects/all", {
                    params,
                    headers: {
                        contentType: 'application/json'
                    }
                })
                if(resp.status === 200) {
                    this.sportObjects = resp.data
                    console.log(this.sportObjects)
                }

            } catch (e) {
                console.log(e)
            }
        },

        async addNewSportObjectContent (body = {}){
            try{
                let resp = await axios.post(this.base + "/manager/objects/content", body,{
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })
            }catch(e){
                console.log(e)
            }
        }

    }
})