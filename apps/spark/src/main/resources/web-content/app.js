import { FitPassWelcome } from "./components/FitPassWelcomeView.js"

// const { FitPassWelcome } = { template: '<fitpass-welcome></fitpass-welcome>' }

const router = new VueRouter({
    mode:'hash',
    routes: [
        { path: '/', component: FitPassWelcome}
    ]
})


var vue = new Vue({
    router,
    el: '#app'
})

window.vue = vue;

export {

}