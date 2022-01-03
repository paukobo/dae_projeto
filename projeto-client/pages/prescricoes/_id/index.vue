<template>
  <b-container>
    <h4>Detalhes Prescrições</h4>
    <label>Descrição</label>
    <b-input disabled v-model="prescricao.descricao"></b-input>
    <label>Data Início</label>
    <b-input disabled v-model="prescricao.dataInicio"></b-input>
    <label>Data Fim</label>
    <b-input disabled v-model="prescricao.dataFim"></b-input>
    <label>Duração</label>
    <b-input disabled v-model="prescricao.duracao"></b-input>

    <br>
    <br>

    <h4>Planos</h4>
    <b-table v-if="planos.length" striped over :items="planos" :fields="planoFields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/planos/${row.item.id}`"><b-icon icon="eyeFill"/></b-button>
      </template>
    </b-table>
    <p v-else>No planos associated.</p>

    <div style="margin-top: 20px">
      <b-button @click="$router.go(-1)">Voltar</b-button>
      <b-button variant="primary" :to=editUrl>Editar</b-button>
      <b-button variant="success" :to="newPlano">Associar plano</b-button>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button variant="danger" style="float: right" @click.prevent="deletePrescricao">Delete</b-button>
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
      prescricao: {},
      planoFields: [
        {
          key: "descricao",
          label: "Descrição"
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
    deletePrescricao(){
      this.$axios.$delete(`api/prescricoes/${this.id}`)
        .then(() => {
          this.$toast.success('Prescrição eliminada com sucesso!').goAway(2000)
          this.$router.go(-1)
        })
        .catch(error => {
          this.errorMsg = error.response.data
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
    newPlano(){
      return this.id + "/associatePlanoWithPrescricao"
    },
    planos() {
      return this.prescricao.planosDTOList == null ? [] : this.prescricao.planosDTOList
    }
  },
  created() {
    this.$axios.$get(`/api/prescricoes/${this.id}`)
      .then((prescricoes) => {
        this.prescricao = prescricoes || {}
      })
  },
}
</script>
