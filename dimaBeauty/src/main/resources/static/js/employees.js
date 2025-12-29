//  LOGIN CHECK 
if (localStorage.getItem("loggedIn") !== "true") {
    window.location.href = "login.html";
}
const API = "http://localhost:8088/employees";

const idInput = document.getElementById("id");
const nameInput = document.getElementById("name");
const specialtyInput = document.getElementById("specialty");
const table = document.getElementById("table");

function loadEmployees() {
    fetch(API)
        .then(res => res.json())
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
    const id = idInput.value;
    const name = nameInput.value.trim();
    const specialty = specialtyInput.value.trim();

    if (!name || !specialty) {
        alert("Fill all fields");
        return;
    }

    const method = id ? "PUT" : "POST";
    const url = id ? `${API}/${id}` : API;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: name,
            specialty: specialty
        })
    })
    .then(() => {
        idInput.value = "";
        nameInput.value = "";
        specialtyInput.value = "";
        loadEmployees();
    });
}

function edit(id, name, specialty) {
    idInput.value = id;
    nameInput.value = name;
    specialtyInput.value = specialty;
}

function del(id) {
    if (!confirm("Delete employee?")) return;
    fetch(`${API}/${id}`, { method: "DELETE" })
        .then(() => loadEmployees());
}

loadEmployees();
