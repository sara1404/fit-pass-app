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
    data: function() {
        return {
            showLoginForm: false,
            showRegisterForm: false,
            logged: false,
            profile: {}
        }
    },

    methods: {
        userLogged: function(profile) {
            this.profile = profile;
            this.logged = true;
        }
    },

    mounted: function() {
        if(localStorage.getItem("auth-token")) {
            this.logged = true;
        }
    },
    router,
    el: '#app'
})

window.vue = vue;

export {

}