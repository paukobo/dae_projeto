<template>
  <b-container>
    <div>
      <h1>Create a new Profissional de Saúde</h1>
      <form @submit.prevent="create" :disabled="!isFormValid">
        <b-form-group description="The name is required" label="Enter your name" label-for="name"
                      :invalid-feedback="invalidNameFeedback" :state="isNameValid">
          <b-input v-model.trim="name" required :state="isNameValid" placeholder="Enter your name"/>
        </b-form-group>

        <b-form-group description="The email is required" label="Enter your email" label-for="email"
                      :invalid-feedback="invalidEmailFeedback" :state="isEmailValid">
          <b-input ref="email" v-model.trim="email" type="email" :state="isEmailValid" required
                   pattern=".+@my.ipleiria.pt" placeholder="Enter your e-mail"/>
        </b-form-group>

        <b-form-group description="The password is required" label="Enter your password" label-for="password"
                      :invalid-feedback="invalidPasswordFeedback" :state="isPasswordValid">
          <b-input v-model="password" type="password" :state="isPasswordValid" required
                   placeholder="Enter your password"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/profissionaisSaude">Return</nuxt-link>
        <button type="reset" @click="reset">RESET</button>
        <button @click.prevent="create" :disabled="!isFormValid">CREATE</button>
      </form>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      password: null,
      name: null,
      email: null,
      profissionais: [],
      errorMsg: false
    }
  },
  computed: {
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
    invalidEmailFeedback() {
      if (!this.email) {
        return null
      }
      return ''
    },
    isEmailValid() {
      if (!this.email) {
        return null
      }
      return this.$refs.email.checkValidity()
    },
    isFormValid() {
      if (!this.isPasswordValid) {
        return false
      }
      if (!this.isNameValid) {
        return false
      }
      if (!this.isEmailValid) {
        return false
      }
      return true
    }
  },
  created() {
    this.$axios.$get('/api/profissionaisSaude')
      .then(profissionais => {
        this.profissionais = profissionais
      })
  },
  methods: {
    reset() {
      this.errorMsg = false
    },
    create() {
      this.$axios.$post('/api/profissionaisSaude', {
        name: this.name,
        email: this.email,
        password: this.password
      })
        .then(() => {
          this.$router.push('/profissionaisSaude')
        })
    }
  }
}
</script>
