document
.getElementById("registerForm")
.addEventListener(
"submit",
async function(e){

e.preventDefault();

const fullName =
document.getElementById("fullName").value;

const email =
document.getElementById("email").value;

const password =
document.getElementById("password").value;

const response =
await fetch(
"/api/auth/register",
{
method:"POST",

headers:{
"Content-Type":
"application/json"
},

body:JSON.stringify({

fullName,
email,
password

})
});

const data =
await response.json();

if(data.token){

alert(
"Registration Successful"
);

window.location.href=
"/login";

}
});