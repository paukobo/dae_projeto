<template>
  <div>
  <h1>Add a new Dado Biomedico Info</h1>

  <form @submit.prevent="create" :disabled="!isFormValid" style="margin-left: 30px">
    <b-form-group>
      <label>Dado Type</label>
      <b-select v-model="tipoId" :options="tipos"
                :state="isTipoValid" required value-field="id" text-field="nome">
        <template v-slot:first>
          <option :value="null">-- Please select the Type --</option>
        </template>
      </b-select>
      <div v-if="isTipoValid">
        <br>
        <div v-if="selectedTipo.valorMax != 0 && selectedTipo.valorMin != 0">
          <label>Quantitative Value([{{this.selectedTipo.valorMin}} - {{this.selectedTipo.valorMax}}] {{this.selectedTipo.unidades}})</label>
          <b-input type="number" :state="isValueValid" v-model.trim="valor"/>
          <br>
        </div>
        <div v-if="selectedTipo.valoresQualitativos != null && selectedTipo.valoresQualitativos.length !== 0">
          <label>Qualitative Value</label>
          <b-select v-model="valorQualitativo" :options="selectedTipo.valoresQualitativos"
                    :state="isQualitativeValid" required>
            <template v-slot:first>
              <option :value="null">-- Please select the Qualitative Value --</option>
            </template>
          </b-select>
          <br>
        </div>
        <label>Date</label>
        <b-input type="date" v-model.trim="date" :state="isDateValid" :max="today" required/>
        <br>
        <label>Time</label>
        <b-input type="time" v-model.trim="time" :state="isTimeValid" required/>
      </div>

    </b-form-group>
    <p v-show="errorMsg">{{ errorMsg }}</p>
    <nuxt-link :to="`/doentes/${id}/dadosbiomedicos`">Return</nuxt-link>
    <button type="reset" @click="reset">RESET</button>
    <button @click.prevent="create"
            :disabled="!isFormValid">CREATE</button>
  </form>
  </div>
</template>

<script>
export default {
  name: "create",
  data(){
    return {
      tipos: [],
      tipoId: null,
      valor: null,
      valorQualitativo: null,
      date: null,
      errorMsg: false,
      time: null
    }
  },
  created() {
    this.$axios.$get(`/api/dadosbiomedicos`)
      .then((tipos) => {
      this.tipos = tipos
    })
  },
  methods: {
    reset(){
      this.valor = null
      this.valorQualitativo = null
      this.tipoId = null
      this.date = null
      this.time = null
    },
    create(){

      let data
      if(this.valor == null){
        this.valor = 0
      }
      data =  {
        tipoId: this.tipoId,
        valorQuantitativo: this.valor,
        valorQualitativo: this.valorQualitativo,
        date: this.date.replaceAll("-","/") + " " + this.time,
        }

      this.$axios.$post(`/api/doentes/${this.id}/dadosbiomedicos`, data)
        .then(() => {
          this.$router.push(`/doentes/${this.id}/dadosbiomedicos`)
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })
    }
  },
  computed: {
    id(){
      return this.$route.params.id
    },
    today(){
      let today = new Date();
      let dd = today.getDate();
      let mm = today.getMonth() + 1; //January is 0!
      let yyyy = today.getFullYear();

      if (dd < 10) {
        dd = '0' + dd;
      }

      if (mm < 10) {
        mm = '0' + mm;
      }

      return yyyy + '-' + mm + '-' + dd;
    },
    selectedTipo(){
      if(!this.tipoId || this.tipoId == null){
        return null
      }
      return this.tipos.find((element)=> element.id === this.tipoId)
    },
    isTipoValid () {
      if(this.selectedTipo == null){
        return false
      }
      return true
    },
    isValueValid(){
      if(this.selectedTipo.valorMax == 0 && this.selectedTipo.valorMin == 0){
        return true
      }
      if(this.valor == null){
        return false
      }
      return !(this.valor < this.selectedTipo.valorMin || this.valor > this.selectedTipo.valorMax);

    },
    isQualitativeValid(){
      if(this.selectedTipo.valoresQualitativos == null && this.selectedTipo.valoresQualitativos.length === 0){
        return true
      }
      if (!this.valorQualitativo) {
        return false
      }
      return this.selectedTipo.valoresQualitativos.some(valor => this.valorQualitativo === valor)
    },
    isDateValid(){
      return this.date != null
    },
    isTimeValid(){
      return this.time != null
    },
    isFormValid () {
      if (! this.isTipoValid) {
        return false
      }
      if(!this.isValueValid){
        return false
      }
      if(!this.isQualitativeValid){
        return false
      }
      if(!this.isDateValid){
        return false
      }
      return this.isTimeValid;

    }
  }
}
</script>

<style scoped>

</style>
