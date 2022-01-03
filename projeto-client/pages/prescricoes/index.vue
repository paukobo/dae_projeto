<template>
  <b-container>
    <h1 style="text-align: center">Prescrições</h1>
    <b-table striped over :items="prescricoes" :fields="fields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/prescricoes/${row.item.id}`"><b-icon icon="eyeFill"></b-icon>
        </b-button>
      </template>
    </b-table>
    <b-container>
      <br>
      <b-button variant="success" to="prescricoes/create"><b-icon icon="plusCircle"></b-icon> Prescrição</b-button>
    </b-container>
    <br>
    <b-button @click="$router.go(-1)">Voltar</b-button>
  </b-container>
</template>
<script>
import { BIcon, BIconPlus, BIconDash, BIconPlusCircle, BIconEyeFill } from 'bootstrap-vue'

export default {
  components: {
    BIcon,
    BIconPlus,
    BIconDash,
    BIconPlusCircle,
    BIconEyeFill
  },
  data() {
    return {
      fields: [
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
          label: "Data Fim"
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
      prescricoes: [],
    };
  },
  created() {
    this.$axios
      .$get("http://localhost:8080/dae_project/api/prescricoes")
      .then((prescricoes) => {
        this.prescricoes = prescricoes;
      });
  },
};
</script>
<style></style>
