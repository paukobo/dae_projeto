<template>
  <b-container>
    <h1 style="text-align: center">Administradores</h1>
  <b-table striped over :items="admins" :fields="fields">
    <template v-slot:cell(actions)="row">
      <b-button
        variant="info"
        :to="`/admins/${row.item.email}`">
        <b-icon icon="eyeFill"></b-icon>
      </b-button>

    </template>
  </b-table>
  <div style="display: inline-grid; grid-template-columns: 30% 30% 50%;">
    <b-container>
      <br>
      <b-button variant="success" to="admins/create"><b-icon icon="plusCircle"/> Admin</b-button>
    </b-container>
    <b-container>
      <br>
      <b-button variant="success" to="doentes/create"><b-icon icon="plusCircle"/> Doente</b-button>
    </b-container>
    <b-container>
      <br>
      <b-button variant="success" to="profissionaisSaude/create"><b-icon icon="plusCircle"/> Profissional de Saúde</b-button>
    </b-container>
  </div>
  <div>
    <b-button style="margin-top: 50px" variant="secondary" to="/">Voltar</b-button>
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
      fields: [{
        key: "name",
        label: "Nome"
      },{
        key: "email",
        label: "Email"
      },{
        key: "actions",
        label: "Detalhes"
      },],
      admins: [],
    };
  },
  methods: {
    back(){
      this.$router.go(-1)
    }
  },
  created() {
  this.$axios
    .$get("http://localhost:8080/dae_project/api/admins")
    .then((admins) => {
      this.admins = admins;
    });
  },
};
</script>
<style></style>
