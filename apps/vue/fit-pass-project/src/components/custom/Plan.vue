<template>
    <div class="container">
        <label for="">{{ plan.type }} - {{ plan.duration }} days</label>
        <label for="">Allowed enters per day: {{ plan.allowedEntersPerDay }}</label>
        <label for="">{{ plan.price }} RSD</label>
    </div>
</template>

<script>
export default {
    name: "Plan",
    props: {
        plan: Object
    },
    data: function () {
        return {

        }
    }
}
</script>

<style scoped>
@import "@/assets/base.css";

.container {
    width: 300px;
    height: 400px;
}
</style>