import Vue from 'vue'
import Router from 'vue-router'
import { normalizeURL, decode } from 'ufo'
import { interopDefault } from './utils'
import scrollBehavior from './router.scrollBehavior.js'

const _71a7bf68 = () => interopDefault(import('..\\pages\\admins\\index.vue' /* webpackChunkName: "pages/admins/index" */))
const _1b0b0e4a = () => interopDefault(import('..\\pages\\dadosbiomedicos\\index.vue' /* webpackChunkName: "pages/dadosbiomedicos/index" */))
const _c335498c = () => interopDefault(import('..\\pages\\doentes\\index.vue' /* webpackChunkName: "pages/doentes/index" */))
const _cf580992 = () => interopDefault(import('..\\pages\\profissionaisSaude\\index.vue' /* webpackChunkName: "pages/profissionaisSaude/index" */))
const _29f70f54 = () => interopDefault(import('..\\pages\\admins\\create.vue' /* webpackChunkName: "pages/admins/create" */))
const _181ab5db = () => interopDefault(import('..\\pages\\auth\\login.vue' /* webpackChunkName: "pages/auth/login" */))
const _f6ee257a = () => interopDefault(import('..\\pages\\dadosbiomedicos\\create.vue' /* webpackChunkName: "pages/dadosbiomedicos/create" */))
const _55fa56c4 = () => interopDefault(import('..\\pages\\doentes\\create.vue' /* webpackChunkName: "pages/doentes/create" */))
const _cc409332 = () => interopDefault(import('..\\pages\\profissionaisSaude\\create.vue' /* webpackChunkName: "pages/profissionaisSaude/create" */))
const _1ea7eeb2 = () => interopDefault(import('..\\pages\\admins\\_id\\index.vue' /* webpackChunkName: "pages/admins/_id/index" */))
const _7443b5c2 = () => interopDefault(import('..\\pages\\dadosbiomedicos\\_id\\index.vue' /* webpackChunkName: "pages/dadosbiomedicos/_id/index" */))
const _458ce620 = () => interopDefault(import('..\\pages\\doentes\\_id\\index.vue' /* webpackChunkName: "pages/doentes/_id/index" */))
const _52a3bc43 = () => interopDefault(import('..\\pages\\profissionaisSaude\\_id\\index.vue' /* webpackChunkName: "pages/profissionaisSaude/_id/index" */))
const _0f62965a = () => interopDefault(import('..\\pages\\admins\\_id\\edit.vue' /* webpackChunkName: "pages/admins/_id/edit" */))
const _0c855e4d = () => interopDefault(import('..\\pages\\dadosbiomedicos\\_id\\edit.vue' /* webpackChunkName: "pages/dadosbiomedicos/_id/edit" */))
const _6b259fa7 = () => interopDefault(import('..\\pages\\doentes\\_id\\dadosbiomedicos\\index.vue' /* webpackChunkName: "pages/doentes/_id/dadosbiomedicos/index" */))
const _2969f92c = () => interopDefault(import('..\\pages\\doentes\\_id\\edit.vue' /* webpackChunkName: "pages/doentes/_id/edit" */))
const _4ade52a9 = () => interopDefault(import('..\\pages\\profissionaisSaude\\_id\\edit.vue' /* webpackChunkName: "pages/profissionaisSaude/_id/edit" */))
const _21429ff7 = () => interopDefault(import('..\\pages\\doentes\\_id\\dadosbiomedicos\\create.vue' /* webpackChunkName: "pages/doentes/_id/dadosbiomedicos/create" */))
const _b4ffa328 = () => interopDefault(import('..\\pages\\index.vue' /* webpackChunkName: "pages/index" */))

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
    component: _71a7bf68,
    name: "admins"
  }, {
    path: "/dadosbiomedicos",
    component: _1b0b0e4a,
    name: "dadosbiomedicos"
  }, {
    path: "/doentes",
    component: _c335498c,
    name: "doentes"
  }, {
    path: "/profissionaisSaude",
    component: _cf580992,
    name: "profissionaisSaude"
  }, {
    path: "/admins/create",
    component: _29f70f54,
    name: "admins-create"
  }, {
    path: "/auth/login",
    component: _181ab5db,
    name: "auth-login"
  }, {
    path: "/dadosbiomedicos/create",
    component: _f6ee257a,
    name: "dadosbiomedicos-create"
  }, {
    path: "/doentes/create",
    component: _55fa56c4,
    name: "doentes-create"
  }, {
    path: "/profissionaisSaude/create",
    component: _cc409332,
    name: "profissionaisSaude-create"
  }, {
    path: "/admins/:id",
    component: _1ea7eeb2,
    name: "admins-id"
  }, {
    path: "/dadosbiomedicos/:id",
    component: _7443b5c2,
    name: "dadosbiomedicos-id"
  }, {
    path: "/doentes/:id",
    component: _458ce620,
    name: "doentes-id"
  }, {
    path: "/profissionaisSaude/:id",
    component: _52a3bc43,
    name: "profissionaisSaude-id"
  }, {
    path: "/admins/:id/edit",
    component: _0f62965a,
    name: "admins-id-edit"
  }, {
    path: "/dadosbiomedicos/:id/edit",
    component: _0c855e4d,
    name: "dadosbiomedicos-id-edit"
  }, {
    path: "/doentes/:id/dadosbiomedicos",
    component: _6b259fa7,
    name: "doentes-id-dadosbiomedicos"
  }, {
    path: "/doentes/:id/edit",
    component: _2969f92c,
    name: "doentes-id-edit"
  }, {
    path: "/profissionaisSaude/:id/edit",
    component: _4ade52a9,
    name: "profissionaisSaude-id-edit"
  }, {
    path: "/doentes/:id/dadosbiomedicos/create",
    component: _21429ff7,
    name: "doentes-id-dadosbiomedicos-create"
  }, {
    path: "/",
    component: _b4ffa328,
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
