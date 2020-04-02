<template>
  <div class='content'>
    <h2><b>EDIT YOUR SHOP</b></h2>
    <div class='flex-form'>
      <label for='shop_name'>Shop Name:</label><input class='form_input' type='text' v-model='name'
                                                      placeholder='Enter shop name...' id='shop_name'/>
      <label for='shop_description'>Shop Description:</label><input class='form_input' type='text'
                                                                    v-model='description'
                                                                    placeholder='Enter shop description...'
                                                                    id='shop_description'/>
      <label for='shop_tags'>Shop Tags:</label><input class='form_input' type='text' v-model='tags'
                                                      placeholder='Enter shop tags (separate tags with a comma)...'
                                                      id='shop_tags'/>
      <div class="editItem_container">
        <div v-for='(item, index) in editItemsComponents' v-bind:key='item' v-bind:is='item' v-bind:index='index'/>
        <button class='add_button' v-on:click='addItemComponent'>Add Item</button>
      </div>
      <button class='button' v-on:click='createShop'>Create Shop</button>
    </div>
  </div>
</template>

<script>
  import axios from 'axios'
  import EditItem from '../components/EditItem'
  import {
    getCookie,
    OWNER_ID_HEADER_STRING,
    TOKEN_COOKIE_HEADER,
    TOKEN_PREFIX
  } from '../constants/constants'

  export default {
    name: 'CreateShopPage',
    components: {
      EditItem,
    },
    data() {
      return {
        errors: [],
        name: '',
        description: '',
        tags: '',
        userId: null,
        editItemsComponents: [],
        items: []
      }
    },
    methods: {
      addItemComponent() {
        this.editItemsComponents.push(EditItem);
      },
      removeItemComponent(index) {
        this.editItemsComponents.splice(index, 1);
        this.items.splice(index, 1);
      },
      addItem() {
        this.items.push({
          name: '',
          desc: '',
          price: '',
          stock: ''
        })
      },
      updateItem(index, item) {
        if (index >= 0) {
          this.items[index] = item;
        }
      },
      getTags(input) {
        // get individual tags
        let tempTags = input.split(',');
        let tags = [];
        tempTags.forEach(function (tag) {
          tags.push(tag.trim())
        });
        return tags
      },
      createShop() {
        if (this.checkInputFields()) {
          let token = JSON.parse(getCookie(TOKEN_COOKIE_HEADER));
          this.userId = token[OWNER_ID_HEADER_STRING];
          let tags = this.getTags(this.tags);
          axios.post('/api/shop', {
            name: this.name,
            desc: this.description,
            tags: tags,
            userId: this.userId,
            products: this.items
          }, {headers: {Authorization: TOKEN_PREFIX + token[TOKEN_COOKIE_HEADER]}})
            .then((response) => {
              console.log(response.status);
              this.$router.push({path: '/all_shops'})
            })
            .catch((error) => {
              console.log(error)
            })
        }
      },
      checkInputFields() {
        let success = true;
        if (this.name !== '' && this.description !== '' && this.tags !== '' && this.items.length > 0) {
          let tags = this.tags.split(',');
          tags.forEach(function (tag) {
            if (tag.trim() === '') {
              console.log('Shop tags not valid');
              success = false
            }
          });
          this.items.forEach(function (item) {
            if (item.name === '' || item.desc === '' || item.price === '' || item.price < 0 || item.stock < 0 || item.stock === '') {
              console.log('Product information not valid');
              success = false
            }
          })
        } else {
          return false
        }
        return success
      }
    }
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

  .form_input {
    margin: 10px 5% 10px 5%;
    padding: 10px;
    border-radius: 10px;
    border: #FFFFFF;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.25);
  }

  .editItem_container {
    flex-direction: column;
    align-items: center;
    justify-content: center;
  }

  .add_button {
    width: 50%;
    margin: 10px;
    padding: 10px;
    background-color: #A3CBFF;
    border: #A3CBFF;
    border-radius: 10px;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.25);
  }

  .button {
    margin: 10px 5%;
    padding: 10px;
    background-color: #f0f0f0;
    border: #f0f0f0;
    border-radius: 10px;
    box-shadow: 0 0 4px rgba(0, 0, 0, 0.25);
  }
</style>