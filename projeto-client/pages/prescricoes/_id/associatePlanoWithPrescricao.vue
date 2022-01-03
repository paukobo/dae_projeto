<template>
  <form @submit.prevent="associate">
    <div>
      ID Prescrição: <input v-model="this.id" type="text" disabled>
    </div>
    <div>
      Planos:
      <select v-model="plano">
        <template v-for="item in planos">
          <option :key="item.id" :value="item.id">
            {{ item.id }} : {{ item.descricao }}
          </option>
        </template>
      </select>
    </div>
    <nuxt-link to="/prescricoes">Return</nuxt-link>
    <button type="reset">RESET</button>
    <button @click.prevent="associate">Associate</button>
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
          this.$router.push('/prescricoes')
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
