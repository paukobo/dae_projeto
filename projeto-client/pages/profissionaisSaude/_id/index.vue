<template>
  <b-container>
    <h4>Profissional Saúde Details</h4>
    <p>Name: {{ profissional.name }}</p>
    <p>Email: {{ profissional.email }}</p>

    <h4>Doentes</h4>
    <b-table v-if="doentes.length" striped over :items="doentes" :fields="doenteFields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/doentes/${row.item.email}`">Details</nuxt-link>
      </template>
    </b-table>
    <p v-else>No doentes associated.</p>

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
      doenteFields: [ 'name', 'email', 'contact', 'actions' ],
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
    doentes() {
      return this.profissional.doentesDTOList || []
    }
  },
  methods:{
    deleteProfissional(){
      this.$axios.$delete(`api/profissionaisSaude/${this.id}`)
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
    this.$axios.$get(`/api/profissionaisSaude/${this.id}`)
      .then((profissional) => {
        this.profissional = profissional || {}
      })
  },
}
</script>
