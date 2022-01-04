<template>
  <b-container>
    <h4>Detalhes Doente</h4>

    <label>Nome:</label>
    <b-input v-model="doente.name" disabled></b-input>

    <label>Email:</label>
    <b-input v-model="doente.email" disabled></b-input>

    <label>Contacto:</label>
    <b-input v-model="doente.contact" disabled></b-input>

    <label>Morada:</label>
    <b-input v-model="doente.address" disabled></b-input>

    <label>Médico:</label>
    <b-input v-model="doente.profissionalEmail" disabled></b-input>

    <br>
    <br>

    <h4>Prescrições</h4>
    <b-table v-if="prescricoes.length" striped over :items="prescricoes" :fields="prescricoesFields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/prescricoes/${row.item.id}`">
          <b-icon icon="eyeFill"></b-icon>
        </b-button>
      </template>
    </b-table>
    <p v-else>No prescrições associated.</p>

    <div style="margin-top: 20px" v-if="$auth.loggedIn">
      <b-button v-if="$auth.user.groups[0] == 'Admin' || $auth.user.groups[0] == 'ProfissionalSaude'" to="/doentes">
        Back
      </b-button>
      <b-button :to="`/doentes/${id}/dadosbiomedicos`">Dados Biomedicos</b-button>
      <b-button variant="primary" :to=editUrl>Editar</b-button>
      <b-button variant="success" v-if="$auth.user.groups[0] == 'Admin' || $auth.user.groups[0] == 'ProfissionalSaude'"
                :to="newPrescricao">Associar prescrição
      </b-button>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button variant="danger" v-if="$auth.user.groups[0] == 'Admin' || $auth.user.groups[0] == 'ProfissionalSaude'"
                style="float: right" @click.prevent="deleteDoente">Delete
      </b-button>
    </div>
    <br>
    <b-button @click.prevent="back()">Voltar</b-button>
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
      doente: {},
      prescricoesFields: [{
        key: "descricao",
        label: "Descrição"
      },
      {
        key: "actions",
        label: "Detalhes"
      }
      ],
      errorMsg: false,
    }
  },
  methods: {
    back() {
      if(this.$auth.user.groups[0] == "Doente"){
        this.$router.push("/")
      }
      else this.$router.go(-1)
    },
    deleteDoente() {
      this.$axios.$delete(`api/doentes/${this.id}`)
        .then(() => {
          this.$toast.success('Doente successfully deleted!').goAway(2000)
          this.$router.go(-1)
        })
        .catch(error => {
          this.errorMsg = error.response.data
          this.$toast.error('Não foi possível eliminar o doente!').goAway(2000)
        })
    },
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl() {
      return this.id + "/edit"
    },
    newPrescricao() {
      return this.id + "/associateDoenteWithPrescricao"
    },
    prescricoes() {
      return this.doente.prescricaoDTOList == null ? [] : this.doente.prescricaoDTOList
    }
  },
  created() {
    this.$axios.$get(`/api/doentes/${this.id}`)
      .then((doente) => {
        this.doente = doente || {}
      })
  },
}
</script>
