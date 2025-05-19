const user = JSON.parse(sessionStorage.getItem("userDetails"));

//Dados do usuário
const pacienteNome = document.getElementById("paciente-nome");
const pacienteEmail = document.getElementById("paciente-email");
const pacienteIdade = document.getElementById("paciente-idade");
const pacienteTelefone = document.getElementById("paciente-telefone");
const paciente = document.getElementById("paciente");
const agendarConsulta = document.getElementById("agendarConsulta");

//Id do usuário
const pacienteId = user.id;

//Preenchendo dados do usuário
pacienteNome.innerHTML = user.name;
pacienteEmail.innerHTML = user.email;
pacienteIdade.innerHTML = user.idade;
pacienteTelefone.innerHTML = user.telefone;
paciente.value = user.name;
paciente.disabled = true;




getAgendamentosPaciente();

getAllMedics().then(medicos => {
    const select = document.getElementById("medico");
    const especialidade = document.getElementById("especialidade");

    medicos.forEach(medico => {
        const option = document.createElement("option");
        const especialidadeOption = document.createElement("option");

        option.value = medico.id;
        option.textContent = `${medico.nome} - ${medico.especialidade}`;

        // Adiciona especialidades únicas ao select
        if (![...especialidade.options].some(opt => opt.value === medico.especialidade)) {
            especialidadeOption.value = medico.especialidade;
            especialidadeOption.textContent = medico.especialidade;
            especialidade.appendChild(especialidadeOption);
        }

        select.appendChild(option);
        especialidade.appendChild(especialidadeOption);
    });
});

async function getAgendamentosPaciente() {
    const response = await fetch(`http://localhost:8080/atendimento/${pacienteId}`);
    const data = await response.json();

    data.forEach(element => {

        console.log(element);


        var table = document.getElementById("table");

        var tbody = document.createElement("tbody");
        var tr = document.createElement("tr");
        var td1 = document.createElement("td");
        var td2 = document.createElement("td");
        var td3 = document.createElement("td");
        var td4 = document.createElement("td");
        var td5 = document.createElement("td");
        var h5 = document.createElement("h5");


        table.appendChild(tbody);
        tbody.appendChild(tr);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        tr.appendChild(td4);
        tr.appendChild(td5);
        td5.appendChild(h5);

        td1.innerHTML = element.id;
        td2.innerHTML = user.name;

        const data = new Date(element.dataAtendimento);
        const dia = String(data.getDate()).padStart(2, '0');
        const mes = String(data.getMonth() + 1).padStart(2, '0');
        const ano = data.getFullYear();
        // Formata o horário para HH:MM
        const [hora, minuto] = element.horaAtendimento.split(':');
        const horaFormatada = `${hora.padStart(2, '0')}:${minuto.padStart(2, '0')}`;
        td3.innerHTML = `${dia}/${mes}/${ano} - ${horaFormatada}`;

        td4.innerHTML = element.especialidade;

        h5.innerHTML = element.status == 1 ? "Aprovado" : "Aguardando Aprovação";
    });

    return data;
}


async function getAllMedics() {
    const response = await fetch(`http://localhost:8080/medico`);
    const data = await response.json();

    data.forEach(element => {
        console.log(element);
    });

    return data;
}

// Adiciona o evento de clique ao botão "Agendar Consulta"
agendarConsulta.addEventListener("click", function () {

    //Capturando valores dos campos
    const especialidade = document.getElementById("especialidade");
    const dataAtendimento = document.getElementById("dataAtendimento");
    const horaAtendimento = document.getElementById("horaAtendimento");
    const sala = document.getElementById("sala");
    const medico = document.getElementById("medico");
    // const paciente = document.getElementById("paciente");


    const agendamento = {
        paciente: user.id,
        medico: Number(medico.value),
        dataAtendimento: dataAtendimento.value,
        horaAtendimento: horaAtendimento.value,
        especialidade: especialidade.value,
        status: false,
        sala: sala.value
    }
    
    const response = fetch(`http://localhost:8080/atendimento`, {
        method: "POST",
        body: JSON.stringify(agendamento),
        headers: {
            "Content-Type": "application/json"
        },
    });


    if (response.status == 400) {
        alert("Erro ao agendar consulta.");
    } else if (response.status == 500) {
        alert("Erro ao agendar consulta.");
    } else {
        alert("Consulta agendada com sucesso!");
        window.location.reload();
    }

});
