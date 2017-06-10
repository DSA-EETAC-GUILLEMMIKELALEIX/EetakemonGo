function loadUser() {
    //load users table
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "User/all",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode: {
            200: function (result) {
                $("#num-users").append(result.length);
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

function loadCaptured(){
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "Relation/Captured/"+sessionStorage.getItem("ID"),
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode:{
            200: function (result) {
                $("#num-captured").append(result.length);
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

function loadEetakemon(){
    //load eetakemon table
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "Eetakemon/all",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode:{
            200: function (result) {
               $("#num-eetakemons").append(result.length);
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
    loadUser();
    loadCaptured();
    loadEetakemon();

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
