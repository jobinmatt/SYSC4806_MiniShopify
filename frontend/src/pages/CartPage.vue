<template>
  <div class="content">
    <div>
      <h2><b>YOUR CART</b></h2>
      <div class="flex-form">
        <CartItem v-for="item in items" :key="item.id" v-bind:id="item.id" v-bind:name="item.name"
                  v-bind:quantity="item.quantity"/>
      </div>
      <h3>{{cartTotal}}</h3>
    </div>
  </div>
</template>

<script>
  import CartItem from "../components/CartItem";
  import {
    getCookie,
    OWNER_ID_HEADER_STRING,
    STATUS_OK_CODE,
    TOKEN_COOKIE_HEADER,
    TOKEN_PREFIX,
  } from "../constants/constants";
  import axios from "axios";

  export default {
    name: "CartPage",
    components: {
      CartItem,
    },
    mounted() {
      if (getCookie(TOKEN_COOKIE_HEADER) !== '') {
        let token = JSON.parse(getCookie(TOKEN_COOKIE_HEADER));
        this.userId = token[OWNER_ID_HEADER_STRING];
      }
      this.getCart();
      this.getTotal();
    },
    data() {
      return {
        userId: null,
        items: [],
        cartTotal: null,
      }
    },
    methods: {
      getCart() {
        let token = JSON.parse(getCookie(TOKEN_COOKIE_HEADER));
        let currentUserId = this.userId;
        let cartItems = [];
        axios.get('/api/cart', {
          params: {ownerId: currentUserId},
          headers: {Authorization: TOKEN_PREFIX + token[TOKEN_COOKIE_HEADER]}
        })
          .then((response) => {
            console.log(response);
          })
          .catch((error) => {
            console.log(error);
          });
      },
      getTotal() {
        let token = JSON.parse(getCookie(TOKEN_COOKIE_HEADER));
        let currentUserId = this.userId;
        axios.get('/api/cart/total', {
          params: {ownerId: currentUserId},
          headers: {Authorization: TOKEN_PREFIX + token[TOKEN_COOKIE_HEADER]}
        })
          .then((response) => {
            console.log(response);
            this.cartTotal = response.data;
          })
          .catch((error) => {
            console.log(error);
          });
      },
    },
  }
</script>

<style scoped>
  .content {
    margin: 16px 15% 0 15%;
    background: #DDECFF;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.25);
    border-radius: 49px;
    padding-top: 16px;
    padding-bottom: 16px;
    text-align: center;
  }


  .flex-form {
    display: flex;
    flex-direction: column;
    justify-content: center;
  }
</style>
