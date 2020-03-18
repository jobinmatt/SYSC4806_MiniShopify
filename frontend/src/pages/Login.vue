<template>
  <div class="content">
    <icon></icon><h1>Mini-Shopify</h1>
    <h4>LOGIN</h4>
    <h4>Kickstart Your Business Today!</h4>
    <p v-if="errors.length">
    <ul>
      <li v-for="error in errors" class="errors"> {{ error }} </li>
    </ul>
    </p>
    <input v-model="email" class="loginform" type="email" placeholder="Email"><br>
    <input v-model="password" class="loginform" type="password" placeholder="Password"><br>
    <button @click="this.login">Login</button>
  </div>
</template>
<script>
  import axios from 'axios'
  export default {
    data () {
      return {
        response: [],
        errors: [],
        email:"",
        password:"",
      }
    },
    methods: {
      isValid: function (e){
        this.errors =[];
        if (!this.email) {
          this.errors.push('Email required.');
        } else if (!this.validEmail(this.email)) {
          this.errors.push('Valid email required.');
        }
        if (!this.password){
          this.errors.push('Password required.');
        }
        if (this.errors.length==0){
          return true;
        }else{
          return false;
        }
        e.preventDefault();
      },
      validEmail: function (email){
        var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;

        return re.test(email);
      },
      passwordConfirmed: function (p, cp){
        if (p===cp) {
          return true;
        }else{
          return false;
        }
      },
      signUp: function (e) {
        if (!this.isValid(e)){
          console.log("invalid form")
          return
        }else{
          axios.post('/api/owner', {
            firstName: this.firstName,
            lastName: this.lastName,
            email: this.email,
            password:this.password
          })
            .then((response)=> {
              console.log(response);
              this.$router.push({path: '/'})
            })
            .catch((error)=> {
              console.log(error);
            });
        }


      }

    }


  }
</script>
<style>
  .errors{
    list-style-type: none;
    border: 2px solid darkred;
    border-radius: 5px;
    font-size: 12px;
    width:100%
  }
</style>
