<template>
  <div class="content">
    <h2>Name: {{name}}</h2>
    <h3>Quantity: {{quantity}}</h3>
  </div>
</template>

<script>
  export default {
    name: "CartItem",
    props: {name: String, id: Number, quantity: Number},
    methods: {},
  }
</script>

<style scoped>
  .content {
    flex: 1;
    cursor: pointer;
    margin: 10px 5%;
    background: #A3CBFF;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.25);
    border-radius: 49px;
    text-align: center;
  }

</style>
