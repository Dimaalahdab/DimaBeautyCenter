const API = "http://localhost:8088/services";

function loadServices(){
    fetch(API).then(r=>r.json()).then(data=>{
        table.innerHTML="";
        data.forEach(s=>{
            table.innerHTML+=`
            <tr>
                <td>${s.id}</td>
                <td>${s.name}</td>
                <td>${s.price}</td>
                <td>
                    <button onclick="edit(${s.id},'${s.name}',${s.price})">Edit</button>
                    <button onclick="del(${s.id})">Delete</button>
                </td>
            </tr>`;
        });
    });
}

function saveService(){
    let method = id.value ? "PUT" : "POST";
    let url = id.value ? API + "/" + id.value : API;

    fetch(url,{
        method:method,
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({
            name:name.value,
            price:price.value
        })
    }).then(()=>{
        id.value=""; name.value=""; price.value="";
        loadServices();
    });
}

function edit(i,n,p){
    id.value=i; name.value=n; price.value=p;
}

function del(i){
    fetch(API+"/"+i,{method:"DELETE"}).then(loadServices);
}

loadServices();
