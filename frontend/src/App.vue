<template>
  <div id='app'>
    <div id='navbar'>
      <div>
        <icon></icon>
        <span id='title'> Mini-Shopify </span>
      </div>
      <div class='links'>
        <span><a @click='this.toggleLogin'>{{linkTextLogin}}</a></span>
        <router-link class='signup_button' to='/signup'
                     tag='button'>Sign Up
        </router-link>
      </div>
    </div>
    <router-view @userLogin='this.updateBar'></router-view>
  </div>
</template>

<script>
  import {TOKEN_COOKIE_HEADER, setCookie} from './constants/constants'

  export default {
    name: 'App',
    data() {
      return {
        isUserLogged: false,
        linkTextLogin: 'Login'
      }
    },
    methods: {
      updateBar: function (e) {
        this.isUserLogged = true
        this.linkTextLogin = 'Logout'
      },
      logoutUser: function () {
        // deleting token
        setCookie(TOKEN_COOKIE_HEADER, '', 0)
        this.isUserLogged = false
        this.linkTextLogin = 'Login'
      },
      toggleLogin: function () {
        if (this.isUserLogged) {
          this.logoutUser()
        } else {
          this.$router.push({path: '/login'})
        }
      }
    }
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Nunito&display=swap');

  * {
    font-family: Nunito;
  }

  #navbar {
    display: flex;
    margin-left: 15%;
    margin-right: 15%;
    padding-left: 2%;
    padding-right: 5%;
    background: #DDECFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
    justify-content: space-between;
  }

  #title {
    font-style: normal;
    font-weight: bold;
    font-size: 36px;
    line-height: 49px;
  }

  .links {
    display: flex;
    line-height: 49px;
    width: 200px;
    justify-content: space-between;
  }

  input {
    padding: 12px 20px;
    margin: 8px 4px;
    box-sizing: border-box;
    border-radius: 10px;
  }

  .signup_button {
    padding: 5% 10% 5% 10%;
    margin: auto;
    background: #007DC4;
    border-radius: 8px;
    box-shadow: 2px 4px 4px rgba(0, 0, 0, 0.1);
    color: white;
  }
</style>
