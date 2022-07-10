<script setup>
</script>


<template>

    <footer class="footer-wrapper">
        <div class="company-info">
            <label for="" class="title">About company</label>
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
        <div class="company-wrapper">
            <span class="logo-wrapper">
                <img src="../../assets/imgs/logo.png" alt="">
            </span>
            &nbsp;
            &nbsp;
            <span class="company-title">Fit Pass</span>
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

footer {
    display: flex;
    flex-wrap: wrap;
    row-gap: 1rem;
    justify-content: space-around;
    background-color: #fafafa;
    padding: 3rem 1.4rem;
    min-height: 200px;
    width: 100%;
}

.company-info,
.interest,
.contact-info {
    display: flex;
    flex-direction: column;
    align-items: flex-start;
    justify-content: flex-start;
}

.title {
    font-size: 2rem;
    color: #000;
}

label {
    color: rgb(77, 77, 77);
    padding: 0.3rem 0;
}

label:hover {
    color: #000;
    cursor: pointer;
}

.logo-wrapper {
    width: 64px;
    height: 64px;
    display: flex;
    align-items: center;
    justify-content: center;
}

.logo-wrapper img {
    width: 100%;
    height: 100%;
}

.company-wrapper {
    display: flex;
    align-items: flex-start;
    background: #fafafa;
}

.company-title {
    font-size: 4rem;
    font-weight: bold;
}

hr {
    margin: auto;
    width: 75%;
    border: 1px solid lightgray;
}
</style>