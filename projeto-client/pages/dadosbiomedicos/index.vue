<template>
  <div>
    <h1>Dados Biomedicos</h1>
    <b-container>
      <b-table striped over :items="biomedicos" :fields="fields">
        <template v-slot:cell(valoresQualitativos)="row">
          <span v-for="(dado, index) in row.item.valoresQualitativos">
            <span  v-if="index != row.item.valoresQualitativos.length -1">{{dado}}, </span>
            <span v-else>{{dado}}</span>
          </span>

        </template>
        <template v-slot:cell(actions)="row">
          <nuxt-link
            class="btn btn-link"
            :to="`/dadosbiomedicos/${row.item.id}`">Details</nuxt-link>
        </template>
      </b-table>

    </b-container>
    <nuxt-link to="/">Back</nuxt-link>
    <nuxt-link to="/dadosbiomedicos/create">Create a New Dado Biomedico</nuxt-link>
  </div>
</template>
<script>
export default {
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
  }
}
</script>
<style></style>
