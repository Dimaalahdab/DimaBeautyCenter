//  LOGIN CHECK 
if (localStorage.getItem("loggedIn") !== "true") {
    window.location.href = "login.html";
}

const API = "http://localhost:8088/services";

const id = document.getElementById("id");
const name = document.getElementById("name");
const price = document.getElementById("price");
const table = document.getElementById("table");

function loadServices(){
    fetch(API)
        .then(r => r.json())
        .then(data => {
            table.innerHTML = "";
            data.forEach(s => {
                table.innerHTML += `
                <tr>
                    <td>${s.id}</td>
                    <td>${s.name}</td>
                    <td>${s.price}</td>
                    <td>
                        <button onclick="edit(${s.id}, '${s.name}', ${s.price})">Edit</button>
                        <button onclick="del(${s.id})">Delete</button>
                    </td>
                </tr>`;
            });
        });
}

function saveService(){
    if (!name.value || !price.value) {
        alert("Fill all fields");
        return;
    }

    const method = id.value ? "PUT" : "POST";
    const url = id.value ? `${API}/${id.value}` : API;

    fetch(url,{
        method: method,
        headers: {"Content-Type":"application/json"},
        body: JSON.stringify({
            name: name.value,
            price: price.value
        })
    }).then(() => {
        id.value = "";
        name.value = "";
        price.value = "";
        loadServices();
    });
}

function edit(i,n,p){
    id.value = i;
    name.value = n;
    price.value = p;
}

function del(i){
    if (!confirm("Delete service?")) return;
    fetch(`${API}/${i}`, { method:"DELETE" })
        .then(() => loadServices());
}

loadServices();
