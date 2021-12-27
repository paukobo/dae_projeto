<template>
  <div>

    <h1>Edit Doente</h1>

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
        <label>Email</label>
        <b-input  v-model="email" required/>
        <br>
        <label>Address</label>
        <b-input type="text" v-model.trim="address" :state="isAddressValid"/>
        <br>
        <label>Contact</label>
        <b-input v-model.trim="contact" required/>
        <br>

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

export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash
  },
  data () {
    return {
      name: null,
      email: null,
      address: null,
      contact: null,
      errorMsg: false,
      doente: {}
    }
  },

  computed: {
    id() {
      return this.$route.params.id
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
    isAddressValid(){
      if (!this.address) {
        return null
      }
      let addressLen = this.address.length
      if (addressLen < 3 || addressLen > 50) {
        return false
      }
      return true
    },
    isFormValid () {

      if (! this.isNameValid) {
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
      this.name = this.doente.nome;
    },

    create() {
      let data = {
        nome: this.name,
        email: this.email,
        morada: this.address,
        contacto: this.contact,
      }

      console.log(data)

      this.$axios.$put('/api/doentes/'+this.id,   data)
        .then(() => {
          this.$router.push('/doentes/'+ this.id)
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })

    },
  },
  created() {
    this.$axios.$get(`api/doentes/${this.id}`)
      .then((doente) => {
        this.doente = doente || {}
        this.reset()
      })
  },
}
</script>
