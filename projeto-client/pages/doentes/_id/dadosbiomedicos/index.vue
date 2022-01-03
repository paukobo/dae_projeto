<template>
  <div class="overflow-auto">
    <div v-if="rows == 0">
      <h2>Sem dados!</h2>
    </div>
    <div v-else>
      <label>Dado Type</label>
      <b-select v-model="tipoId" :options="tipos" value-field="id" text-field="nome">
        <template v-slot:first>
          <option :value="null">-- No Type Filter --</option>
        </template>
      </b-select>

      <p class="mt-3">Current Page: {{ currentPage }}</p>
      <b-table
        id="my-table"
        :items="dados"
        :fields="fields"
        :per-page="perPage"
        :current-page="currentPage"
        :filter="String(tipoId)"
        :filter-function="filterTipo"
        @filtered="onFiltered"
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
      tipos: [],
      tipoId: null,
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
        this.dados = dados
      })
    this.$axios.$get(`/api/dadosbiomedicos`)
      .then((tipos) => {
        this.tipos = tipos
      })
  },
  methods:{
    filterTipo(row, filter){
      if(filter == "null"){
        return true
      }else if(row.tipoId == filter){
        return true
      }else{
        return false
      }
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length
      this.currentPage = 1
    }
  }
}
</script>

<style scoped>

</style>
