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
import AllShopsPage from './pages/AllShopsPage';
import CreateShopPage from "./pages/CreateShopPage";

Vue.config.productionTip = false
Vue.use(VueRouter)

const routes = [
  {path: '/', component: Dashboard},
  {path: '/signup', component: SignUp},
  {path: '/cart', component: Cart, props: true},
  {path: '/error', component: Error},
  {path: '/login', component: Login},
  {path: '/shop', name: 'shop', component: ShopPage, props: (route) => ({shopId: route.query.shopId})},
  {path: '/create', name: 'create', component: CreateShopPage},
  {path: '/all_shops', component: AllShopsPage, props: {allShops: false}}
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
