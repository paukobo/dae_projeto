<template>
  <b-container>
    <b-table striped over :items="prescricoes" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/prescricoes/${row.item.id}`">Details</nuxt-link>
      </template>
    </b-table>
    <b-container>
      <br>
      <nuxt-link to="prescricoes/create">Create a New Prescrição</nuxt-link>
    </b-container>
    <nuxt-link to="/">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      fields: ["descricao", "dataInicio", "dataFim", "duracao", "actions"],
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
