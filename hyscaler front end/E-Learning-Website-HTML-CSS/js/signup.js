let userName = document.getElementById("username");
let password = document.getElementById("password");
let email = document.getElementById("email");
let role = document.getElementById("role");
let signup = document.getElementById("signup");


signup.addEventListener("click" ,(e) =>{
        e.preventDefault();

        let user= {
            name:userName.value,
            password : password.value,
            email : email.value,
            role: role.value

        }
    
        console.log(user);

        let x = fetch("http://localhost:8080/user/create",{
            method :'POST',
            headers:{
                'Content-Type' : 'application/json',
            },
            body:JSON.stringify(user)
        }).then((x) =>{
            console.log("Error is comming");
            
        }).then((x) =>{
            console.log("Error is comming");
        })
        open("login.html","_self")
})