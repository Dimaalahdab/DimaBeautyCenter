//  LOGIN CHECK 
if (localStorage.getItem("loggedIn") !== "true") {
    window.location.href = "login.html";
}
const API = "http://localhost:8088/clients";

const id = document.getElementById("id");
const name = document.getElementById("name");
const phone = document.getElementById("phone");
const table = document.getElementById("table");

function loadClients() {
    fetch(API)
        .then(res => res.json())
        .then(data => {
            table.innerHTML = "";
            data.forEach(c => {
                table.innerHTML += `
                <tr>
                    <td>${c.id}</td>
                    <td>${c.name}</td>
                    <td>${c.phone}</td>
                    <td>
                        <button onclick="editClient(${c.id}, '${c.name}', '${c.phone}')">Edit</button>
                        <button onclick="deleteClient(${c.id})">Delete</button>
                    </td>
                </tr>`;
            });
        });
}

function saveClient() {
    if (!name.value || !phone.value) {
        alert("Fill all fields");
        return;
    }

    const method = id.value ? "PUT" : "POST";
    const url = id.value ? `${API}/${id.value}` : API;

    fetch(url, {
        method: method,
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            name: name.value,
            phone: phone.value
        })
    }).then(() => {
        id.value = "";
        name.value = "";
        phone.value = "";
        loadClients();
    });
}

function editClient(i, n, p) {
    id.value = i;
    name.value = n;
    phone.value = p;
}

function deleteClient(i) {
    if (!confirm("Delete client?")) return;
    fetch(`${API}/${i}`, { method: "DELETE" })
        .then(() => loadClients());
}

loadClients();
