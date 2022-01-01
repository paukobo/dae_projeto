<template>
  <div class="overflow-auto">
    <div v-if="rows == 0">
      <h2>Sem dados!</h2>
    </div>
    <div v-else>
      <p class="mt-3">Current Page: {{ currentPage }}</p>

      <b-table
        id="my-table"
        :items="dados"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        small
      ></b-table>

      <b-pagination
        v-model="currentPage"
        :total-rows="rows"
        :per-page="perPage"
        aria-controls="my-table"
      ></b-pagination>
    </div>
    <nuxt-link :to="`/doentes/${id}`">Back</nuxt-link>
    <nuxt-link :to="`/doentes/${id}/dadosbiomedicos/create`">Create a New Dado Biomedico</nuxt-link>
  </div>
</template>

<script>
export default {
  name: "dadosbiomedicos",
  data(){
    return{
      perPage: 10,
      currentPage: 1,
      dados: [],
      fields: ['nome', 'valorQuantitativo','valorQuantitativo', 'unidade', 'valorQualitativo', 'date']
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    rows() {
      return this.dados.length
    }
  },
  created() {
    this.$axios.$get(`/api/doentes/${this.id}/dadosbiomedicos`)
      .then((dados) => {
        console.log(dados)
        this.dados = dados
      })
  },
}
</script>

<style scoped>

</style>
