<template>
  <div>

    <h1>Edit Plano</h1>

    <form @submit.prevent="create" :disabled="!isFormValid" style="margin-left: 30px">
      <b-form-group
        id="descricao"
        label="Descricao"
        label-for="descricao"
        :invalid-feedback="invalidDescricaoFeedback"
        :state="isDescricaoValid">
        <b-input id="descricao" v-model.trim="descricao" :state="isDescricaoValid" trim/>
        <br>
        <label>Duração</label>
        <b-input  v-model="duracao" required/>
      </b-form-group>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <a href="#" @click.prevent="back()">Cancel</a>
      <button type="reset" @click="reset">RESET</button>
      <button @click.prevent="create"
              :disabled="!isFormValid">SAVE</button>
    </form>
  </div>
</template>

<script>
import { BIcon, BIconPlus, BIconDash } from 'bootstrap-vue'

export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash
  },
  data () {
    return {
      descricao: null,
      duracao: null,
      errorMsg: false,
      plano: {}
    }
  },

  computed: {
    id() {
      return this.$route.params.id
    },
    invalidDescricaoFeedback () {
      if (!this.descricao) {
        return null
      }
      let descricaoLen = this.descricao.length
      if (descricaoLen < 3 || descricaoLen > 150) {
        return 'The Descrição must have between [3, 150] characters.'
      }
      return ''
    },
    isDescricaoValid () {
      if (!this.descricao) {
        return null
      }
      let descricaoLen = this.descricao.length
      if (descricaoLen < 3 || descricaoLen > 150) {
        return false
      }
      return true
    },
    isFormValid () {
      if (! this.isDescricaoValid) {
        return false
      }
      return true
    }
  },

  methods: {
    back(){
      this.$router.go(-1)
    },
    reset () {
      this.errorMsg = false
      this.descricao = this.prescricao.descricao;
      this.duracao = this.prescricao.duracao;
    },

    create() {
      let data = {
        descricao: this.descricao,
        duracao: this.duracao
      }

      this.$axios.$put('/api/planos/'+this.id,   data)
        .then(() => {
          this.$toast.success('Plano successfully updated!').goAway(2000)
          this.$router.push('/planos/'+ this.id)
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })

    },
  },
  created() {
    this.$axios.$get(`api/planos/${this.id}`)
      .then((plano) => {
        this.plano = plano || {}
        this.reset()
      })
  },
}
</script>
