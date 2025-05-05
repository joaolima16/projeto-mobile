document.getElementById('recuperarSenha').addEventListener('click', function () {
    const email = document.getElementById('email').value;
    const senha = document.getElementById('senha').value;
    const newSenha = document.getElementById('newSenha').value;
  
    console.log('Email:', email);
    console.log('Senha:', senha);
    console.log('newSenha:', newSenha);

    window.location.href = "../Login/login.html";
  });