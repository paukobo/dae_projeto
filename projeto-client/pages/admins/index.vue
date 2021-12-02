<template>
  <b-container>
  <b-table striped over :items="admins" :fields="fields">
    <template v-slot:cell(actions)="row">
      <nuxt-link
        class="btn btn-link"
        :to="`/admins/${row.item.id}`">Details</nuxt-link>
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
      admins: [],
    };
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
