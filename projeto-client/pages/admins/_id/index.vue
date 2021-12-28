<template>
  <b-container>
    <h4>Admin Details</h4>
    <p>Name: {{ admin.name }}</p>
    <p>Email: {{ admin.email }}</p>

    <div style="margin-top: 20px">
      <nuxt-link to="/admins">Back</nuxt-link>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deleteAdmin">Delete</button>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      admin: {},
      adminFields: [ 'name', 'email' ],
      errorMsg: false,
    }
  },
  methods:{
    deleteAdmin(){
      this.$axios.$delete(`api/admins/${this.id}`)
        .then(() => {
          this.$toast.success('Admin successfully deleted!').goAway(2000)
          this.$router.push('/admins')
        })
        .catch(error => {
          this.errorMsg = error.response.data
        })
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl(){
      return this.id + "/edit"
    },
  },
  created() {
    this.$axios.$get(`/api/admins/${this.id}`)
      .then((admin) => {
        this.admin = admin || {}
      })
  },
}
</script>
