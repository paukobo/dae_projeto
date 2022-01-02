<template>
  <b-container>
    <h4>Plano Details</h4>
    <p>Descrição: {{ plano.descricao }}</p>
    <p>Duração: {{ plano.duracao }}</p>

    <h4>Prescrições</h4>
    <b-table v-if="prescricoes.length" striped over :items="prescricoes" :fields="prescricaoFields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/api/prescricoes/${row.item.id}/doentes`">Details</nuxt-link>
      </template>
    </b-table>
    <p v-else>No prescrições associated.</p>

    <div style="margin-top: 20px">
      <nuxt-link to="/planos">Back</nuxt-link>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deletePlano">Delete</button>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      plano: {},
      prescricaoFields: [ 'descricao', 'duracao' ],
      doenteFields: ['name', 'email', 'contact', 'address'],
      errorMsg: false,
    }
  },
  methods:{
    deletePlano(){
      this.$axios.$delete(`api/planos/${this.id}`)
        .then(() => {
          this.$toast.success('Plano successfully deleted!').goAway(2000)
          this.$router.push('/planos')
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl(){
      return this.id + "/edit"
    },
    prescricoes() {
      return this.plano.prescricaoDTOList == null ? [] : this.plano.prescricaoDTOList
    },
  },
  created() {
    this.$axios.$get(`/api/planos/${this.id}`)
      .then((planos) => {
        this.plano = planos || {}
      })
  },
}
</script>
