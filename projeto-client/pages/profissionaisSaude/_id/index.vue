<template>
  <b-container>
    <h4>Profissional Saúde Details</h4>
    <p>Name: {{ profissional.name }}</p>
    <p>Email: {{ profissional.email }}</p>

    <div style="margin-top: 20px">
      <nuxt-link to="/profissionaisSaude">Back</nuxt-link>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deleteProfissional">Delete</button>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      profissional: {},
      profissionalFields: [ 'name', 'email' ],
      errorMsg: false,
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl(){
      return this.id + "/edit"
    },
  },
  methods:{
    deleteProfissional(){
      this.$axios.$delete(`api/profissionais/${this.id}`)
        .then(() => {
          this.$toast.success('Profissional de Saúde successfully deleted!').goAway(2000)
          this.$router.push('/profissionaisSaude')
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })
    }
  },
  created() {
    this.$axios.$get(`/api/profissionais/${this.id}`)
      .then((profissional) => {
        this.profissional = profissional || {}
      })
  },
}
</script>
