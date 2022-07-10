<template>
    <tr class="additional-sub-row">
        <td>{{ sub.type }}</td>
        <td>{{ sub.contentName }}</td>
        <td>{{ sub.price }}</td>
        <td>{{ sub.appointmentsLeft }}</td>
    </tr>
</template>




<script>
export default {
    name: "AdditionalSub",
    props: {
        sub: {},
    }
}


</script>



<style scoped>
.additional-sub-row {
    height: 3rem;
    border-top: 1px solid #f7f7f7;
}

.additional-sub-row td {
    width: 25%;
}
</style>