<template>
    <div class="medal-display-wrapper">
        <span class="points-wrapper">
            Points: {{ profile.points }}
        </span>
        <div class="medal-wrapper">
            <img v-if="profile.buyerType.name === 'GOLD'" src="../../assets/imgs/golden-medal.png" alt="" height="40px"
                width="40px">
            <img v-else-if="profile.buyerType.name === 'SILVER'" src="../../assets/imgs/silver-medal.png" alt=""
                height="40px" width="40px">
            <img v-else src="../../assets/imgs/bronze-medal.png" alt="" height="40px" width="40px">
        </div>
    </div>


</template>



<script>

export default {
    name: "MedalDisplay",
    props: {
        profile: {}
    },
    data: function () {
        return {

        }
    }
}
</script>

<style scoped>
.medal-display-wrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    min-height: 80px;
    position: absolute;
    top: 35px;
    right: 40px;
}

.medal-wrapper {
    width: 80px;
    height: 80px;
}

.points-wrapper {
    font-weight: bold;
    font-size: 1.5rem;

}

.medal-wrapper img {
    height: 100%;
    width: 100%;
}
</style>