import { FitPassWelcome } from "./components/FitPassWelcomeView.js"
import { SecondViewTest } from "./components/SecondTestView.js"
import { AdminPanelView } from "./components/AdminPanel.js";
// const { FitPassWelcome } = { template: '<fitpass-welcome></fitpass-welcome>' }

const router = new VueRouter({
    mode:'hash',
    routes: [
        { path: '/', component: FitPassWelcome },
        { path: '/test', component: SecondViewTest },
        { path: '/admin/panel', component: AdminPanelView }
    ]
})


var vue = new Vue({
    router,
    el: '#app'
})

window.vue = vue;

export {

}