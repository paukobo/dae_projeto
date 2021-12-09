<template>
  <b-container>
    <h4>Admin Details</h4>
    <p>ID: {{ admin.id }}</p>
    <p>Name: {{ admin.name }}</p>
    <p>Email: {{ admin.email }}</p>

    <nuxt-link to="/admins">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      admin: {},
      adminFields: [ 'id', 'name', 'email' ]
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
  },
  created() {
    this.$axios.$get(`/api/admins/${this.id}`)
      .then((admin) => {
        this.admin = admin || {}
      })
  },
}
</script>
