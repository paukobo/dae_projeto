<template>
  <form @submit.prevent="associate">
    <b-container>
      <h1>Associar prescrição ao doente {{this.id}}</h1>

      <label>Prescrição</label>
      <b-select v-model="prescricao">
        <template v-for="item in prescricoes">
          <option :key="item.id" :value="item.id">
            {{ item.id }} : {{ item.descricao }}
          </option>
        </template>
      </b-select>
    </b-container>

    <br>

    <b-container>
      <b-button to="/doentes">Voltar</b-button>
      <b-button variant="success" @click.prevent="associate">Associar</b-button>
    </b-container>

  </form>
</template>
<script>
export default {
  data() {
    return {
      prescricoes:[],
      prescricao: {},
    }
  },
  created() {
    this.$axios.$get('/api/prescricoes')
      .then(prescricoes => {
        this.prescricoes = prescricoes
      })
  },
  methods: {
    associate() {
      this.$axios.$put('/api/prescricoes/' + this.prescricao + '/doente/associate/' + this.id)
        .then(() => {
          this.$toast.success('Prescrição adicionada com sucesso!').goAway(2000)
          this.$router.go(-1)
        })
        .catch(()=>{
          this.$toast.error('Não foi possível adicionar a prescrição!').goAway(2000)
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
