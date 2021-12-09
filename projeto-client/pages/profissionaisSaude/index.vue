<template>
  <b-container>
    <b-table striped over :items="profissionais" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/profissionaisSaude/${row.item.id}`">Details</nuxt-link>
      </template>
    </b-table>
    <nuxt-link to="/">Back</nuxt-link>
    <b-container>
      <br>
      <nuxt-link to="profissionaisSaude/create">Create a New Profissional de Saúde</nuxt-link>
      <br>
      <nuxt-link to="profissionaisSaude/remove">Remove a Profissional de Saúde</nuxt-link>
      <br>
      <nuxt-link to="profissionaisSaude/update">Update a Profissional de Saúde</nuxt-link>
      <br>
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
      .$get("http://localhost:8080/dae_project/api/profissionais")
      .then((profissionais) => {
        this.profissionais = profissionais;
      });
  },
};
</script>
<style></style>
