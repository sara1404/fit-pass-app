<template>
    <div class="sub-wrapper-outer">
        <table class="subscription-table">
            <thead>
                <tr>
                    <th></th>
                    <th>Type</th>
                    <th>Payment Date</th>
                    <th>Expiration Date</th>
                    <th>Price(RSD)</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <Subscription v-for="sub in subs" :subscription="sub" />
            </tbody>
        </table>
    </div>


</template>



<script>
import Subscription from '../Subscription.vue'
import { useProfileStore } from '../../../stores/profile-store'

export default {
    name: "SubscriptionsView",
    components: {
        Subscription
    },
    created: async function () {
        this.profileStore = useProfileStore()
        this.subs = await this.profileStore.getSubscriptionHistory()
    },
    data: function () {
        return {
            subs: [],
            profileStore: null
        }
    }
}
</script>



<style scoped>
.sub-wrapper-outer {
    min-width: 100%;
    min-height: 100vh;
    display: flex;
    align-items: flex-start;
    justify-content: center;
    margin: auto;
    background-color: #fafafa;
    padding-top: 2rem;
}

.subscription-table {
    table-layout: fixed;
    border-collapse: collapse;
    width: 80%;
    max-width: 80%;
    height: 80%;
    background-color: white;

}

.subscription-table th {
    height: 4rem;
    font-size: 1.5rem;
}
</style>