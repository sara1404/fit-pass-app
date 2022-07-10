<template>
    <div class="profile-wrapper">
        <ProfileEditing />
    </div>

</template>



<script>
import ProfileEditing from '../../custom/ProfileEditing.vue'
export default {
    name: "AdminProfile",
    components: {
        ProfileEditing
    }
}


</script>


<style scoped>
.profile-wrapper {
    max-width: 100%;
    min-height: 100%;
    height: 100%;
    padding: 1.5em;
}
</style>   