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

            console.log(data);

            nome.innerHTML = data.nome;
            especialidade.innerHTML = data.especialidade;
            telefone.innerHTML = data.telefone;
            crm.innerHTML = data.crm;

            const patientTableBody = document.getElementById("patientTableBody");

            data.atendimentos.forEach(element => {
                var tr = document.createElement("tr");
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");

                var buttonAccept = document.createElement("button");
                var buttonReject = document.createElement("button");

                buttonAccept.classList.add("btn" , "approve");
                buttonReject.classList.add("btn" , "reject");
                buttonAccept.style.marginRight = "10px";

                buttonAccept.setAttribute("onclick", `acceptAppointment(${element.id})`);
                buttonReject.setAttribute("onclick", `rejectAppointment(${element.id})`);

                buttonAccept.innerHTML = "Aceitar";
                buttonReject.innerHTML = "Rejeitar";

                td1.innerHTML = element.id;
                td3.innerHTML = element.dataAtendimento;


                patientTableBody.appendChild(tr);
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);
                tr.appendChild(td4);
                tr.appendChild(td5);
                td5.appendChild(buttonAccept);
                td5.appendChild(buttonReject);

            });


            btnRegister.style.visibility = "hidden";
            btnLogin.style.visibility = "hidden";


        }).catch((error) => {
            console.error(error);
        });
    }
}