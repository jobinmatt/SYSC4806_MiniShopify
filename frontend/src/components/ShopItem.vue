<template>
  <div class="content">
    <div class="center">
      <h1><b>Shop: {{name}}</b></h1>
      <div class="flex-form">
        <h2>Description: {{description}}</h2>
        <h2>Owner: {{ownerName}}</h2>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: {name: String, description: String, ownerName: String},
    name: "ShopItem"
  }
</script>

<style scoped>
  .content {
    flex: 0, 32%;
    margin: 2% 15% 0% 15%;
    background: #A3CBFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 49px;
    padding-top: 5%;
    padding-bottom: 5%;
    text-align: center;
  }

  .center {
    margin: auto;
    width: 50%;
  }

  .flex-form {
    display: flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
  }
</style>
