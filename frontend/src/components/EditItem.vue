<template>
  <div class='content'>
    <button id='remove_button' @click='removeItem'>-</button>
    <div class='center'>
      <h1><b>CREATE AN ITEM</b></h1>
      <div class='flex-form'>
        <h2>ITEM NAME:</h2>
        <input class='form_input' type='text' v-model='name' placeholder='Enter item name...' id='item_name'><br>
        <h2>ITEM DESCRIPTION:</h2>
        <input class='form_input' type='text' v-model='description' placeholder='Enter item description...'
               id='item_description'><br>
        <h2>PRICE:</h2>
        <input class='form_input' type='number' min='0.00' step="1.00" v-model='stockPrice'
               placeholder='Enter item price...'
               id='item_price'><br>
        <h2>QUANTITY:</h2>
        <input class='form_input' type='number' min='0' v-model='stockQuantity' placeholder='Enter item quantity...'
               id='item_quantity'><br>
      </div>
    </div>
  </div>
</template>

<script>
  export default {
    props: {index: Number},
    mounted() {
      this.$parent.addItem();
    },
    data() {
      return {
        name: '',
        description: '',
        stockPrice: '',
        stockQuantity: ''
      }
    },
    watch: {
      name: function () {
        this.updateItem()
      },
      description: function () {
        this.updateItem()
      },
      stockPrice: function () {
        this.updateItem()
      },
      stockQuantity: function () {
        this.updateItem()
      },
    },
    name: 'EditItem',
    methods: {
      updateItem() {
        const item = {
          name: this.name,
          description: this.description,
          stockPrice: this.stockPrice,
          stockQuantity: this.stockQuantity
        }
        this.$parent.updateItem(this.$props.index, item);
      },
      removeItem() {
        this.$parent.removeItemComponent(this.$props.index);
      }
    }
  }
</script>

<style scoped>
  .content {
    margin: 2% 15% 0% 15%;
    background: #A3CBFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 49px;
    padding-top: 0;
    padding-bottom: 5%;
    text-align: center;
  }

  #remove_button {
    background-color: palevioletred;
    border-radius: 50%;
    padding: 2px 8px;
    color: #DDECFF;
    margin: 2% 0% 2% 90%;
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

  .form_input {
    margin: 10px;
    padding: 10px;
    border-radius: 10px;
  }
</style>
