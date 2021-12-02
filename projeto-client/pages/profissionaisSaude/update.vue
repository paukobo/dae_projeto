<template>
  <b-container>
    <div>
      <h1>Update a Profissional de Saúde</h1>
      <form @submit.prevent="update">

        <b-form-group label="Enter ID" label-for="id">
          <b-input v-model.trim="id" placeholder="Enter the profissional de saúde ID you want to edit" type="number"> </b-input>
        </b-form-group>

        <b-form-group description="The new name is required" label="Enter your new name" label-for="name"
                      :invalid-feedback="invalidNameFeedback" :state="isNameValid">
          <b-input v-model.trim="name" required :state="isNameValid" placeholder="Enter your new name"/>
        </b-form-group>

        <b-form-group description="The new email is required" label="Enter your new email" label-for="email"
                      :invalid-feedback="invalidEmailFeedback" :state="isEmailValid">
          <b-input ref="email" v-model.trim="email" type="email" :state="isEmailValid" required
                   pattern=".+@my.ipleiria.pt" placeholder="Enter your new e-mail"/>
        </b-form-group>

        <b-form-group description="The new password is required" label="Enter your new password" label-for="password"
                      :invalid-feedback="invalidPasswordFeedback" :state="isPasswordValid">
          <b-input v-model="password" type="password" :state="isPasswordValid" required
                   placeholder="Enter your new password"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/profissionais">Return</nuxt-link>
        <button type="reset" @click="reset">Reset</button>
        <button @click.prevent="update" >Update</button>
      </form>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      id: null,
      name: null,
      email: null,
      password: null,
      errorMsg: false
    }
  },
  methods: {
    reset() {
      this.errorMsg = false
    },
    update() {
      this.$axios.$put('/api/profissionais/' + this.id, {
        name: this.name,
        email: this.email,
        password: this.password,
      })
        .then(() => {
          this.$router.push('/profissionais')
        })
        .catch(()=>{
          alert("Profissional de Saúde with id: " + this.id + " not found")
        })
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
  }
}
</script>
