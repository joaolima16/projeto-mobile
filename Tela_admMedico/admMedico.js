const medico = sessionStorage.getItem('user');
const usuario = JSON.parse(medico);

const btnRegister = document.getElementById("btnRegister");
const btnLogin = document.getElementById("btnLogin");

const nome = document.getElementById("doctor-name");
const especialidade = document.getElementById("doctor-specialty");
const telefone = document.getElementById("doctor-phone");
const crm = document.getElementById("doctor-crm");




console.log(usuario);

getUserData();

async function getUserData() {

    if (sessionStorage.getItem("user") == null) {
        console.log("Cliente Nulo!");
        btnLogout.style.visibility = "hidden";
    } else {

        const email = usuario.email;
        const endpointMontado = `http://localhost:8080/medico/${email}`;

        await fetch(endpointMontado).then(response => {
            if (response.ok) {

            }
            return response.json();
        }).then((data) => {
            sessionStorage.setItem("userDetails", JSON.stringify(data));


            nome.innerHTML = data.nome;
            especialidade.innerHTML = data.especialidade;
            telefone.innerHTML = data.telefone;
            crm.innerHTML = data.crm;

            const patientTableBody = document.getElementById("patientTableBody");



            data.atendimentos.forEach(element => {
                // Obter a data atual
                const dataAtual = new Date();

                // Converter a data do atendimento para objeto Date
                const dataAtendimento = new Date(element.dataAtendimento);

                if (dataAtendimento > dataAtual) {

                    console.log(dataAtual);
                    console.log(dataAtendimento);
                    console.log(element);
                    var tr = document.createElement("tr");
                    var td1 = document.createElement("td");
                    var td2 = document.createElement("td");
                    var td3 = document.createElement("td");
                    var td4 = document.createElement("td");
                    var td5 = document.createElement("td");
                    var td6 = document.createElement("td");

                    var buttonAccept = document.createElement("button");
                    var buttonReject = document.createElement("button");

                    buttonAccept.classList.add("btn", "approve");
                    buttonAccept.style.marginRight = "10px";

                    buttonAccept.setAttribute("onclick", `acceptAppointment(${element.id})`);
                    buttonReject.setAttribute("onclick", `rejectAppointment(${element.id})`);

                    buttonAccept.innerHTML = "Aceitar";

                    td1.innerHTML = element.id;
                    td2.innerHTML = element.paciente.name;
                    const data = new Date(element.dataAtendimento);
                    const dia = String(data.getDate()).padStart(2, '0');
                    const mes = String(data.getMonth() + 1).padStart(2, '0');
                    const ano = data.getFullYear();
                    const [hora, minuto] = element.horaAtendimento.split(':');
                    const horaFormatada = `${hora.padStart(2, '0')}:${minuto.padStart(2, '0')}`;
                    td3.innerHTML = `${dia}/${mes}/${ano} - ${horaFormatada}`;
                    td4.innerHTML = element.especialidade;
                    td5.innerHTML = element.sala;

                    patientTableBody.appendChild(tr);
                    tr.appendChild(td1);
                    tr.appendChild(td2);
                    tr.appendChild(td3);
                    tr.appendChild(td4);
                    tr.appendChild(td5);
                    tr.appendChild(td6);

                    console.log(element.status);

                    if (!element.status) {
                        td6.appendChild(buttonAccept);
                        // td6.appendChild(buttonReject);
                    } else {
                        td6.innerHTML = "Atendimento já aceito, aguardando a data!";
                        td6.style.fontWeight = "bold";
                    }

                    buttonAccept.addEventListener("click", function () {
                        updateAgendamento(element.id);
                    });

                }
            });


            btnRegister.style.visibility = "hidden";
            btnLogin.style.visibility = "hidden";


        }).catch((error) => {
            console.error(error);
        });
    }
}

function updateAgendamento(id) {

    ///Chamar a API para fazer login
    fetch(`http://localhost:8080/atendimento/update/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
        },
    })
        .then(data => {
            alert("Atendimento Aceito!");
            setTimeout(window.Location.reload, 2000);
        })
        .catch(error => {
            alert(error);
        });
}