<script setup>
</script>


<template>
    <footer class="footer-wrapper">
        <div class="company-info">
            <label for="" class="title">About company</label>
            <label for="">FitPass</label>
            <label for="">Company users</label>
            <label for="">Partners</label>
            <label for="">Contact</label>
        </div>
        <div class="interest">
            <label for="" class="title">Could be interested</label>
            <label for="">Objects</label>
            <label for="">FAQ</label>
            <label for="">Selling terms</label>
        </div>
        <div class="contact-info">
            <label for="" class="title">Contact us</label>
            <label for="">Fit Sport d.o.o</label>
            <label for="">PIB</label>
            <label for="">E-mail: contact@fitpass.fit</label>
        </div>
    </footer>
</template>

<script>
export default {
  name: "Footer",
  
  
}

</script>

<style scoped>
@import "@/assets/base.css";
footer{
    display: flex;
    justify-content: space-around;
    background-color: #fafafa;
    padding: 1.7em 1.4em;
    height: 200px;
    width: 100%;
}

.company-info, .interest, .contact-info{
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
}

.title{
    font-size: 1.5em;
    color: #000;
}

label{
    color: rgb(77, 77, 77);
    padding: 0.3em;
}

label:hover{
    color: #000;
    cursor: pointer;
}
</style>