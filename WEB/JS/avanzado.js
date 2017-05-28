var ctxPath = "http://localhost:8081/EetakemonGo/";

function admin (admin){
    var a;
    if(admin===1){
        a="Si";
    }else{
        a="No";
    }
    return a;
}

if (sessionStorage.getItem("Admin")!=1){
    alert("No tiene suficientes permisos para entrar aqui");
    window.location.replace("inicio.html");
}

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
                        "<td>" + admin(obj.admin) + "</td>" +
                        "</tr>");
                });
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

    //load eetakemon table
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "Eetakemon/all",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode:{
            200: function (result) {
                console.log(result);
                $.each(result, function (i, obj) {
                    console.log((obj));
                    $("#tabla-eetakemon").append("<tr class=\"eetakemon\">" +
                        "<td>" +
                        "<img src = \" /images/" + obj.nombre + ".png\" style=\"width:50px;height:50px;\" ' >" +
                        "<td>" +obj.id+ "</td>"+
                        "<td>" +obj.nombre+ "</td>"+
                        "<td>" +obj.tipo+ "</td>"+
                        "<td>" +obj.nivel+ "</td>"+
                        "<td class=\"edit\">" +"Editar"+ "</td>"+
                        "<td class=\"delete\">" +"Borrar"+ "</td>"+
                        "</tr>");
                });
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
