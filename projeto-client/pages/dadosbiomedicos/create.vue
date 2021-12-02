<template>
  <div>

    <h1>Create a new Student</h1>

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
      <nuxt-link to="/students">Return</nuxt-link>
      <button type="reset" @click="reset">RESET</button>
      <button @click.prevent="create"
              :disabled="!isFormValid">CREATE</button>
      <button @click.prevent="test"
              >Temp</button>
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
      biomedicos: [Dado.constructor()]
    }
  },

  computed: {
    isMinValValid(){
      if(this.valorMin == null || this.valorMin == String() ){
        return false
      }
      if(!(this.valorMax > this.valorMin)){
        return false
      }
      return true
    },
    isMaxValValid(){

      if(this.valorMax == null || this.valorMax == String() ){
        return false
      }
      if(!(this.valorMax > this.valorMin)){
        return false
      }
      return true
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
      if(!this.isMaxValValid){
        return false
      }
      if(!this.isMinValValid){
        return false
      }
      return true
    }
  },

  methods: {
    test(){
      console.log(this.biomedicos)

    },
    reset () {
      this.errorMsg = false
      this.biomedicos.splice(1,this.biomedicos.length -1)
    },

    create() {
      let data
      if(this.biomedicos.length == 0){
        data =  {
          nome: this.name,
          descricao: this.description,
          valorMin: this.valorMin,
          valorMax: this.valorMax,
          unidades: this.unidades,
        }
      }else{
        let i = 0
        let array = []
        for(i; i < this.biomedicos.length;i++){
          array.push(this.biomedicos[i].dadoText)
        }
        data =  {

          nome: this.name,
          descricao: this.description,
          valorMin: this.valorMin,
          valorMax: this.valorMax,
          unidades: this.unidades,
          valoresQualitativos: array
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
      this.biomedicos.splice(index,1)
    },
    add(){
      this.biomedicos.push(Dado.constructor())
    }
  }
}
</script>
