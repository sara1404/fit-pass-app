<template>

    <div class="subscription-wrapper">
        <div class="main-subscription-wrapper">
            <span class="sub-data" @click="showAdditional = !showAdditional">
                <div class="arrow-wrapper">
                    <img src="../../assets/imgs/arrow-bottom.png" alt=""
                        :class="[showAdditional ? 'rotate' : 'rotate-oposite']" />
                    <!-- v-if="showAdditional === false || subscription.additionalSubs.length == 0"> -->
                    <!-- <img src="../../assets/imgs/arrow-top.png" alt="" v-else> -->
                </div>
                <span>
                    <span class="data-label">
                        Type:
                    </span>
                    <span class="data-wrapper">
                        {{ subscription.type }}
                    </span>
                </span>
                <span>
                    <span class="data-label">
                        Payment Date:
                    </span>
                    <span class="data-wrapper">
                        {{ subscription.paymentDate }}
                    </span>
                </span>
                <span>
                    <span class="data-label">
                        Expiration Date:
                    </span>
                    <span class="data-wrapper">
                        {{ subscription.expirationDate }}
                    </span>
                </span>
                <span>
                    <span class="data-label">
                        Price:
                    </span>
                    <span class="data-wrapper">
                        {{ subscription.price }}
                    </span>
                </span>
            </span>
        </div>
        <div class="additional-subs-wrapper" :class="[showAdditional ? 'slide-down' : '']">
            <AdditionalSub v-for="addSub in subscription.additionalSubs" :sub="addSub" />
        </div>


    </div>
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
.sub-data {
    display: flex;
    align-items: center;
    flex-wrap: wrap;
    gap: 2rem;
    box-shadow: 0 0 3px rgba(0, 0, 0, 0.3);
    padding: 1rem;
}

.sub-data span {
    min-width: 250px;
    font-size: 2rem;
    font-weight: bold;
    color: rgb(66, 66, 66);
}

.arrow-wrapper {
    cursor: pointer;
}

.rotate-oposite {
    transition: all .5s ease-out;
    transform: rotate(0deg);
}


.additional-subs-wrapper {
    transition: max-height 1s ease-out;
    max-height: 0;
    overflow: hidden;

}

.rotate {
    transition: all .5s ease-out;
    transform: rotate(180deg);
}

.slide-down {
    transition: max-height 1s ease-out;
    max-height: 500px;

}
</style>