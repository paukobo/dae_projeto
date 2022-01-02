<template>
  <b-container>
    <div>
      <h1>Create a new Doente</h1>
      <form @submit.prevent="create" :disabled="!isFormValid">
        <b-form-group description="The name is required" label="Enter your name" label-for="name"
                      :invalid-feedback="invalidNameFeedback" :state="isNameValid">
          <b-input v-model="name" required :state="isNameValid" placeholder="Enter your name"/>
        </b-form-group>

        <b-form-group description="The email is required" label="Enter your email" label-for="email"
                      :invalid-feedback="invalidEmailFeedback" :state="isEmailValid">
          <b-input ref="email" v-model="email" type="email" :state="isEmailValid" required
                   placeholder="Enter your e-mail"/>
        </b-form-group>

        <b-form-group description="The password is required" label="Enter your password" label-for="password"
                      :invalid-feedback="invalidPasswordFeedback" :state="isPasswordValid">
          <b-input v-model="password" type="password" :state="isPasswordValid" required
                   placeholder="Enter your password"/>
        </b-form-group>

        <b-form-group description="The contact is required" label="Enter your contact" label-for="contact"
                      :invalid-feedback="invalidContactFeedback" :state="isContactValid">
          <b-input type="tel" pattern="[9]{1}[1|2|3|6]{1}[0-9]{7}" v-model="contact" required :state="isContactValid" placeholder="Enter your contact"/>
        </b-form-group>

        <b-form-group description="The address is required" label="Enter your address" label-for="address"
                      :invalid-feedback="invalidAddressFeedback" :state="isAddressValid">
          <b-input v-model="address" required :state="isAddressValid" placeholder="Enter your address"/>
        </b-form-group>

        <b-form-group>
          <label>Profissionais de Saúde:</label>
            <b-form-select v-model="profissionalEmail">
              <template v-for="item in profissionais">
                <option :key="item.email" :value="item.email">
                  {{ item.email }}
                </option>
              </template>
            </b-form-select>
        </b-form-group>


        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/doentes">Return</nuxt-link>
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
      contact: null,
      address: null,
      profissionalEmail: null,
      doentes: [],
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
      if (!this.isEmailValid) {
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
  created() {
    this.$axios.$get('/api/doentes')
      .then(doentes => {
        this.doentes = doentes
      })

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
      this.$axios.$post('/api/doentes', {
        name: this.name,
        email: this.email,
        password: this.password,
        contact: this.contact,
        address: this.address,
        profissionalEmail: this.profissionalEmail
      })
        .then(() => {
          this.$toast.success("Doente created successfully!").goAway(2000)
          this.$router.push('/doentes')
        })
    }
  }
}
</script>
