import { FitPassWelcome } from "./components/FitPassWelcomeView.js"
import { SecondViewTest } from "./components/SecondTestView.js"
import { AdminPanelView } from "./components/Admin/AdminPanel.js";
import { AdminRegister } from "./components/Admin/AdminRegister.js";
import {ProfileView} from "./components/Profile.js";
// const { FitPassWelcome } = { template: '<fitpass-welcome></fitpass-welcome>' }

const router = new VueRouter({
    mode:'hash',
    routes: [
        { path: '/', component: FitPassWelcome },
        { path: '/test', component: SecondViewTest },
        { path: '/admin/panel', component: AdminPanelView },
        { path: '/admin/register', component: AdminRegister},
        { path: '/profile', component: ProfileView, props: (route) => {
            profile: this.profile
        }}
    ]
})


var vue = new Vue({
    data: function() {
        return {
            showLoginForm: false,
            showRegisterForm: false,
            logged: false,
            profile: {
                username: "neko",
                name: "neko",
                surname: "neko"
            }
        }
    },

    methods: {
        userLogged: function(profile) {
            console.log("User logged event")
            this.profile = profile;
            this.logged = true;
            this.$router.push({path: '/profile'})
        }
    },

    mounted: function() {
        if(localStorage.getItem("auth-token")) {
            this.logged = true;
            console.log("Logged now")
        }
    },
    router,
    el: '#app'
})

window.vue = vue;

export {

}