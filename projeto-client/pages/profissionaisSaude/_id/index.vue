<template>
  <b-container>
    <h4>Profissional Saúde Details</h4>
    <p>Name: {{ profissional.name }}</p>
    <p>Email: {{ profissional.email }}</p>

    <nuxt-link to="/profissionaisSaude">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      profissional: {},
      profissionalFields: [ 'name', 'email' ]
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
  },
  created() {
    this.$axios.$get(`/api/profissionais/${this.id}`)
      .then((profissional) => {
        this.profissional = profissional || {}
      })
  },
}
</script>
