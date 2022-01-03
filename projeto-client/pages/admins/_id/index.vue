<template>
  <b-container>
    <h4>Detalhes Administrador</h4>
    <label>Nome</label>
    <b-input v-model="admin.name" disabled/>
    <label>Email</label>
    <b-input v-model="admin.email" disabled/>

    <div style="margin-top: 20px">
      <b-button to="/admins">Voltar</b-button>
      <b-button :to=editUrl variant="primary">Edit</b-button>
      <p v-show="errorMsg">{{ errorMsg }}</p>
      <b-button style="float: right" variant="danger" @click.prevent="deleteAdmin">Delete</b-button>
    </div>
  </b-container>
</template>
<script>
export default {
  data() {
    return {
      admin: {},
      adminFields: [{
          key: "name",
          label: "Nome"
        }, {
          key: "email",
          label: "Email"
        },
      ],
      errorMsg: false,
    }
  },
  methods: {
    deleteAdmin() {
      this.$axios.$delete(`api/admins/${this.id}`)
        .then(() => {
          this.$toast.success('Administrador eliminado com sucesso!').goAway(2000)
          this.$router.push('/admins')
        })
        .catch(error => {
          this.errorMsg = error.response.data
          this.$toast.error('Não foi possível eliminar o administrador!').goAway(2000)
        })
    }
  },
  computed: {
    id() {
      return this.$route.params.id
    },
    editUrl() {
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
