<template>
  <div>

    <h1>Edit Dado Biomedico</h1>

    <form @submit.prevent="create" :disabled="!isFormValid" style="margin-left: 30px">
      <b-form-group
        id="name"
        description="The name is required"
        label="Name"
        label-for="name"
        :invalid-feedback="invalidNameFeedback"
        :state="isNameValid">
        <b-input id="name" v-model.trim="name" :state="isNameValid" trim/>
        <br>
        <label>Description</label>
        <b-input  v-model="description"  required/>
        <br>
        <label>Minimum value</label>
        <b-input type="number" :state="isMinValValid" v-model.trim="valorMin"/>
        <br>
        <label>Max value</label>
        <b-input type="number" v-model.trim="valorMax" :state="isMaxValValid"/>
        <br>
        <label>Unidades</label>
        <b-input v-model.trim="unidades" required/>
        <br>
        <label>Qualitative Values</label>
        <div v-for="(dado, index) in biomedicos" style="display: grid;grid-template-columns: 40px 500px 40px 50px">
          <label style="grid-row-start:1; grid-column-start:2">{{index + 1}}º Qualitative Value</label>
          <b-input v-model.trim="dado.dadoText" required style="grid-row-start:2; grid-column-start:2"/>
          <b-button v-on:click="remove(index)" style="grid-row-start:2; grid-column-start:4"><b-icon icon="dash"/></b-button>
        </div>
        <b-button style="margin: 20px 0 0 40px"v-on:click="add"><b-icon icon="plus"/></b-button>

      </b-form-group>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <a href="#" @click.prevent="back()">Cancel</a>
      <button type="reset" @click="reset">RESET</button>
      <button @click.prevent="create"
              :disabled="!isFormValid">SAVE</button>
    </form>
  </div>
</template>

<script>
import { BIcon, BIconPlus, BIconDash } from 'bootstrap-vue'

class Dado{
  constructor() {
    this.dadoText = "";
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
      biomedicos: [Dado.constructor()],
      dadoBiomedico: {}
    }
  },

  computed: {
    id() {
      return this.$route.params.id
    },
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
      if (nameLen < 3 || nameLen > 50) {
        return false
      }
      return true
    },
    isNameValid () {
      if (!this.name) {
        return null
      }
      let nameLen = this.name.length
      if (nameLen < 3 || nameLen > 25) {
        return false
      }
      return true
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
    back(){
      this.$router.go(-1)
    },
    reset () {
      this.errorMsg = false
      this.name = this.dadoBiomedico.nome;
      this.unidades = this.dadoBiomedico.unidades;
      this.description = this.dadoBiomedico.descricao;
      this.valorMin = this.dadoBiomedico.valorMin;
      this.valorMax= this.dadoBiomedico.valorMax;
      this.biomedicos = [];

      for(let i = 0; i < this.dadoBiomedico.valoresQualitativos.length; i++){
        let dado = Dado.constructor();
        dado.dadoText = this.dadoBiomedico.valoresQualitativos[i];
        this.biomedicos.push(dado)
      }
    console.log(this.biomedicos);
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

      this.$axios.$put('/api/dadosbiomedicos/'+this.id,   data)
        .then(() => {
          this.$router.push('/dadosbiomedicos/'+ this.id)
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })

    },
    remove(index) {
      this.biomedicos.splice(index,1)
    },
    add(){
      this.biomedicos.push(Dado.constructor())
    }
  },
  created() {
    this.$axios.$get(`api/dadosbiomedicos/${this.id}`)
      .then((biomedico) => {
        this.dadoBiomedico = biomedico || {}
        this.reset()
      })
  },
}
</script>
