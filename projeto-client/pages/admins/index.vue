<template>
  <b-container>
  <b-table striped over :items="admins" :fields="fields">
    <template v-slot:cell(actions)="row">
      <nuxt-link
        class="btn btn-link"
        :to="`/admins/${row.item.id}`">Details</nuxt-link>
    </template>
  </b-table>
    <b-container>
      <br>
      <nuxt-link to="admins/create">Create a New Admin</nuxt-link>
      <br>
      <nuxt-link to="admins/remove">Remove a Admin</nuxt-link>
      <br>
      <nuxt-link to="admins/update">Update a Admin</nuxt-link>
      <br>
    </b-container>
  <nuxt-link to="/">Back</nuxt-link>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      fields: ["id", "name", "email", "actions"],
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
