import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _05e9a883 = () => interopDefault(import('..\\pages\\admins\\index.vue' /* webpackChunkName: "pages/admins/index" */))
const _330d9e20 = () => interopDefault(import('..\\pages\\dadosbiomedicos\\index.vue' /* webpackChunkName: "pages/dadosbiomedicos/index" */))
const _db3ed502 = () => interopDefault(import('..\\pages\\doentes\\index.vue' /* webpackChunkName: "pages/doentes/index" */))
const _2408d5cc = () => interopDefault(import('..\\pages\\planos\\index.vue' /* webpackChunkName: "pages/planos/index" */))
const _2dc1c0a9 = () => interopDefault(import('..\\pages\\prescricoes\\index.vue' /* webpackChunkName: "pages/prescricoes/index" */))
const _3bd213d2 = () => interopDefault(import('..\\pages\\profissionaisSaude\\index.vue' /* webpackChunkName: "pages/profissionaisSaude/index" */))
const _42009aca = () => interopDefault(import('..\\pages\\admins\\create.vue' /* webpackChunkName: "pages/admins/create" */))
const _6243f3ba = () => interopDefault(import('..\\pages\\admins\\remove.vue' /* webpackChunkName: "pages/admins/remove" */))
const _7bbf9be8 = () => interopDefault(import('..\\pages\\admins\\update.vue' /* webpackChunkName: "pages/admins/update" */))
const _026a29b6 = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _565a709e = () => interopDefault(import('..\\pages\\dadosbiomedicos\\create.vue' /* webpackChunkName: "pages/dadosbiomedicos/create" */))
const _3d3335c2 = () => interopDefault(import('..\\pages\\doentes\\create.vue' /* webpackChunkName: "pages/doentes/create" */))
const _5d768eb2 = () => interopDefault(import('..\\pages\\doentes\\remove.vue' /* webpackChunkName: "pages/doentes/remove" */))
const _7e264e6c = () => interopDefault(import('..\\pages\\doentes\\update.vue' /* webpackChunkName: "pages/doentes/update" */))
const _0da94e38 = () => interopDefault(import('..\\pages\\planos\\create.vue' /* webpackChunkName: "pages/planos/create" */))
const _9baac196 = () => interopDefault(import('..\\pages\\prescricoes\\create.vue' /* webpackChunkName: "pages/prescricoes/create" */))
const _6624b12c = () => interopDefault(import('..\\pages\\profissionaisSaude\\create.vue' /* webpackChunkName: "pages/profissionaisSaude/create" */))
const _560304b4 = () => interopDefault(import('..\\pages\\profissionaisSaude\\remove.vue' /* webpackChunkName: "pages/profissionaisSaude/remove" */))
const _02e49a79 = () => interopDefault(import('..\\pages\\profissionaisSaude\\update.vue' /* webpackChunkName: "pages/profissionaisSaude/update" */))
const _5b7b0312 = () => interopDefault(import('..\\pages\\admins\\_id\\index.vue' /* webpackChunkName: "pages/admins/_id/index" */))
const _fb8efa0c = () => interopDefault(import('..\\pages\\dadosbiomedicos\\_id\\index.vue' /* webpackChunkName: "pages/dadosbiomedicos/_id/index" */))
const _05444efb = () => interopDefault(import('..\\pages\\doentes\\_id\\index.vue' /* webpackChunkName: "pages/doentes/_id/index" */))
const _06918e11 = () => interopDefault(import('..\\pages\\prescricoes\\_id\\index.vue' /* webpackChunkName: "pages/prescricoes/_id/index" */))
const _2ab3cc88 = () => interopDefault(import('..\\pages\\profissionaisSaude\\_id\\index.vue' /* webpackChunkName: "pages/profissionaisSaude/_id/index" */))
const _700479f5 = () => interopDefault(import('..\\pages\\admins\\_id\\edit.vue' /* webpackChunkName: "pages/admins/_id/edit" */))
const _4e6b4ddc = () => interopDefault(import('..\\pages\\dadosbiomedicos\\_id\\edit.vue' /* webpackChunkName: "pages/dadosbiomedicos/_id/edit" */))
const _6cb39af9 = () => interopDefault(import('..\\pages\\doentes\\_id\\associateDoenteWithPrescricao.vue' /* webpackChunkName: "pages/doentes/_id/associateDoenteWithPrescricao" */))
const _c610afa8 = () => interopDefault(import('..\\pages\\doentes\\_id\\dadosbiomedicos\\index.vue' /* webpackChunkName: "pages/doentes/_id/dadosbiomedicos/index" */))
const _45f6ee1e = () => interopDefault(import('..\\pages\\doentes\\_id\\edit.vue' /* webpackChunkName: "pages/doentes/_id/edit" */))
const _4eb2dfe6 = () => interopDefault(import('..\\pages\\prescricoes\\_id\\associatePlanoWithPrescricao.vue' /* webpackChunkName: "pages/prescricoes/_id/associatePlanoWithPrescricao" */))
const _40280f1b = () => interopDefault(import('..\\pages\\prescricoes\\_id\\edit.vue' /* webpackChunkName: "pages/prescricoes/_id/edit" */))
const _5a18a5c4 = () => interopDefault(import('..\\pages\\profissionaisSaude\\_id\\edit.vue' /* webpackChunkName: "pages/profissionaisSaude/_id/edit" */))
const _29b1a812 = () => interopDefault(import('..\\pages\\doentes\\_id\\dadosbiomedicos\\create.vue' /* webpackChunkName: "pages/doentes/_id/dadosbiomedicos/create" */))
const _0dfe6287 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

