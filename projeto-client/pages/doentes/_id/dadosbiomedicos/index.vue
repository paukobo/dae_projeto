<template>
  <div class="overflow-auto">
    <div v-if="rows == 0">
      <br>
      <h2>Sem dados!</h2>
      <br>
    </div>
    <div v-else>
      <label>Tipo Dado</label>
      <b-select v-model="tipoId" :options="tipos" value-field="id" text-field="nome">
        <template v-slot:first>
          <option :value="null">-- Sem Filtro --</option>
        </template>
      </b-select>

      <p class="mt-3">Pagina: {{ currentPage }}</p>
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
    <b-button variant="secondary" @click.prevent="back">Voltar</b-button>
    <b-button variant="success"@click.prevent="addDado"><b-icon icon="plusCircle"/>Adicionar Dado Biomedico</b-button>
  </div>
</template>

<script>
import {BIconPlusCircle,BIcon, BIconPlus, BIconDash} from 'bootstrap-vue'

export default {
  name: "dadosbiomedicos",
  components:{
    BIcon,
    BIconPlus,
    BIconDash,
    BIconPlusCircle
  },
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
    addDado(){
      this.$router.push(`/doentes/${this.id}/dadosbiomedicos/create`)
    },
    back(){
      this.$router.push(`/doentes/${this.id}`)
    },
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
