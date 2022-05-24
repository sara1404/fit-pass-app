import { FitPassWelcome } from "./components/FitPassWelcomeView.js"
import { SecondViewTest } from "./components/SecondTestView.js"
// const { FitPassWelcome } = { template: '<fitpass-welcome></fitpass-welcome>' }

const router = new VueRouter({
    mode:'hash',
    routes: [
        { path: '/', component: FitPassWelcome},
        { path: '/test', component: SecondViewTest}
    ]
})


var vue = new Vue({
    router,
    el: '#app'
})

window.vue = vue;

export {

}