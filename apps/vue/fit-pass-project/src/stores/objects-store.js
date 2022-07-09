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
                    error: e
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
                await axios.post(this.base + "objects/content/add", body, this.createHeadersWithToken())
                return {}
            }catch(e){
                return {
                    error: e.response.data
                }
            }
        },

        async editSportObjectContent(body = {}, name){
            try{
                let resp = await axios.put(this.base + "objects/content/" + name, body, this.createHeadersWithToken())
            }catch(e){
                console.log(e)
            }
        },

        async getAdditionalTrainingSessions(objectId) {
            console.log(objectId)
            try {
                let resp = await axios.get(`${this.base}objects/${objectId}/training/additional`, this.createHeadersWithToken())
                console.log(resp)
                return resp.data

            } catch(e) {
                console.log(e)
                return []
            }
        },

        async buyExtraTrainingPackage(content) {
            try {
                let resp = await axios.post(`${this.base}objects/training/extras`, content, this.createHeadersWithToken())
                return resp.data
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },

        async reserveGroupTraining(content) {
            try {
                let resp = await axios.post(`${this.base}objects/training/reserve/group`, content, this.createHeadersWithToken())
                return resp.data
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },
        async reservePersonalTraining(content) {
            try {
                let resp = await axios.post(`${this.base}objects/training/reserve`, content, this.createHeadersWithToken())
                return resp.data
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },
        async getAllGroupTrainingContent(objectId) {
            try {
                let resp = await axios.get(`${this.base}objects/${objectId}/training/additional/group`,this.createHeadersWithToken())
                return resp.data
            } catch(e) {
                console.log(e)
                return []
            }
        },
        async getAllPersonalTrainingContent(objectId) {
            try {
                let resp = await axios.get(`${this.base}objects/${objectId}/training/additional/personal`,this.createHeadersWithToken())
                console.log(objectId, resp)
                return resp.data
            } catch(e) {
                console.log(e)
                return []
            }
        },

        async getAllUndecidedComments() {
            try {
                let resp = await axios.get(`${this.base}objects/comments/undecided`, this.createHeadersWithToken())
                return resp.data
            } catch(e) {
                console.log(e)
                return []
            }
        },

        async approveComment(id) {
            try {
                let resp = await axios.patch(`${this.base}objects/comments/${id}/approve`, {}, this.createHeadersWithToken())
                return {}
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },
        async declineComment(id) {
            try {
                let resp = await axios.patch(`${this.base}objects/comments/${id}/decline`, {}, this.createHeadersWithToken())
                return {}
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },

        async deleteComment(id) {
            try {
                let resp = await axios.delete(`${this.base}objects/comments/${id}/delete`, this.createHeadersWithToken())
                return {}
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },
        createHeadersWithToken() {
            return {
                headers: {
                    Authorization: "Bearer " + localStorage.getItem('auth-token')
                }
            }
        }

    }
})