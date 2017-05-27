var ctxPath = "http://localhost:8081/EetakemonGo/";

$(document).ready(function(){
    $("#opcion-usuario").css({"background-color": "#636363","color":"white"});

    //load users table
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "User/all",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode: {
            200: function (result) {

                $.each(result, function (i, obj) {
                    console.log((obj));
                    $("#tabla-usuarios").append("<tr class=\"usuario\" id=\"u\">" +
                        "<td>" + obj.id + "</td>" +
                        "<td>" + obj.nombre + "</td>" +
                        "<td>" + obj.email + "</td>" +
                        "<td>" + obj.contrasena + "</td>" +
                        "<td>" + obj.admin + "</td>" +
                        "</tr>");
                });
                for(i=0;i<1000;i++){
                    document.getElementById("texto").value += "\nID: " + result[i].id + "\nNombre:" + result[i].nombre +
                        "\nEmail:" + result[i].email;
                }
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
    })

    //change user-eetakemon options
    $('#opcion-usuario, #opcion-eetakemon').click(function(){
        $('#opcion-usuario, #opcion-eetakemon').removeAttr('style');
        $(this).css({"background-color": "#636363","color":"white"});

        $('.opciones-usuario, .opciones-eetakemon').toggle();
    });
});
