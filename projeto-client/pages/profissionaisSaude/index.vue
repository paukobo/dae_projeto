<template>
  <b-container>
    <b-table striped over :items="profissionais" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/profissionaisSaude/${row.item.email}`">Details</nuxt-link>
      </template>
    </b-table>
    <nuxt-link to="/">Back</nuxt-link>
    <nuxt-link to="/prescricoes">Ver Prescrições</nuxt-link>
    <b-container>
      <br>
      <nuxt-link to="prescricoes/create">Create a New Prescrição</nuxt-link>
    </b-container>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      fields: ["name", "email", "actions"],
      profissionais: [],
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
