import { FitPassWelcome } from "./components/FitPassWelcomeView.js"
import { SecondViewTest } from "./components/SecondTestView.js"
import { AdminPanelView } from "./components/Admin/AdminPanel.js";
import { AdminRegister } from "./components/Admin/AdminRegister.js";
// const { FitPassWelcome } = { template: '<fitpass-welcome></fitpass-welcome>' }

const router = new VueRouter({
    mode:'hash',
    routes: [
        { path: '/', component: FitPassWelcome },
        { path: '/test', component: SecondViewTest },
        { path: '/admin/panel', component: AdminPanelView },
        { path: '/admin/register', component: AdminRegister}
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