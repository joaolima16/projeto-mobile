const btnLogin = document.getElementById("login");
const btnRegister = document.getElementById("register");
const btnLogout = document.getElementById("logout");
const agendamento = document.getElementById("agendamento");

const user = JSON.parse(sessionStorage.getItem("user"));

// ../Tela_Cadastro/Login/login.html


getUserData();

async function getUserData() {
    
    if (sessionStorage.getItem("user") == null) {
        console.log("Cliente Nulo!");
        btnLogout.style.visibility = "hidden";
    } else {
        
        const email = user.email;
        const endpointMontado = `http://localhost:8080/paciente/${email}`;
        
        await fetch(endpointMontado).then(response => {
            if (response.ok) {
                
            }
            return response.json();
        }).then((data) => {
            sessionStorage.setItem("userDetails", JSON.stringify(data));
            
            console.log(data);
            
            btnRegister.style.visibility = "hidden";
            btnLogin.style.visibility = "hidden";
            
            
        }).catch((error) => {
            console.error(error);
        });
    }
}

agendamento.addEventListener("click", function(){
    if (sessionStorage.getItem("user") == null) {
        alert("Você precisa estar logado para acessar essa página!");
        window.location.href = "../Tela_Cadastro/Login/login.html";
    } else {
        window.location.href = "../Tela_ConsultaMedica/consulta.html";
    }
});

//LOGOUT
btnLogout.addEventListener("click", function(){
    sessionStorage.clear();
    window.location.href = "../Tela_Cadastro/Login/login.html";
})