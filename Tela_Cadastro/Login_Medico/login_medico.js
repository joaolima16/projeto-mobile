document.getElementById('login').addEventListener('click', function () {
  const email = document.getElementById('email').value;
  const senha = document.getElementById('senha').value;

  console.log('Email:', email);
  console.log('Senha:', senha);

  const usuario = {
    "email": email,
    "senha": senha,
  };

  if (usuario.email == '' || usuario.senha == '') {
    alert('Usuário ou senha não preenchidos!')

  } else {
    // Chamar a API para fazer login
    fetch("http://localhost:8080/medico/login", {
      method: "POST",
      body: JSON.stringify(usuario),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then(response => {
        (response.status);
        if (response.status == 400) {
          throw new Error(JSON.stringify("Usuário ou senha incorretos."));;
        } else if (response.status == 423) {
          throw new Error(JSON.stringify("Usuário não ativo no banco de dados."));;
        }
        else if (response.status == 500) {
          throw new Error(JSON.stringify("Dados não corretos"));;
        }
        return response.text();
      })
      .then(data => {
        // Redirecionar para outra página após o login bem-sucedido
        // sessionStorage.setItem("userBackOffice", JSON.stringify(usuario));
        setTimeout(window.location.href = "../../Tela_admMedico/admMedico.html", 2000);
        sessionStorage.setItem('user', JSON.stringify(usuario));
      })
      .catch(error => {
        console.error(error);
        alert('Erro!')
      });
  }
});



document.getElementById('cadastro').addEventListener('click', function () {


})