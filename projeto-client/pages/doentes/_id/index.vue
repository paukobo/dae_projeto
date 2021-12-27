<template>
  <b-container>
    <h4>Doente Details</h4>
    <p>Name: {{ doente.name }}</p>
    <p>Email: {{ doente.email }}</p>
    <p>Contact: {{ doente.contact }}</p>
    <p>Address: {{ doente.address }}</p>

    <div style="margin-top: 20px">
      <nuxt-link to="/doentes">Back</nuxt-link>
      <nuxt-link :to=editUrl>Edit</nuxt-link>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <button style="float: right" @click.prevent="deleteDoente">Delete</button>
    </div>

  </b-container>
</template>
<script>
export default {
  data() {
    return {
      doente: {},
      doentesFields: [ 'name', 'email', 'contact', 'address' ],
      errorMsg: false,
    }
  },
  methods:{
    deleteDoente(){
      this.$axios.$delete(`api/doentes/${this.id}`)
        .then(() => {
          this.$router.push('/doentes')
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
    this.$axios.$get(`/api/doentes/${this.id}`)
      .then((doente) => {
        this.doente = doente || {}
      })
  },
}
</script>
