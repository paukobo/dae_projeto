<template>
  <div>
    <h1>Editar Administrador</h1>
    <form @submit.prevent="edit" :disabled="!isFormValid" style="margin-left: 30px">
      <b-form-group
        id="name"
        description="O nome é obrigatório"
        label="Name"
        label-for="name"
        :invalid-feedback="invalidNameFeedback"
        :state="isNameValid">
        <b-input id="name" v-model.trim="name" :state="isNameValid" trim/>
        <br>
        <label>Email</label>
        <b-input  v-model="email" required disabled/>
      </b-form-group>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button @click.prevent="back">Cancel</b-button>
      <b-button variant="danger" type="reset" @click="reset">Reset</b-button>
      <b-button variant="success" @click.prevent="edit" :disabled="!isFormValid">Guardar</b-button>
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
      password: null,
      errorMsg: false,
      admin: {}
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
      this.name = this.admin.name;
      this.email = this.admin.email;
      this.password = this.admin.password;
    },

    edit() {
      let data = {
        name: this.name,
        email: this.email,
        password: this.password
      }

      this.$axios.$put('/api/admins/'+this.id,   data)
        .then(() => {
          this.$toast.success('Admin successfully updated!').goAway(2000)
          this.$router.push('/admins/'+ this.id)
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })

    },
  },
  created() {
    this.$axios.$get(`api/admins/${this.id}`)
      .then((admin) => {
        this.admin = admin || {}
        this.reset()
      })
  },
}
</script>
