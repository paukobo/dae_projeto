<template>
  <b-container>
    <b-table striped over :items="planos" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/planos/${row.item.id}`">Details</nuxt-link>
      </template>
    </b-table>
    <b-container>
      <br>
      <nuxt-link to="planos/create">Create a New Plano</nuxt-link>
    </b-container>
    <nuxt-link to="/">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      fields: ["descricao", "duracao", "actions"],
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
