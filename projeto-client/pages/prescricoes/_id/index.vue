<template>
  <b-container>
    <h4>Prescrições Details</h4>
    <p>Descrição: {{ prescricao.descricao }}</p>
    <p>Data Inicio: {{ prescricao.dataInicio }}</p>
    <p>Data Fim: {{ prescricao.dataFim }}</p>
    <p>Duração: {{ prescricao.duracao }}</p>

    <h4>Planos</h4>
    <b-table v-if="planos.length" striped over :items="planos" :fields="planoFields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/api/prescricoes/${row.item.id}/doentes`">Details</nuxt-link>
      </template>
    </b-table>
    <p v-else>No planos associated.</p>

    <div style="margin-top: 20px">
      <nuxt-link to="/prescricoes">Back</nuxt-link>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <nuxt-link :to="newPlano">Associate plano with prescrição</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deletePrescricao">Delete</button>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      prescricao: {},
      planoFields: [ 'descricao', 'duracao' ],
      errorMsg: false,
    }
  },
  methods:{
    deletePrescricao(){
      this.$axios.$delete(`api/prescricoes/${this.id}`)
        .then(() => {
          this.$toast.success('Prescrição successfully deleted!').goAway(2000)
          this.$router.push('/prescricoes')
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
    newPlano(){
      return this.id + "/associatePlanoWithPrescricao"
    },
    planos() {
      console.log(this.prescricao)
      return this.prescricao.planosDTOList == null ? [] : this.prescricao.planosDTOList
    }
  },
  created() {
    this.$axios.$get(`/api/prescricoes/${this.id}`)
      .then((prescricoes) => {
        this.prescricao = prescricoes || {}
      })
  },
}
</script>
