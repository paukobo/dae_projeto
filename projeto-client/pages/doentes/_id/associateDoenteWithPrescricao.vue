<template>
  <form @submit.prevent="associate">
    <div>
      Email: <input v-model="this.id" type="text" disabled>
    </div>
    <div>
      Prescrições:
      <select v-model="prescricao">
        <template v-for="item in prescricoes">
          <option :key="item.id" :value="item.id">
            {{ item.id }} : {{ item.descricao }}
          </option>
        </template>
      </select>
    </div>
    <nuxt-link to="/doentes">Return</nuxt-link>
    <button type="reset">RESET</button>
    <button @click.prevent="associate">Associate</button>
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
          this.$router.push('/doentes')
        })
        .catch((e)=>{
          alert(e)
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
