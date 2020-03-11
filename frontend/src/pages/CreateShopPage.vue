<template>
  <div class="content">
    <div class="center">
      <h2><b>CREATE A SHOP</b></h2>
      <div class="flex-form">
        <h2>SHOP NAME:</h2>
        <input class="form_input" type="text" placeholder="Enter shop name..." id="shop_name"><br>
        <h2>SHOP DESCRIPTION:</h2>
        <input class="form_input" type="text" placeholder="Enter shop description..." id="shop_description"><br>
        <h2>SHOP TAGS:</h2>
        <input class="form_input" type="text" placeholder="Enter shop tags..." id="shop_tags"><br>

        <button>Create Shop</button>
      </div>
    </div>
  </div>
</template>
<style>
  .content{
    margin: auto;
    margin-top: 2%;
    width: 50%;
    background: #DDECFF;
    box-shadow: 0px 4px 4px rgba(0, 0, 0, 0.25);
    border-radius: 49px;
    padding-top: 5%;
    padding-bottom: 5%;
    text-align: center;
  }
  .center{
    margin:auto;
    width:50%;
  }
  .flex-form{
    display:flex;
    flex-direction: column;
    flex-wrap: wrap;
    justify-content: center;
  }
  .half{
    display: flex;
    flex-direction: row;
    align-items: center;
  }
  .instr{
    text-align: left;
  }
</style>
