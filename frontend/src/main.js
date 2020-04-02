// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import VueRouter from 'vue-router'
import Dashboard from './pages/Dashboard'
import SignUp from './pages/SignUp'
import Cart from './pages/Cart'
import Error from './pages/Error'
import Login from './pages/Login'
import ShopPage from './pages/ShopPage'
import MerchantShops from './pages/MerchantShops';
import Search from './pages/Search'

Vue.config.productionTip = false
Vue.use(VueRouter)

const routes = [
  {path: '/', component: Dashboard},
  {path: '/signup', component: SignUp},
  {path: '/cart', component: Cart, props: true},
  {path: '/error', component: Error},
  {path: '/login', component: Login},
  {path: '/shop', component: ShopPage, props: {edit: false}},
  {path: '/merchant', component: MerchantShops, props: true},
  {path: '/search', component: Search}
]

const router = new VueRouter({
  routes
})
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: {App},
  template: '<App/>'
})
