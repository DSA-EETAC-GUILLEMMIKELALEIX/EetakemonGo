var ctxPath = "http://localhost:8081/EetakemonGo/";

function hideAlert(alert) {
    setTimeout(function() {
        $(alert).slideToggle();
    },2200);
}

function setAlertMessage(message, type){
    $(".alert-message").append("<p class=\""+type+"\" id=\"writed-message\" \" >"+message+"</p>");
}

function loadMenu(){
    $(".menu").load("../forms/Menu.html")
}

function loadAdminMenu(){
    $(".menu").load("../forms/AdminMenu.html")
}

function logout() {
    sessionStorage.clear();
    window.location.replace("../index.html");
}

function checkLoged(){
    if(sessionStorage.getItem("ID")==null)
    {
        sessionStorage.clear();
        window.location.replace("../index.html");
    }else {
        if (sessionStorage.getItem("Admin") != 1) {
            /*$("#avanzado#).css({"display":"none"});*/
            loadMenu();
        }
        else {
            loadAdminMenu();
        }
    }

    $("#logout").click(function(){
        sessionStorage.clear();
        window.location.replace("../index.html");
    });
}

function checkAdmin(){
    if (sessionStorage.getItem("Admin")!=1){
        alert("No tiene suficientes permisos para entrar aqui");
        window.location.replace("inicio.html");
    }
}
