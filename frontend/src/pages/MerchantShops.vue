<template>
  <div class="content">
    <div>
      <h1><b>MERCHANT SHOPS</b></h1>
      <div class="flex-form">
        <ShopItem v-for="shop in shops" :key="shop.id" v-bind:name="shop.name" v-bind:description="shop.description"
                  v-bind:owner-name="shop.owner.firstName +' '+ shop.owner.lastName"/>
      </div>
    </div>
  </div>
</template>

<script>
  import ShopItem from "../components/ShopItem";
  import {
    getCookie,
    OWNER_ID_HEADER_STRING,
    STATUS_OK_CODE,
    TOKEN_COOKIE_HEADER,
  } from "../constants/constants";
  import axios from "axios";

  export default {
    name: "MerchantShops",
    components: {
      ShopItem
    },
    mounted() {
      if (getCookie(TOKEN_COOKIE_HEADER) !== '') {
        var token = JSON.parse(getCookie(TOKEN_COOKIE_HEADER))
        this.userId = token[OWNER_ID_HEADER_STRING]
      }
      this.loadShops()
    },
    data() {
      return {
        userId: null,
        shops: [],
      }
    },
    methods: {
      loadShops() {
        var currentUserId = this.userId;
        var userShops = [];
        axios.get('/api/public/allShops')
          .then((response) => {
            if (response.status === STATUS_OK_CODE) {
              if (currentUserId !== null) {
                response.data.forEach(function (shop) {
                  if (currentUserId == shop.owner.id) {
                    userShops.push(shop);
                  }
                });
                this.shops = userShops;
              } else {
                this.shops = response.data;
              }
            }
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
    margin: 2% 15% 0% 15%;
    background: #DDECFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 49px;
    padding-top: 5%;
    padding-bottom: 5%;
    text-align: center;
  }

  .flex-form {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>
