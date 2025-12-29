const API = "http://localhost:8088/appointments";

function load(){
    fetch(API).then(r=>r.json()).then(data=>{
        table.innerHTML="";
        data.forEach(a=>{
            table.innerHTML+=`
            <tr>
                <td>${a.id}</td>
                <td>${a.date}</td>
                <td>${a.time}</td>
                <td>${a.client.name}</td>
                <td>${a.employee.name}</td>
                <td>
                    <button onclick="del(${a.id})">Delete</button>
                </td>
            </tr>`;
        });
    });
}

function addAppointment(){
    fetch(`${API}/${clientId.value}/${serviceId.value}/${employeeId.value}`,{
        method:"POST",
        headers:{"Content-Type":"application/json"},
        body:JSON.stringify({date:date.value,time:time.value})
    }).then(load);
}

function del(id){
    fetch(API+"/"+id,{method:"DELETE"}).then(load);
}

load();