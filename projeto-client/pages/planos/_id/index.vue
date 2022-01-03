<template>
  <b-container>
    <h4>Detalhes Plano</h4>
    <label>Descrição</label>
    <b-input disabled v-model="plano.descricao"></b-input>
    <label>Duração</label>
    <b-input disabled v-model="plano.duracao"></b-input>

    <br>
    <br>

    <h4>Prescrições</h4>
    <b-table v-if="prescricoes.length" striped over :items="prescricoes" :fields="prescricaoFields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/prescricoes/${row.item.id}`"><b-icon icon="eyeFill"/></b-button>
      </template>
    </b-table>
    <p v-else>No prescrições associated.</p>

    <div style="margin-top: 20px">
      <b-button @click="$router.go(-1)">Voltar</b-button>
      <b-button variant="primary" :to=editUrl>Editar</b-button>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button variant="danger" style="float: right" @click.prevent="deletePlano">Delete</b-button>
    </div>
  </b-container>
</template>
<script>
import { BIcon, BIconPlus, BIconDash, BIconEyeFill, BIconPlusCircle } from 'bootstrap-vue'

export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash,
    BIconEyeFill,
    BIconPlusCircle
  },
  data() {
    return {
      plano: {},
      prescricaoFields: [
          {
            key: "descricao",
            label: "Descrição"
          },
          {
            key: "dataInicio",
            label: "Data Início"
          },
          {
            key: "dataFim",
            label: "dataFim"
          },
          {
            key: "duracao",
            label: "Duração"
          },
          {
            key: "actions",
            label: "Detalhes"
          },
        ],
      errorMsg: false,
    }
  },
  methods:{
    deletePlano(){
      this.$axios.$delete(`api/planos/${this.id}`)
        .then(() => {
          this.$toast.success('Plano eliminado com sucesso!').goAway(2000)
          this.$router.push('/planos')
        })
        .catch(error => {
          this.errorMsg = error.response.data
          this.$toast.error('Não foi possível eliminar o plano!').goAway(2000)
        })
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl(){
      return this.id + "/edit"
    },
    prescricoes() {
      return this.plano.prescricaoDTOList == null ? [] : this.plano.prescricaoDTOList
    },
  },
  created() {
    this.$axios.$get(`/api/planos/${this.id}`)
      .then((planos) => {
        this.plano = planos || {}
      })
  },
}
</script>
