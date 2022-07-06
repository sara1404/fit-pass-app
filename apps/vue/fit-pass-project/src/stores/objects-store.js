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

        async addNewSportObject(body={}, manager = ""){
            try{
                let resp = await axios.post(this.base + "objects/create", body, {
                    params: {
                      username: manager 
                    },
                    headers:{
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })

                return resp.data
            }catch(e){
                console.log(e)
                return {
                    error: e.response.data
                }
            }
        },

        async uploadLogo(id, file){
            let formData = new FormData();
            formData.append("file", file);

            try{
                await axios.post(`${this.base}objects/${id}/logo`, formData, {
                    headers:{
                        Authorization: "Bearer " + localStorage.getItem("auth-token"),
                        contentType: "multipart/form-data"
                    }
                })
            }catch(e){
                console.log(e)
            }
        },

        async addNewSportObjectContent (body = {}){
            try{
                let resp = await axios.post(this.base + "objects/content/add", body,{
                    headers: {
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })
            }catch(e){
                console.log(e)
            }
        },

        async editSportObjectContent(body = {}, name){
            try{
                let resp = await axios.put(this.base + "objects/content/" + name, body, {
                    headers:{
                        Authorization: "Bearer " + localStorage.getItem("auth-token")
                    }
                })
            }catch(e){
                console.log(e)
            }
        }

    }
})