let menuComponent = Vue.component("fitpass-menu", {
    data: function() {
        return {
            showLogin: false
        }
    },
    props: {
        logged: {
            type: Boolean,
            default: false
        },
        profile:{
            type: Object,
            default: {}
        }
    },
    methods:{
        displayLogin : function(){
            this.$emit("display-login");
        },
        displayRegister : function(){
            this.$emit("display-register");
        }

    },
    template:
`
<nav>
    <ul>
        <li>Profile</li>
        <li>Trainings</li>
        <li>Contact</li>
        <li>About us</li>
    </ul>
    <div class="menuBtnWrapper">
        <button  id="loginBtn" v-on:click="displayLogin">SIGN IN</button>

        <button id="registerBtn" v-on:click="displayRegister">SIGN UP</button>
    </div>
    
</nav>    
    
`
})


window.menuComponent = menuComponent