let col_id=document.getElementById("col_id")
let col_name=document.getElementById("col_name")
let col_email=document.getElementById("col_email")
let col_role=document.getElementById("col_role")

col_id.innerHTML=localStorage.getItem("userId")
col_name.innerHTML=localStorage.getItem("userName")
col_email.innerHTML=localStorage.getItem("userEmail")
col_role.innerHTML=localStorage.getItem("userRole")