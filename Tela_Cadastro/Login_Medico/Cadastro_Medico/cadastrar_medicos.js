const nome = document.getElementById("nome");
const emailCadastro = document.getElementById("email");
const crm = document.getElementById("crm");
const especialidade = document.getElementById("especialidade");
const telefone = document.getElementById("telefone");
const confirmSenha = document.getElementById("confirmSenha");
const senhaCadastro = document.getElementById("senha");

document.getElementById("cadastro").addEventListener('click', function () {

    const medicoCadastrado = {
        "nome": nome.value,
        "email": emailCadastro.value,
        "crm": crm.value,
        "especialidade": especialidade.value,
        "endereco": especialidade.value,
        "telefone": telefone.value,
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
    } else if(!validaCrm(crm.value)){
        alert('CRM inválido!')
    }
    else {
        console.log(medicoCadastrado);
        //     //Chamar a API para fazer login
        fetch("http://localhost:8080/medico", {
            method: "POST",
            body: JSON.stringify(medicoCadastrado),
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
                setTimeout(window.location.href = "../login_medico.html", 2000); // A função será executada após 2 segundos (2000 milissegundos)
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

function validaCrm(crmValue) {
    const value = crmValue.trim();
    // Verifica se tem exatamente 4 dígitos e todos são ímpares
    return /^[13579]{4}$/.test(value);
}
