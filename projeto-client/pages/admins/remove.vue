<template>
  <b-container>
    <div>
      <h1>Remove an Administrator</h1>
      <form @submit.prevent="remove" :disabled="!isFormValid">

        <b-form-group description="The ID is required" label="Enter your ID" label-for="id">
          <b-input v-model.trim="id" required placeholder="Enter your ID"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/admins">Return</nuxt-link>
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
      this.$axios.$delete('/api/admins/' + this.id)
        .then(() => {
          this.$router.push('/admins')
        })
        .catch((e) => {
          alert(e)
        })
    }
  }
}
</script>
