<template>
    <div class="sub-wrapper-outer">
        <div class="sub-wrapper">
            <Subscription v-for="sub in subs" :subscription="sub" />
        </div>
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
</style>