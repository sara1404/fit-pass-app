import * as Vue from 'vue'
import { createPinia } from 'pinia'
import axios from 'axios'
import VueAxios from 'vue-axios'
import App from './App.vue'
import router from './router'
import VueToast from 'vue-toast-notification';
// Import one of the available themes
//import 'vue-toast-notification/dist/theme-default.css';
import 'vue-toast-notification/dist/theme-sugar.css';


const app = Vue.createApp(App)

app.use(VueToast)
app.use(VueAxios, axios)
app.use(createPinia())
app.use(router)

app.mount('#app')


