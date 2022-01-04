<template>
  <div id="app">
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="/">DAE PRC Handling</b-navbar-brand>
      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>
      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav v-if="$auth.loggedIn">
          <b-container v-if="$auth.user.groups[0] == 'Admin'">
            <b-nav-item href="/dadosbiomedicos">Dados Biomédicos</b-nav-item>
            <b-nav-item href="/doentes">Doentes</b-nav-item>
            <b-nav-item href="/admins">Administradores</b-nav-item>
            <b-nav-item href="/profissionaisSaude">Profissionais de Saúde</b-nav-item>
          </b-container>
          <b-container v-else-if="$auth.user.groups[0] == 'ProfissionalSaude'">
            <b-nav-item href="/doentes">Doentes</b-nav-item>
            <b-nav-item href="/profissionaisSaude">Profissionais de Saúde</b-nav-item>
          </b-container>
        </b-navbar-nav>
        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item-dropdown v-if="$auth.loggedIn" right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>{{ $auth.user.sub }}</em>
            </template>
            <b-dropdown-item @click.prevent="profile">Profile</b-dropdown-item>
            <b-dropdown-item @click.prevent="signOut">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
          <li class="nav-item" v-else>
            <nuxt-link class="nav-link" to="/auth/login">Sign In</nuxt-link>
          </li>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <main>
      <Nuxt/>
    </main>
  </div>
</template>
<script>
export default {
  methods: {
    signOut() {
      this.$auth.logout()
      this.$router.push('/auth/login')
    },
    profile() {
      if (this.$auth.loggedIn){
        if (this.$auth.user.groups[0] == 'Doente'){
          this.$router.push('/doentes/' + this.$auth.user.sub)
        } else if (this.$auth.user.groups[0] == 'Admin') {
          this.$router.push('/admins/' + this.$auth.user.sub)
        } else {
          this.$router.push('/profissionaisSaude/' + this.$auth.user.sub)
        }
      }
    }
  }
}
</script>
