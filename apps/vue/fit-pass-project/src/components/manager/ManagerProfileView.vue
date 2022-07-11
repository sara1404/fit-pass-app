<template>
    <div class="profile-wrapper">
        <ProfileEditing />
    </div>

</template>



<script>
import ProfileEditing from '../custom/ProfileEditing.vue'
export default {
    name: "ManagerProfileView",
    components: {
        ProfileEditing
    }
}


</script>


<style scoped>
.profile-wrapper {
    display: flex;
    justify-content: center;
    align-items: center;
    max-width: 100%;
    min-height: 80vh;
    height: 100%;
    padding: 1.5em;
}
</style>   