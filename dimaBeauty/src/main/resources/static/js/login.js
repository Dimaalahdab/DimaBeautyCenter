
const username = document.getElementById("username");
const password = document.getElementById("password");
const msg = document.getElementById("msg");

//LOGIN FUNCTION 
function login() {
    fetch("http://localhost:8088/auth/login", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
            username: username.value,
            password: password.value
        })
    })
    .then(res => res.text())
    .then(result => {
        if (result === "LOGIN_SUCCESS") {
            localStorage.setItem("loggedIn", "true");
            window.location.href = "index.html";
        } else {
            msg.innerText = "Invalid username or password";
        }
    });
}


function logout() {
    localStorage.removeItem("loggedIn");
    window.location.href = "login.html";
}

