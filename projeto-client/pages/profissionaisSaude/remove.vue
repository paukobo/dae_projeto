<template>
  <b-container>
    <div>
      <h1>Remove a Profissional de Saúde</h1>
      <form @submit.prevent="remove" :disabled="!isFormValid">

        <b-form-group description="The ID is required" label="Enter your ID" label-for="id">
          <b-input v-model.trim="id" required placeholder="Enter your ID"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/profissionais">Return</nuxt-link>
        <button type="reset" @click="reset">RESET</button>
        <button @click.prevent="remove">Remove</button>
      </form>
    </div>
  </b-container>
</template>

<script>
export default {
  data() {
    return {
      id: null,
      errorMsg: false
    }
  },
  methods: {
    reset() {
      this.errorMsg = false
    },
    remove() {
      this.$axios.$delete('/api/profissionais/' + this.id)
        .then(() => {
          this.$router.push('/profissionais')
        })
        .catch((e) => {
          alert(e)
        })
    }
  }
}
</script>
