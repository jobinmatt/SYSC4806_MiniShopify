<template>
  <div class="content">
    <div class="center">
      <icon></icon><h1>Mini-Shopify</h1>

      <div class="flex-form">
        <div class="instr">
          <h4>SIGN UP</h4>
          <h4>Kickstart Your Business Today!</h4>
        </div>
        <div class="half">
          <input class="signupform" id="firstname" type="text" placeholder="First Name">
          <input class="signupform" id="lastname" type="text" placeholder="Last Name">
        </div>
        <input class="signupform" type="text" placeholder="Email" id="email"><br>
        <input class="signupform" type="password" placeholder="Password" id="password"><br>
        <input class="signupform" type="password" placeholder="Confirm Password" id="cpassword"><br>
        <button>Sign Up</button>
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
  #firstname{
    width:50%;
  }
  #lastname{
    width: 50%;
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
