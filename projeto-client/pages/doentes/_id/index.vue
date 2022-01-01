<template>
  <b-container>
    <h4>Doente Details</h4>
    <p>Name: {{ doente.name }}</p>
    <p>Email: {{ doente.email }}</p>
    <p>Contact: {{ doente.contact }}</p>
    <p>Address: {{ doente.address }}</p>
    <p>Médico: {{ doente.profissionalEmail }}</p>

    <h4>Prescrições</h4>
    <b-table v-if="prescricoes.length" striped over :items="prescricoes" :fields="prescricoesFields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/api/prescricoes/${row.item.id}/doentes`">Details</nuxt-link>
      </template>
    </b-table>
    <p v-else>No prescrições associated.</p>

    <div style="margin-top: 20px">
      <button @click="back">Back</button>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <nuxt-link :to="newPrescricao">Associate doente with prescrição</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deleteDoente">Delete</button>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      doente: {},
      prescricoesFields: ['descricao', 'actions'],
      errorMsg: false,
    }
  },
  methods:{
    back(){
      this.$router.go(-1)
    },
    deleteDoente(){
      this.$axios.$delete(`api/doentes/${this.id}`)
        .then(() => {
          this.$toast.success('Doente successfully deleted!').goAway(2000)
          this.$router.push('/doentes')
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })
    },
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl(){
      return this.id + "/edit"
    },
    newPrescricao(){
      return this.id + "/associateDoenteWithPrescricao"
    },
    prescricoes() {
      return this.doente.prescricaoDTOList == null ? [] : this.doente.prescricaoDTOList
    }
  },
  created() {
    this.$axios.$get(`/api/doentes/${this.id}`)
      .then((doente) => {
        this.doente = doente || {}
      })
  },
}
</script>
