<template>
    <div class="additional-sub">
        <span class="add-sub-data">
            <span>Type: {{ sub.type }}</span>
            <span>Name: {{ sub.contentName }}</span>
            <span>Price: {{ sub.price }}</span>
            <span>Trainings left: {{ sub.appointmentsLeft }}</span>
        </span>
    </div>
</template>




<script>
export default {
    name: "AdditionalSub",
    props: {
        sub: {}
    }
}

</script>



<style scoped>
.additional-sub {
    display: flex;
    flex-wrap: wrap;
    padding: 1rem;
    border-bottom: 1px solid lightgray;
}

.add-sub-data {
    display: flex;
    gap: 2rem;
    font-size: 1.5rem;
    color: rgb(131, 131, 131);
}

.add-sub-data span {
    min-width: 400px;
}
</style>