<template>
  <b-container>
    <h1 style="text-align: center">Planos</h1>
    <b-table striped over :items="planos" :fields="fields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/planos/${row.item.id}`"><b-icon icon="eyeFill"></b-icon>
        </b-button>
      </template>
    </b-table>
    <b-container>
      <br>
      <b-button variant="success" to="planos/create"><b-icon icon="plusCircle"></b-icon> Plano</b-button>
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
          key: "duracao",
          label: "Duração"
        },
        {
          key: "actions",
          label: "Detalhes"
        },
      ],
      planos: [],
    };
  },
  created() {
    this.$axios
      .$get("http://localhost:8080/dae_project/api/planos")
      .then((planos) => {
        this.planos = planos;
      });
  },
};
</script>
<style></style>
