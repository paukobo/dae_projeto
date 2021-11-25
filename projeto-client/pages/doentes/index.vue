<template>
  <b-container>
    <b-table striped over :items="doentes" :fields="fields">
      <template v-slot:cell(actions)="row">
        <nuxt-link
          class="btn btn-link"
          :to="`/doentes/${row.item.id}`">Details</nuxt-link>
      </template>
    </b-table>
    <nuxt-link to="/">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      fields: ["name", "email", "actions"],
      doentes: [],
    };
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
