import axios from "axios"
const base = "http://localhost:8000/api"

export const userFilterService =  {
    async fetchFilteredData(params = {}) {
        console.log(params)
        try {
            let resp = await axios.get(base + "/users/all", {
                    params,
                    headers: this.createFilterHeaders()
                }
            )
            return {
                data: resp.data,
            };
        }catch(e) {
            console.log(e);
            return {
                error: e.response.data
            }
        }
    },

    createFilterHeaders() {
        return {
            Authorization: "Bearer " + localStorage.getItem("auth-token")
        }
    }
}