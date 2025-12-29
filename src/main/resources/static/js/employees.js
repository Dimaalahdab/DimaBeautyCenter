const API = "http://localhost:8088/employees";

const id = document.getElementById("id");
const empName = document.getElementById("empName");
const specialty = document.getElementById("specialty");
const table = document.getElementById("table");


function loadEmployees() {
    fetch(API)
        .then(r => r.json())
        .then(data => {
            table.innerHTML = "";
            data.forEach(e => {
                table.innerHTML += `
                <tr>
                    <td>${e.id}</td>
                    <td>${e.name}</td>
                    <td>${e.specialty}</td>
                    <td>
                        <button onclick="edit(${e.id}, '${e.name}', '${e.specialty}')">Edit</button>
                        <button onclick="del(${e.id})">Delete</button>
                    </td>
                </tr>`;
            });
        });
}

function saveEmployee() {
    let method = id.value ? "PUT" : "POST";
    let url = id.value ? API + "/" + id.value : API;

    fetch(url, {
        method: method,
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: empName.value,
            specialty: specialty.value
        })
    }).then(() => {
        id.value = "";
        empName.value = "";
        specialty.value = "";
        loadEmployees();
    });
}

function edit(i, n, s) {
    id.value = i;
    empName.value = n;
    specialty.value = s;
}


function edit(i, n, s) {
    id.value = i;
    name.value = n;
    specialty.value = s;
}

function del(i) {
    fetch(API + "/" + i, { method: "DELETE" })
        .then(() => loadEmployees());
}

loadEmployees();
