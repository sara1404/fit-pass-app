import { defineStore } from 'pinia'


export const useTestStore = defineStore({
    id: 'test',
    state: () => ({
        message: "User logged",
        loggedIn : false
      }),
      getters: {
        doubleCount: (state) => state.message
      },
      actions: {
        printMessage() {
          alert(this.message)
        },
       
      }
})