function hideAlert() {
    setTimeout(function() {
        $(".alert").slideToggle();
    },3000);
}

function setAlertMessage(message, type){
    $(".alert-message").append("<p class=\""+type+"\" id=\"writed-message\" \" >"+message+"</p>");
}

window.onload=function adminConfig(){
    if(sessionStorage.getItem("ID")==null)
    {
        sessionStorage.clear();
        window.location.replace("../index.html");
    }
    if(sessionStorage.getItem("Admin")!=1){
        /*$("#avanzado#).css({"display":"none"});*/
        $("#avanzado").hide();
    }
    else{
    }

    $("#logout").click(function(){
        sessionStorage.clear();
        window.location.replace("../index.html");
    });
};
