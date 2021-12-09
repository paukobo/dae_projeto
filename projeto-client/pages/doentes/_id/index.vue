<template>
  <b-container>
    <h4>Doente Details</h4>
    <p>ID: {{ doente.id }}</p>
    <p>Name: {{ doente.name }}</p>
    <p>Email: {{ doente.email }}</p>
    <p>Contact: {{ doente.contact }}</p>
    <p>Address: {{ doente.address }}</p>

    <nuxt-link to="/doentes">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      doente: {},
      doentesFields: [ 'id', 'name', 'email', 'contact', 'address' ]
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
  },
  created() {
    this.$axios.$get(`/api/doentes/${this.id}`)
      .then((doente) => {
        this.doente = doente || {}
      })
  },
}
</script>
