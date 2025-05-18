const nome = document.getElementById("nome");
const emailCadastro = document.getElementById("email");
const cpf = document.getElementById("cpf");
const idade = document.getElementById("idade");
const telefone = document.getElementById("telefone");
const endereco = document.getElementById("endereco");
const confirmSenha = document.getElementById("confirmSenha");
const senhaCadastro = document.getElementById("senha");

document.getElementById("cadastro").addEventListener('click', function () {

    const usuarioCadastro = {
        "nome": nome.value,
        "email": emailCadastro.value,
        "cpf": cpf.value,
        "endereco": endereco.value,
        "telefone": telefone.value,
        "idade": idade.value,
        "senha": senha.value,
    };



    if (nome.value == "") {
        alert("Preencha o nome do usuário!")
    }
    else if (!validaSenha() || senhaCadastro.value == "") {
        console.log(senhaCadastro.value);
        console.log(confirmSenha.value);
        alert("Senhas não preenchidas ou não coincidem.");
    } else if (emailCadastro.value == "") {
        alert("Email não preenchido")
    } else {
        console.log(usuarioCadastro);
        //     //Chamar a API para fazer login
        fetch("http://localhost:8080/paciente", {
            method: "POST",
            body: JSON.stringify(usuarioCadastro),
            headers: {
                "Content-Type": "application/json",
            },
        })
            .then(response => {
                (response.status);
                if (response.status == 400) {
                    throw new Error(JSON.stringify("Problema durante o cadastro do usuário"));;
                }
                else if (response.status == 500) {
                    throw new Error(JSON.stringify("Dados não corretos"));;
                } else if (!validaSenha()) {
                    alert("Senhas não coincidem!")
                }
                return response.text();
            })
            .then(data => {
                alert("Usuário cadastrado na base de dados.")
                setTimeout(window.location.href = "Login/login.html", 2000); // A função será executada após 2 segundos (2000 milissegundos)
            })
            .catch(error => {
                alert(error);
            });
    }
});

function validaSenha() {
    if (senhaCadastro.value == confirmSenha.value) {
        return true;
    } else {
        return false;
    }
}