const emptyFn = () => {}

Vue.use(Router)

export const routerOptions = {
  mode: 'history',
  base: '/',
  linkActiveClass: 'nuxt-link-active',
  linkExactActiveClass: 'nuxt-link-exact-active',
  scrollBehavior,

  routes: [{
    path: "/admins",
    component: _05e9a883,
    name: "admins"
  }, {
    path: "/dadosbiomedicos",
    component: _330d9e20,
    name: "dadosbiomedicos"
  }, {
    path: "/doentes",
    component: _db3ed502,
    name: "doentes"
  }, {
    path: "/planos",
    component: _2408d5cc,
    name: "planos"
  }, {
    path: "/prescricoes",
    component: _2dc1c0a9,
    name: "prescricoes"
  }, {
    path: "/profissionaisSaude",
    component: _3bd213d2,
    name: "profissionaisSaude"
  }, {
    path: "/admins/create",
    component: _42009aca,
    name: "admins-create"
  }, {
    path: "/admins/remove",
    component: _6243f3ba,
    name: "admins-remove"
  }, {
    path: "/admins/update",
    component: _7bbf9be8,
    name: "admins-update"
  }, {
    path: "/auth/login",
    component: _026a29b6,
    name: "auth-login"
  }, {
    path: "/dadosbiomedicos/create",
    component: _565a709e,
    name: "dadosbiomedicos-create"
  }, {
    path: "/doentes/create",
    component: _3d3335c2,
    name: "doentes-create"
  }, {
    path: "/doentes/remove",
    component: _5d768eb2,
    name: "doentes-remove"
  }, {
    path: "/doentes/update",
    component: _7e264e6c,
    name: "doentes-update"
  }, {
    path: "/planos/create",
    component: _0da94e38,
    name: "planos-create"
  }, {
    path: "/prescricoes/create",
    component: _9baac196,
    name: "prescricoes-create"
  }, {
    path: "/profissionaisSaude/create",
    component: _6624b12c,
    name: "profissionaisSaude-create"
  }, {
    path: "/profissionaisSaude/remove",
    component: _560304b4,
    name: "profissionaisSaude-remove"
  }, {
    path: "/profissionaisSaude/update",
    component: _02e49a79,
    name: "profissionaisSaude-update"
  }, {
    path: "/admins/:id",
    component: _5b7b0312,
    name: "admins-id"
  }, {
    path: "/dadosbiomedicos/:id",
    component: _fb8efa0c,
    name: "dadosbiomedicos-id"
  }, {
    path: "/doentes/:id",
    component: _05444efb,
    name: "doentes-id"
  }, {
    path: "/prescricoes/:id",
    component: _06918e11,
    name: "prescricoes-id"
  }, {
    path: "/profissionaisSaude/:id",
    component: _2ab3cc88,
    name: "profissionaisSaude-id"
  }, {
    path: "/admins/:id/edit",
    component: _700479f5,
    name: "admins-id-edit"
  }, {
    path: "/dadosbiomedicos/:id/edit",
    component: _4e6b4ddc,
    name: "dadosbiomedicos-id-edit"
  }, {
    path: "/doentes/:id/associateDoenteWithPrescricao",
    component: _6cb39af9,
    name: "doentes-id-associateDoenteWithPrescricao"
  }, {
    path: "/doentes/:id/dadosbiomedicos",
    component: _c610afa8,
    name: "doentes-id-dadosbiomedicos"
  }, {
    path: "/doentes/:id/edit",
    component: _45f6ee1e,
    name: "doentes-id-edit"
  }, {
    path: "/prescricoes/:id/associatePlanoWithPrescricao",
    component: _4eb2dfe6,
    name: "prescricoes-id-associatePlanoWithPrescricao"
  }, {
    path: "/prescricoes/:id/edit",
    component: _40280f1b,
    name: "prescricoes-id-edit"
  }, {
    path: "/profissionaisSaude/:id/edit",
    component: _5a18a5c4,
    name: "profissionaisSaude-id-edit"
  }, {
    path: "/doentes/:id/dadosbiomedicos/create",
    component: _29b1a812,
    name: "doentes-id-dadosbiomedicos-create"
  }, {
    path: "/",
    component: _0dfe6287,
    name: "index"
  }],

  fallback: false
}

export function createRouter (ssrContext, config) {
  const base = (config._app && config._app.basePath) || routerOptions.base
  const router = new Router({ ...routerOptions, base  })

  // TODO: remove in Nuxt 3
  const originalPush = router.push
  router.push = function push (location, onComplete = emptyFn, onAbort) {
    return originalPush.call(this, location, onComplete, onAbort)
  }

  const resolve = router.resolve.bind(router)
  router.resolve = (to, current, append) => {
    if (typeof to === 'string') {
      to = normalizeURL(to)
    }
    return resolve(to, current, append)
  }

  return router
}
