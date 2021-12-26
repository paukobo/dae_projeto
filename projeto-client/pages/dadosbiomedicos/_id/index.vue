<template>
  <b-container>
    <h2>Tipo Dado Biomedico Detalhes</h2>
    <p>Nome: {{ biomedico.nome }}</p>
    <p>Descrição: {{ biomedico.descricao }}</p>
    <p>Valor Min: {{ biomedico.valorMin }}</p>
    <p>Valor Max: {{ biomedico.valorMax }}</p>
    <p>Unidades: {{ biomedico.unidades }}</p>


    <h4>Dados Qualitativos</h4>
    <div v-if="valoresQualitativos.length > 0">
      <div v-for="valores in valoresQualitativos" :key="valores " style="margin-left: 20px">- {{valores}}</div>
    </div>
    <p v-else>Tipo Dado Biomedico não tem valores qualitativos.</p>

    <div style="margin-top: 20px">
      <nuxt-link to="/dadosbiomedicos">Back</nuxt-link>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deleteDado">Delete</button>
    </div>
  </b-container>


</template>

<script>
export default {
  data() {
    return {
      fields: ["valorQualitativo"],
      biomedico: {},
      errorMsg: false,
    }
  },
  methods:{
    deleteDado(){
      this.$axios.$delete(`api/dadosbiomedicos/${this.id}`)
      .then(() => {
        this.$router.push('/dadosbiomedicos')
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
    valoresQualitativos() {
      return this.biomedico.valoresQualitativos == null ? [] : this.biomedico.valoresQualitativos
    }
  },
  created() {
    this.$axios.$get(`api/dadosbiomedicos/${this.id}`)
      .then((biomedico) => {
        this.biomedico = biomedico || {}
      })
  },
}
</script>
