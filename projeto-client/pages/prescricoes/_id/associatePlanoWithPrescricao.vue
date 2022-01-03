<template>
  <form @submit.prevent="associate">
    <b-container>
      <h1>Associar plano à prescrição #{{this.id}}</h1>

      <label>Plano</label>
      <b-select v-model="plano">
        <template v-for="item in planos">
          <option :key="item.id" :value="item.id">
            {{ item.id }}: {{ item.descricao }}
          </option>
        </template>
      </b-select>
    </b-container>
    <br>
    <br>
    <b-container>
      <b-button to="/prescricoes">Voltar</b-button>
      <b-button variant="success" @click.prevent="associate">Associar</b-button>
    </b-container>
  </form>
</template>
<script>
export default {
  data() {
    return {
      planos:[],
      plano: {},
    }
  },
  created() {
    this.$axios.$get('/api/planos')
      .then(planos => {
        this.planos = planos
      })
  },
  methods: {
    associate() {
      this.$axios.$put('/api/planos/' + this.plano + '/prescricao/associate/' + this.id)
        .then(() => {
          this.$toast.success('Plano adicionado com sucesso!').goAway(2000)
          this.$router.go(-1)
        })
        .catch(()=>{
          this.$toast.error('Não foi possível adicionar o plano!').goAway(2000)
        })
    },
  },
  computed: {
    id() {
      return this.$route.params.id
    },
  }
}
</script>
