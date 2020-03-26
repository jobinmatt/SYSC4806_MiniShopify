<template>
  <div class="content">
    <div>
      <h2><b>YOUR SHOPS</b></h2>
      <div class="flex-form">
        <li v-repeat="shops">
          <ShopItem/>
        </li>
      </div>
    </div>
  </div>
</template>

<script>
  import ShopItem from "../components/ShopItem";

  export default {
    data() {
      return {
        shops: [],
      }
    },
    components: {
      ShopItem
    },
    name: "UserShopsPage"
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
