var ctxPath = "http://localhost:8081/EetakemonGo/";

function loadUserTable() {
    //load users table
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "User/all",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode: {
            200: function (result) {
            console.log(result);
            },
            204: function () {
                alert("No se ha podido visualizar");
            },
            401: function () {
                alert("No autorizado");
                sessionStorage.clear();
                window.location.replace("../index.html");
            },
            403: function () {
                alert("No tiene suficientes permisos");
            }
        }
    });
}

function loadEetakemonTable(){
    //load eetakemon table
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "Eetakemon/all",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode:{
            200: function (result) {
                console.log(result);
               console.log(result.length);
            },
            204: function () {
                alert("No se han encontrado eetakemons");
            },
            401: function () {
                alert("No autorizado");
                sessionStorage.clear();
                window.location.replace("../index.html");
            }
        }
    });

}

$(document).ready(function(){
    checkLoged();
    loadEetakemonTable();

    //change user-eetakemon options
    $('#opcion-usuario').click(function(){
        $('#opcion-eetakemon').removeAttr('style');
        $(this).css({"background-color": "#636363","color":"white"});

        $('.opciones-eetakemon').hide();
        $('.opciones-usuario').show();
    });

    //change user-eetakemon options
    $("#opcion-eetakemon").click(function(){
        $('#opcion-usuario').removeAttr('style');
        $(this).css({"background-color": "#636363","color":"white"});

        $('.opciones-usuario').hide();
        $('.opciones-eetakemon').show();
    });
});
