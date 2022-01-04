<template>
  <b-container class="overflow-auto">
    <h1 style="text-align: center">Dados Biomédicos Doente {{id}}</h1>
    <div v-if="rows == 0">
      <br>
      <h2>Sem dados!</h2>
      <br>
    </div>
    <div v-else>
      <div style="display: flex; gap: 100px">
        <div>
          <label>Data Inicio</label>
          <b-input type="date" v-model.trim="dateI" :max="dateF"/>
        </div>
        <div>
          <label>Data Fim</label>
          <b-input type="date" v-model.trim="dateF" :min="dateI"/>
        </div>
      </div>
      <br>
      <label>Tipo Dado</label>
      <b-select v-model="tipoId" :options="tipos" value-field="id" text-field="nome">
        <template v-slot:first>
          <option :value="null">-- Sem Filtro Tipo--</option>
        </template>
      </b-select>
      <br>
      <div v-if="tipoId != null && selectedTipo.valoresQualitativos.length != 0">
        <label>Valor Qualitativo</label>
        <b-select v-model="filtroQualitativo" :options="selectedTipo.valoresQualitativos">
          <template v-slot:first>
            <option :value="null">-- Sem Filtro Qualitativo--</option>
          </template>
        </b-select>
      </div>
      <br>
      <div>

        <b-table
          id="my-table"
          :items="dados"
          :fields="fields"
          :per-page="perPage"
          :current-page="currentPage"
          :filter="filter"
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
      <div>
        <div v-if="tipoId == null">
          <h3>Filtre por tipo para mais informação!</h3>
        </div>
        <div v-else-if="showData">
          <b-card style="max-width: 400px;">
            <form>
              <h4><strong>Média:</strong> {{ media }} {{selectedTipo.unidades}}</h4>
            <h4><strong>Maior Medida</strong>: {{maiorMedida}} {{selectedTipo.unidades}}</h4>
            <h4><strong>Menor Medida</strong>: {{menorMedida}} {{selectedTipo.unidades}}</h4>
            </form>
          </b-card>
          <br>
        </div>
        <div v-else>
          <br>
          <h2>Sem dados!</h2>
          <br>
        </div>
      </div>
    </div>



    <b-button variant="secondary" @click.prevent="back">Voltar</b-button>
    <b-button variant="success"@click.prevent="addDado"><b-icon icon="plusCircle"/>Adicionar Dado Biomedico</b-button>
    <br style="margin-bottom: 50px">

  </b-container>
</template>

<script>
import {BIconPlusCircle,BIcon, BIconPlus, BIconDash} from 'bootstrap-vue'
import moment from "moment";

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
      fields: ['nome', 'valorQuantitativo', 'unidade', 'valorQualitativo', 'date'],
      media: 0,
      maiorMedida: 0,
      menorMedida: 0,
      filtroQualitativo: null,
      showData: true,
      dateF: null,
      dateI: null
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    rows() {
      return this.dados.length
    },
    selectedTipo(){
      if(!this.tipoId || this.tipoId == null){
        return null
      }
      return this.tipos.find((element)=> element.id === this.tipoId)
    },
    filter(){
      if (this.tipoId === null && this.filtroQualitativo ===  null && this.dateF == null && this.dateI == null){
        return null;
      }
      return [this.tipoId , this.filtroQualitativo, this.dateI, this.dateF];
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
      if(filter[2] != null && moment(filter[2],'YYYY-MM-DD').diff(moment(row.date, 'YYYY/MM/DD')) > 0){
        return false
      }
      if(filter[3] != null && moment(filter[3],'YYYY-MM-DD').diff(moment(row.date, 'YYYY/MM/DD')) < 0){
        return false
      }

      if(filter[0] == null){
        return true
      }else if(row.tipoId == filter[0]){
        if(filter[1] == null){
          return true
        }
        else if(row.valorQualitativo == filter[1]){
          return true
        }
        return false
      }else{
        return false
      }
    },
    onFiltered(filteredItems) {
      // Trigger pagination to update the number of buttons/pages due to filtering
      this.totalRows = filteredItems.length
      this.currentPage = 1
      let valueArray = []

      if(filteredItems.length != 0){
        this.showData = true
        let soma = 0
        this.menorMedida = filteredItems[0].valorQuantitativo
        this.maiorMedida = filteredItems[0].valorQuantitativo
        for(let i in filteredItems){
          let item = filteredItems[i]
          soma += item.valorQuantitativo;
          if(item.valorQuantitativo > this.maiorMedida){
            this.maiorMedida = item.valorQuantitativo
          }
          if(item.valorQuantitativo < this.menorMedida){
            this.menorMedida = item.valorQuantitativo
          }
        }

        this.media = Math.round(soma/filteredItems.length * 100) / 100
      }else{
        this.showData = false
      }
    }
  }
}
</script>

<style scoped>

</style>
