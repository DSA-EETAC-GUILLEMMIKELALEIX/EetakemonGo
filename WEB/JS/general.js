if(sessionStorage.getItem("ID")==null)
{
    sessionStorage.clear();
    window.location.replace("../index.html");
}

window.onload=function adminConfig(){
    var avanzado = document.getElementById("avanzado");
    if(sessionStorage.getItem("Admin")!=1){
        $(avanzado).css({"display":"none"});
    }
    else{
    }
};
