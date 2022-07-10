<template>
    <tr class="subscription-row">
        <td class="arrow-td">
            <div class="arrow-down" @click="showAdditional = !showAdditional" :class="[showAdditional ? 'rotate' : '']">
            </div>
        </td>
        <td> {{ subscription.type }}</td>
        <td> {{ subscription.paymentDate }}</td>
        <td> {{ subscription.expirationDate }}</td>
        <td> {{ subscription.price }}</td>
        <td :style="[subscription.status === 'ACTIVE' ? { color: 'green' } : { color: 'red' }]">
            {{ subscription.status }}
        </td>
    </tr>
    <tr class="additional-subscriptions-wrapper" v-show="showAdditional">
        <td colspan="6" class="inner-table-wrapper">
            <table>
                <thead>
                    <tr>
                        <th>Name</th>
                        <th>Type</th>
                        <th>Price</th>
                        <th>Trainings Left</th>
                    </tr>
                </thead>
                <tbody>
                    <AdditionalSub v-for="sub in subscription.additionalSubs" :sub="sub" />
                </tbody>
            </table>
        </td>
    </tr>
</template>



<script>

import AdditionalSub from './AdditionalSub.vue'

export default {
    name: "Subscription",
    components: {
        AdditionalSub
    },
    props: {
        subscription: {
            additionalSubs: []
        }
    },
    data: function () {
        return {
            showAdditional: false
        }
    }
}

</script>



<style scoped>
.inner-table-wrapper {
    /* padding: 2rem 0; */
}

.inner-table-wrapper table {
    width: 100%;
    border-collapse: collapse;
    border-bottom: 1px solid lightgray;
    border-left: 1px solid lightgray;
    border-right: 1px solid lightgray;
}

.inner-table-wrapper table tr {
    text-align: center;
    font-size: 1.4rem;
}

.inner-table-wrapper table th {
    height: 3rem;
    width: 25%;
}

.subscription-row {
    border: 1px solid #f7f7f7;
    font-size: 1.5rem;
    height: 4rem;
    text-align: center;
}

.arrow-td {
    display: flex;
    align-items: center;
    justify-content: center;
    height: 100%;
}

.arrow-down {
    width: 0;
    height: 0;
    border-left: 20px solid transparent;
    border-right: 20px solid transparent;
    border-top: 20px solid #c1c1c1;
    cursor: pointer;
    transform: rotate(0deg);
    transition-duration: .5s;
}

.rotate {
    transform: rotate(180deg);
    transition-duration: .5s;
}
</style>