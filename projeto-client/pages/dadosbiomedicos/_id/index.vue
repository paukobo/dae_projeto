<template>
  <b-container>
    <h2>Tipo Dado Biomedico Detalhes</h2>
    <label>Nome:</label>
    <b-input v-model="biomedico.nome" disabled></b-input>
    <label>Descrição:</label>
    <b-input v-model="biomedico.descricao" disabled></b-input>
    <label>Valor Min:</label>
    <b-input v-model="biomedico.valorMin" disabled></b-input>
    <label>Valor Max:</label>
    <b-input v-model="biomedico.valorMax" disabled></b-input>
    <label>Unidades:</label>
    <b-input v-model="biomedico.unidades" disabled></b-input>


    <h4>Dados Qualitativos</h4>

    <div v-if="qualitativos.length > 0">
      <div v-for="(dado, index) in qualitativos" style="display: grid;grid-template-columns: 40px 500px 40px 50px">
        <label style="grid-row-start:1; grid-column-start:2">{{index + 1}}º Qualitative Value</label>
        <b-input v-model="dado.dadoText" disabled required style="grid-row-start:2; grid-column-start:2"/>
      </div>

    </div>
    <p v-else>Tipo Dado Biomedico não tem valores qualitativos.</p>

    <div style="margin-top: 20px">
      <b-button to="/dadosbiomedicos">Voltar</b-button>
      <b-button variant="info" :to=editUrl>Edit</b-button>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button variant="danger" style="float: right" @click.prevent="deleteDado">Delete</b-button>
    </div>
  </b-container>


</template>

<script>
class Dado{
  constructor() {
    this.dadoText = '';
  }
}

export default {
  data() {
    return {
      biomedico: {},
      qualitativos: [],
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
    }
  },
  created() {
    this.$axios.$get(`api/dadosbiomedicos/${this.id}`)
      .then((biomedico) => {
        this.biomedico = biomedico || {}
        for(let i = 0; i < this.biomedico.valoresQualitativos.length; i++){
          let dado = Dado.constructor();
          dado.dadoText = this.biomedico.valoresQualitativos[i];
          this.qualitativos.push(dado)
        }
      })
  },
}
</script>
