<template>
  <b-container>
    <b-table striped over :items="doentes" :fields="fields">
      <template v-slot:cell(actions)="row">
        <b-button
          variant="info"
          :to="`/doentes/${row.item.email}`">
          <b-icon icon="eyeFill"></b-icon>
        </b-button>
      </template>
    </b-table>
    <div v-if="$auth.user.groups[0] == 'Admin' || $auth.user.groups[0] == 'ProfissionalSaude'">
      <b-button variant="success" to="/doentes/create">Novo doente</b-button>
    </div>
    <b-button @click.prevent="back">Back</b-button>
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
      fields: [{
        key: "name",
        label: "Nome"
      },{
        key: "email",
        label: "Email"
      },{
        key: "actions",
        label: "Detalhes"
      }],
      doentes: [],
    };
  },
  methods:{
    back(){
      this.$router.go(-1)
    }
  },
  created() {
    this.$axios
      .$get("http://localhost:8080/dae_project/api/doentes")
      .then((doentes) => {
        this.doentes = doentes;
      });
  },
};
</script>
<style></style>
