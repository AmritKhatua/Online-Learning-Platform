let del = document.getElementById("delete");
 let userId = localStorage.getItem("userId");

del.addEventListener("click" ,(e) =>{
    e.preventDefault();

    let x = fetch(`http://localhost:8080/user/delete/${userId}` ,{
        method: 'DELETE'
    })

    x.then((response) =>{
        console.log(response);
        if(response.ok){
            localStorage.removeItem("userId");
            localStorage.removeItem("userEmail");
            localStorage.removeItem("jwtToken");
            localStorage.removeItem("userName");
            localStorage.removeItem("userRole");
            open("./index.html","_self")
        }
        
    }) .catch((err) =>{
        console.log(err);
        
    })
})