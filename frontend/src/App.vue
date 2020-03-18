<template>
  <div id="app">
    <div id="navbar">
      <div>
        <icon></icon><span id="title"> Mini-Shopify </span>
      </div>
      <div class="links">
        <span><a onclick="this.$router.replace('/login')">Login</a></span>
        <button id="signup" onclick="this.$router.replace('/signup')">Sign Up</button>
      </div>
    </div>
    <router-view></router-view>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  #navbar {
    display: flex;
    margin-left: 15%;
    margin-right:15%;
    padding: 1%;
    padding-left: 2%;
    padding-right: 5%;
    background: #DDECFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 30px;
    justify-content: space-between;
  }
  #title {
    font-family: Nunito;
    font-style: normal;
    font-weight: bold;
    font-size: 36px;
    line-height: 49px;
  }
  .links{
    display: flex;
    line-height: 49px;
    width: 200px;
    justify-content: space-between;
  }
  #signup{
    padding: 3%;
    padding-left: 10%;
    padding-right: 10%;
    background: #007DC4;
    border-radius: 8px;
    box-shadow: 2px 4px 4px rgba(0, 0, 0, 0.1);
    color: white;
  }
  .errors{
    list-style-type: none;
    border: 2px solid darkred;
    border-radius: 5px;
    font-size: 12px;
  }
</style>
