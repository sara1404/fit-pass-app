import axios from 'axios'
import {
    defineStore
} from 'pinia'


export const useTrainingStore = defineStore({
    id: "trainingStore",
    state: () => {
            return {
                buyerTrainings : [],
                coachHistory: [],
                base: "http://localhost:8000/api/"
            }
    },
    getters: {
        getBuyerTrainings: (state) => state.buyerTrainings
    },
    actions: {
        async filterBuyerTrainings(params) {
            try {
                let resp = await axios.get(this.base + "objects/training/buyer/history", {
                    params,
                    headers: {
                        Authorization: this.createToken()
                    }
                })
                this.buyerTrainings = resp.data
                console.log(resp)
            } catch(e) {
                console.log(e)
            }
        },

        async getCoachTrainingReservations() {
            try {
                let resp = await axios.get(this.base + "objects/training/coach", this.createOptionsWithTokenHeader())
                return resp.data
            } catch(e) {
                console.log(e)
                return {
                    error: e.response.data
                }
            }
        },

        async getCoachTrainingHistory(params = {}) {
            try {
                let resp = await axios.get(this.base + "objects/training/coach/history", {
                    params,
                    headers: {
                        Authorization : this.createToken()
                    }
                })
                this.coachHistory = resp.data;
                return {
                    data: resp.data
                }

            }catch(e) {
                this.coachHistory = []
                return {
                    error: e.response.data
                }
            }
        },

        async cancelTraining(id) {
            try {
                let resp = await axios.patch(this.base + `objects/training/${id}/cancel`, {}, this.createOptionsWithTokenHeader())
                return {}
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },

        async getBuyerReservations() {
            try {
                let resp = await axios.get(`${this.base}objects/training/buyer/all`, this.createOptionsWithTokenHeader())
                return resp.data
            } catch(e) {
                return []
            }
        },

        async checkInTraining({reservation, sportObject}) {
            try {
                let resp = await axios.post(`${this.base}objects/training/${reservation.id}/checkIn`, {
                    objectId: sportObject.id,
                    name: reservation.contentName
                }, this.createOptionsWithTokenHeader())
                console.log(resp)
                if(resp.status == 200) {
                    return {}
                }
            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },

        async checkInForRegularTraining() {
            try {   
                let resp = await axios.patch(`${this.base}objects/checkIn`, {}, this.createOptionsWithTokenHeader())
                return {};

            } catch(e) {
                return {
                    error: e.response.data
                }
            }
        },
        createOptionsWithTokenHeader() {
            return {
                headers: {
                    Authorization: this.createToken()
                }
            }
        },
        createToken() {
            return "Bearer " + localStorage.getItem("auth-token")
        }



    }
})