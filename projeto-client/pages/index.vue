<template>
  <b-container v-if="$auth.loggedIn">
    <br>
    <h3>Welcome <nuxt-link :to="profile()">{{$auth.user.sub}}</nuxt-link></h3>
    <br>
    <div v-if="$auth.user.groups[0] == 'Admin' || $auth.user.groups[0] == 'ProfissionalSaude'">
      <span v-if="$auth.user.groups[0] == 'Admin'">
        <b-button variant="info" to="/dadosbiomedicos">Dados Biomédicos</b-button>
        <b-button variant="info" to="/admins">Administradores</b-button>
      </span>
      <b-button variant="info" to="/doentes/">Doentes</b-button>
      <b-button variant="info" to="/profissionaisSaude">Profissionais de Saúde</b-button>
    </div>
    <div v-else>
      <b-button variant="info" :to="profile() + '/dadosbiomedicos'">Adicionar Dado Biomedico</b-button>
    </div>
  </b-container>
</template>

<script>

export default {
  methods: {
    profile() {
      if (this.$auth.loggedIn){
        if (this.$auth.user.groups[0] == 'Doente'){
          return '/doentes/' + this.$auth.user.sub
        } else if (this.$auth.user.groups[0] == 'Admin') {
          return '/admins/' + this.$auth.user.sub
        } else {
          return '/profissionaisSaude/' + this.$auth.user.sub
        }
      }
    }
  }
}
</script>
