<template>
  <div>

    <h1>Editar Doente</h1>

    <form @submit.prevent="create" :disabled="!isFormValid" style="margin-left: 30px">
      <b-form-group
        id="name"
        description="O nome é obrigatório"
        label="Nome"
        label-for="name"
        :invalid-feedback="invalidNameFeedback"
        :state="isNameValid">
        <b-input id="name" v-model.trim="name" :state="isNameValid" trim/>
        <br>
        <label>Email</label>
        <b-input  v-model="email" required disabled/>
        <br>
        <label>Morada</label>
        <b-input type="text" v-model.trim="address" :state="isAddressValid"/>
        <br>
        <label>Contacto</label>
        <b-input type="tel" pattern="[9]{1}[1|2|3|6]{1}[0-9]{8}" v-model.trim="contact" required/>
        <br>
        <label>Profissional de Saúde</label>
        <b-input v-model="profissionalEmail" disabled/>
      </b-form-group>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button variant="danger" href="#" @click.prevent="back()">Cancel</b-button>
      <b-button type="reset" @click="reset">Reset</b-button>
      <b-button variant="success" @click.prevent="create"
              :disabled="!isFormValid">Save</b-button>
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
      password: null,
      profissionalEmail: null,
      errorMsg: false,
      doente: {},
      profissionais: [],
    }
  },

  computed: {
    id() {
      return this.$route.params.id
    },
    invalidPasswordFeedback() {
      if (!this.password) {
        return null
      }
      let passwordLen = this.password.length
      if (passwordLen < 8) {
        return 'The password must have at least 8 characters.'
      }
      return ''
    },
    isPasswordValid() {
      if (!this.password) {
        return null
      }
      let passwordLen = this.password.length
      if (passwordLen < 3 || passwordLen > 255) {
        return false
      }
      return true
    },
    invalidNameFeedback() {
      if (!this.name) {
        return null
      }
      let nameLen = this.name.length
      if (nameLen < 2) {
        return 'The name must have at least 2 characters.'
      }
      return ''
    },
    isNameValid() {
      if (!this.name) {
        return null
      }
      let nameLen = this.name.length
      if (nameLen < 3 || nameLen > 25) {
        return false
      }
      return true
    },
    invalidContactFeedback() {
      if (!this.contact) {
        return null
      }
      let contactLen = this.contact.length
      if (contactLen != 9) {
        return 'The contact must have 9 digits.'
      }
      return ''
    },
    isContactValid() {
      if (!this.contact) {
        return null
      }
      let contactLen = this.contact.length
      if (contactLen != 9) {
        return false
      }
      return true
    },
    invalidAddressFeedback() {
      if (!this.address) {
        return null
      }
      let addressLen = this.address.length
      if (addressLen < 2 || addressLen > 75) {
        return 'The adress must have between [2-75] characters.'
      }
      return ''
    },
    isAddressValid() {
      if (!this.address) {
        return null
      }
      let addressLen = this.address.length
      if (addressLen < 2 || addressLen > 75) {
        return false
      }
      return true
    },
    isFormValid() {
      if (!this.isPasswordValid) {
        return false
      }
      if (!this.isNameValid) {
        return false
      }
      if (!this.isContactValid) {
        return false
      }
      if (!this.isAddressValid) {
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
      this.name = this.doente.name;
      this.email = this.doente.email;
      this.contact = this.doente.contact;
      this.password = this.doente.password;
      this.address = this.doente.address;
      this.profissionalEmail = this.doente.profissionalEmail;
    },

    create() {
      let data = {
        name: this.name,
        email: this.email,
        password: this.password,
        address: this.address,
        contact: this.contact,
        profissionalEmail: this.profissionalEmail
      }

      console.log(data)

      this.$axios.$put('/api/doentes/'+this.id,   data)
        .then(() => {
          this.$toast.success('Doente successfully updated!').goAway(2000)
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

    this.$axios.$get('/api/profissionaisSaude')
      .then(profissionais => {
        this.profissionais = profissionais
      })
  },
}
</script>
