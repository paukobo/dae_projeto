<template>
  <div>

    <h1>Create a new Dado Biomedico</h1>

    <form @submit.prevent="create" :disabled="!isFormValid" style="margin-left: 30px">
      <b-form-group
        id="name"
        description="The name is required"
        label="Enter the name"
        label-for="name"
        :invalid-feedback="invalidNameFeedback"
        :state="isNameValid">
        <b-input id="name" v-model.trim="name" :state="isNameValid" trim/>
        <br>
        <label>Description</label>
        <b-input  v-model="description"  required/>
        <br>
        <label>Minimum value</label>
        <b-input type="number" :state="isValuesValid" v-model.trim="valorMin"/>
        <br>
        <label>Max value</label>
        <b-input type="number" v-model.trim="valorMax" :state="isValuesValid"/>
        <br>
        <label>Unidades</label>
        <b-input v-model.trim="unidades" required/>
        <br>
        <label>Qualitative Values</label>
        <div v-for="(dado, index) in qualitativos" style="display: grid;grid-template-columns: 40px 500px 40px 50px">
          <label style="grid-row-start:1; grid-column-start:2">{{index + 1}}º Qualitative Value</label>
          <b-input v-model.trim="dado.dadoText" required style="grid-row-start:2; grid-column-start:2"/>
          <b-button v-on:click="remove(index)" style="grid-row-start:2; grid-column-start:4"><b-icon icon="dash"/></b-button>
        </div>
        <b-button style="margin: 20px 0 0 40px"v-on:click="add"><b-icon icon="plus"/></b-button>

      </b-form-group>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <nuxt-link to="/dadosbiomedicos">Return</nuxt-link>
      <button type="reset" @click="reset">RESET</button>
      <button @click.prevent="create"
              :disabled="!isFormValid">CREATE</button>
    </form>
  </div>
</template>

<script>
import { BIcon, BIconPlus, BIconDash } from 'bootstrap-vue'

class Dado{
  constructor() {
    this.dadoText = '';
  }
}
export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash
  },
  data () {
    return {
      name: null,
      description: null,
      valorMin: null,
      valorMax: null,
      unidades: null,
      errorMsg: false,
      qualitativos: []
    }
  },

  computed: {
    isMinValFiled(){
      if(this.valorMin == null || this.valorMin == String() ){
        return false
      }
      return true
    },
    isMaxValFiled(){

      if(this.valorMax == null || this.valorMax == String() ){
        return false
      }
      return true
    },
    isValuesValid(){
      if(this.isMinValFiled && this.isMaxValFiled){
        return Number(this.valorMax) >= Number(this.valorMin);

      }
      return !this.isMinValFiled && !this.isMaxValFiled;

    },
    invalidNameFeedback () {
      if (!this.name) {
        return null
      }
      let usernameLen = this.name.length
      if (usernameLen < 3 || usernameLen > 25) {
        return 'The Name must be between [3, 25] characters.'
      }

      return ''
    },

    isDescriptionValid () {
      if (!this.description) {
        return null
      }
      let nameLen = this.description.length
      return !(nameLen < 3 || nameLen > 50);

    },
    isNameValid () {
      if (!this.name) {
        return null
      }
      let nameLen = this.name.length
      return !(nameLen < 3 || nameLen > 25);

    },

    isFormValid () {

      if (! this.isNameValid) {
        return false
      }

      if (! this.isDescriptionValid) {
        return false
      }
      if(!this.isValuesValid || ((!this.isMinValFiled && !this.isMaxValFiled) && this.qualitativos.length == 0)){
        return false
      }
      return true
    }
  },

  methods: {
    reset () {
      this.errorMsg = false
      this.qualitativos.splice(1,this.qualitativos.length -1)
    },

    create() {
      let data
      let maxValue = 0
      let minValue = 0
      if(this.isMaxValFiled){
        maxValue = this.valorMax
      }
      if(this.isMinValFiled){
        minValue = this.valorMin
      }

      if(this.qualitativos.length == 0){
        data =  {
          nome: this.name,
          descricao: this.description,
          valorMin: minValue,
          valorMax: maxValue,
          unidades: this.unidades,
        }
      }else{

        let i = 0
        let array = []
        for(i; i < this.qualitativos.length; i++){

          array.push(this.qualitativos[i].dadoText)
        }
        data =  {

          nome: this.name,
          descricao: this.description,

          valorMin: minValue,
          valorMax: maxValue,
          unidades: this.unidades,
          valoresQualitativos: array,
        }
      }
        this.$axios.$post('/api/dadosbiomedicos',   data)
        .then(() => {
          this.$router.push('/dadosbiomedicos')
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })

    },
    remove(index) {
      this.qualitativos.splice(index,1)
    },
    add(){
      this.qualitativos.push(Dado.constructor())
    }
  }
}
</script>
