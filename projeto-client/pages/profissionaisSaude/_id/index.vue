<template>
  <b-container>
    <h4>Detalhes Profissional Saúde</h4>

    <label>Nome</label>
    <b-input disabled v-model="profissional.name"></b-input>
    <label>Email</label>
    <b-input disabled v-model="profissional.email"></b-input>

    <br>
    <br>

    <h4>Doentes</h4>
    <b-table v-if="doentes.length" striped over :items="doentes" :fields="doenteFields">
      <template v-slot:cell(actions)="row">
        <div v-if="$auth.user.sub == profissional.email">
          <b-button
            variant="info"
            :to="`/doentes/${row.item.email}`"><b-icon icon="eyeFill"></b-icon>
          </b-button>
        </div>
        <div v-else>
          <b-button
            variant="danger"
            disabled
            :to="`/doentes/${row.item.email}`"><b-icon icon="eyeFill"></b-icon>
          </b-button>
        </div>
      </template>
    </b-table>
    <p v-else>No doentes associated.</p>

    <div style="margin-top: 20px">
      <b-button to="/profissionaisSaude">Voltar</b-button>
      <b-button variant="primary" v-if="$auth.user.groups[0] == 'Admin'" :to=editUrl>Editar</b-button>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button variant="danger" v-if="$auth.user.groups[0] == 'Admin'" style="float: right" @click.prevent="deleteProfissional">Delete</b-button>
    </div>
  </b-container>
</template>
<script>
import { BIcon, BIconPlus, BIconDash, BIconEyeFill } from 'bootstrap-vue'

export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash,
    BIconEyeFill
  },
  data() {
    return {
      profissional: {},
      doenteFields: [ 'name', 'email', 'contact', 'actions' ],
      errorMsg: false,
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl(){
      return this.id + "/edit"
    },
    doentes() {
      return this.profissional.doentesDTOList || []
    }
  },
  methods:{
    deleteProfissional(){
      this.$axios.$delete(`api/profissionaisSaude/${this.id}`)
        .then(() => {
          this.$toast.success('Profissional de Saúde eliminado com sucesso!').goAway(2000)
          this.$router.push('/profissionaisSaude')
        })
        .catch(error => {
          this.errorMsg = error.response.data
          this.$toast.error('Não foi possível eliminar o profissional de saúde!').goAway(2000)
        })
    }
  },
  created() {
    this.$axios.$get(`/api/profissionaisSaude/${this.id}`)
      .then((profissional) => {
        this.profissional = profissional || {}
      })
  },
}
</script>
