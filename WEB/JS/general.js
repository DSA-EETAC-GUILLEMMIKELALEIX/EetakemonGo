function hideAlert() {
    setTimeout(function() {
        $(".alert").slideToggle();
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

window.onload=function adminConfig(){
    if(sessionStorage.getItem("ID")==null)
    {
        sessionStorage.clear();
        window.location.replace("../index.html");
    }
    if(sessionStorage.getItem("Admin")!=1){
        /*$("#avanzado#).css({"display":"none"});*/
        loadMenu();
    }
    else{
        loadAdminMenu();
    }

    $("#logout").click(function(){
        sessionStorage.clear();
        window.location.replace("../index.html");
    });
};
