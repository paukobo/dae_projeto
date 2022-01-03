<template>
  <b-container>
    <div>
      <h1>Create a new Plano</h1>
      <form @submit.prevent="create" :disabled="!isFormValid">
        <b-form-group description="The descrição is required" label="Enter descrição" label-for="descricao"
                      :invalid-feedback="invalidDescricaoFeedback" :state="isDescricaoValid">
          <b-input v-model.trim="descricao" required :state="isDescricaoValid" placeholder="Enter descrição"/>
        </b-form-group>

        <b-form-group description="The duração is required" label="Enter duração" label-for="duracao"
                      :invalid-feedback="invalidDuracaoFeedback" :state="isDuracaoValid">
          <b-input v-model="duracao" type="number" :state="isDuracaoValid" required
                   placeholder="Enter duração"/>
        </b-form-group>

        <p class="text-danger" v-show="errorMsg">{{ errorMsg }}</p>
        <nuxt-link to="/planos">Return</nuxt-link>
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
      descricao: null,
      duracao: null,
      planos: [],
      errorMsg: false
    }
  },
  computed: {
    invalidDuracaoFeedback() {
      if (!this.duracao) {
        return null
      }
      if (this.duracao <= 0) {
        return 'The duração must be greater than 0.'
      }
      return ''
    },
    isDuracaoValid() {
      if (!this.duracao) {
        return null
      }
      if (this.duracao <= 0) {
        return false
      }
      return true
    },
    invalidDescricaoFeedback() {
      if (!this.descricao) {
        return null
      }
      let descricaoLen = this.descricao.length
      if (descricaoLen < 2 || descricaoLen > 150) {
        return 'The descrição must have between [3, 150] characters.'
      }
      return ''
    },
    isDescricaoValid() {
      if (!this.descricao) {
        return null
      }
      let descricaoLen = this.descricao.length
      if (descricaoLen < 2 || descricaoLen > 150) {
        return false
      }
      return true
    },
    isFormValid() {
      if (!this.isDescricaoValid) {
        return false
      }
      if (!this.isDuracaoValid) {
        return false
      }
      return true
    }
  },
  created() {
    this.$axios.$get('/api/planos')
      .then(planos => {
        this.planos = planos
      })
  },
  methods: {
    reset() {
      this.errorMsg = false
    },
    create() {
    this.$axios.$post('/api/planos', {
      descricao: this.descricao,
      duracao: this.duracao
    })
      .then(() => {
        this.$toast.success("Plano created successfully!").goAway(2000)
        this.$router.push('/planos')
      })

    },
  }
}
</script>
