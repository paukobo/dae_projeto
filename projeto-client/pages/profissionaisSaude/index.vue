<template>
  <b-container>
    <h1 style="text-align: center">Profissionais Saúde</h1>
    <b-table striped over :items="profissionais" :fields="fields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/profissionaisSaude/${row.item.email}`">
          <b-icon icon="eyeFill"></b-icon>
        </b-button>
      </template>
    </b-table>
    <b-container>
      <b-button variant="success" to="prescricoes/create"><b-icon icon="plusCircle"/> Prescrição</b-button>
      <b-button variant="primary" to="/prescricoes">Ver Prescrições</b-button>
      <b-button variant="primary" to="/planos">Ver Planos</b-button>
    </b-container>
    <b-container>
      <br>
      <b-button to="/">Voltar</b-button>
    </b-container>
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
      fields: [
        {
          key: "name",
          label: "Nome"
        },{
          key: "email",
          label: "Email"
        },{
          key: "actions",
          label: "Detalhes"
        },
      ],
      profissionais: [],
      profissionaisDoentes: [],
    };
  },
  created() {
    this.$axios
      .$get("http://localhost:8080/dae_project/api/profissionaisSaude")
      .then((profissionais) => {
        this.profissionais = profissionais;
      });
  },
};
</script>
<style></style>
