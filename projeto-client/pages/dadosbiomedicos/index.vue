<template>
  <div>
    <h1 style="text-align: center">Dados Biomedicos</h1>
    <b-container>
      <b-table striped over :items="biomedicos" :fields="fields">
        <template v-slot:cell(valoresQualitativos)="row">
          <span v-for="(dado, index) in row.item.valoresQualitativos">
            <span  v-if="index != row.item.valoresQualitativos.length -1">{{dado}}, </span>
            <span v-else>{{dado}}</span>
          </span>

        </template>
        <template v-slot:cell(actions)="row">
          <b-button
            variant="info"
            :to="`/dadosbiomedicos/${row.item.id}`">
            <b-icon icon="eyeFill"></b-icon>
          </b-button>
        </template>
      </b-table>
      <b-button variant="secondary" to="/">Voltar</b-button>
      <b-button variant="success" @click.prevent="routeCreate">Criar Novo Tipo Dado Biomedico</b-button>
    </b-container>

  </div>

</template>
<script>
import { BIcon, BIconPlus, BIconDash, BIconEyeFill } from 'bootstrap-vue'


export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash,
    BIconEyeFill
  },
  data () {
    return {
      fields: ['nome', 'descricao', 'valorMin','valorMax', 'unidades', 'valoresQualitativos', 'actions'],
      biomedicos: []
    }
  },
  created () {
    this.$axios.$get('api/dadosbiomedicos')
      .then((biomedicos) => {
        console.log(biomedicos)
        this.biomedicos = biomedicos
      })
  },
  methods:{
    routeCreate(){
      this.$router.push("/dadosbiomedicos/create")
    }
  }
}
</script>
<style></style>
