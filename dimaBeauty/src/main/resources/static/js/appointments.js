// LOGIN CHECK 
if (localStorage.getItem("loggedIn") !== "true") {
    window.location.href = "login.html";
}



const API = "http://localhost:8088/appointments";

const client = document.getElementById("client");
const service = document.getElementById("service");
const employee = document.getElementById("employee");
const date = document.getElementById("date");
const time = document.getElementById("time");
const table = document.getElementById("table");

/* LOAD DROPDOWNS */
function loadSelect(url, select, label) {
    fetch(url)
        .then(r => r.json())
        .then(data => {
            select.innerHTML = "";
            data.forEach(x => {
                select.innerHTML += `<option value="${x.id}">
                    ${x.name || label}
                </option>`;
            });
        });
}

function loadAppointments(){
    fetch(API)
        .then(r => r.json())
        .then(data => {
            table.innerHTML = "";
            data.forEach(a => {
                table.innerHTML += `
                <tr>
                    <td>${a.id}</td>
                    <td>${a.date}</td>
                    <td>${a.time}</td>
                    <td>${a.client.name}</td>
                    <td>${a.employee.name}</td>
                    <td>${a.serv.name}</td>
                    <td>
                        <button onclick="del(${a.id})">Delete</button>
                    </td>
                </tr>`;
            });
        });
}

function addAppointment(){
    fetch(`${API}/${client.value}/${service.value}/${employee.value}`, {
        method: "POST",
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({
            date: date.value,
            time: time.value
        })
    })
    .then(r => {
        if (!r.ok) return r.text().then(alert);
        loadAppointments();
    });
}

function del(id){
    fetch(`${API}/${id}`, { method: "DELETE" })
        .then(loadAppointments);
}

/* INIT */
loadSelect("http://localhost:8088/clients", client);
loadSelect("http://localhost:8088/services", service);
loadSelect("http://localhost:8088/employees", employee);

loadAppointments();
