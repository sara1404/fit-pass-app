let menuComponent = Vue.component("fitpass-menu", {
    data: function() {
        return {

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
</nav>    
    
`
})


window.menuComponent = menuComponent