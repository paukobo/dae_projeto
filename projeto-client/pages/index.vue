<template>
  <div>
    <b-navbar type="dark" variant="dark">
      <b-navbar-brand href="/">PRC Handling Page</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="/dadosbiomedicos">Dados Biomédicos</b-nav-item>
          <b-nav-item href="/doentes">Doentes</b-nav-item>
          <b-nav-item href="/admins">Administradores</b-nav-item>
          <b-nav-item href="/profissionaisSaude">Profissionais de Saúde</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">

          <b-nav-item-dropdown right>
            <!-- Using 'button-content' slot -->
            <template #button-content>
              <em>User</em>
            </template>
            <b-dropdown-item href="#">Profile</b-dropdown-item>
            <b-dropdown-item href="#">Sign Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
    <b-container>
      <h1>Welcome</h1>
    </b-container>
  </div>
</template>
