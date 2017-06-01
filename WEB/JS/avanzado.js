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

function addEetakemon (){
    window.location.href="./Add-Eetakemon.html";
}

function deleteUser (id){
    $.ajax({
        type: "DELETE",
        url: ctxPath + "User/" + id,
        contentType: "application/json",
        dataType: "json",
        //data: JSON.stringify(o),
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode: {
            200: function () {
                alert("Usuario eliminado"); //alerta
                //location.reload();
                $(".usuario").remove();
                loadUserTable();
            },
            401: function () {
                alert("No autorizado");
                sessionStorage.clear();
                window.location.replace("../index.html");
            },
            403: function () {
                alert("No tiene permisos suficientes"); //alerta
            }
        }
    })
}

function deleteEetakemon (id){
    $.ajax({
        type: "DELETE",
        url: ctxPath + "Eetakemon/" + id,
        contentType: "application/json",
        dataType: "json",
        //data: JSON.stringify(o),
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode: {
            200: function () {
                alert("Eetakemon eliminado"); //alerta
                //location.reload();
                $(".eetakemon").remove();
                loadEetakemonTable();

            },
            202: function () {
                alert("No se ha podido eliminar el Eetakemon"); //alerta
            },
            401: function () {
                alert("No autorizado");
                sessionStorage.clear();
                window.location.replace("../index.html");
            },
            403: function () {
                alert("No tiene permisos suficientes"); //alerta
            }
        }
    })
}

if (sessionStorage.getItem("Admin")!=1){
    alert("No tiene suficientes permisos para entrar aqui");
    window.location.replace("inicio.html");
}

function loadUserTable() {
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
                        "<td class=\"edit\">" +"Editar"+ "</td>"+
                        "<td class=\"delete\" onclick='deleteUser("+obj.id+")'>" +"Borrar"+ "</td>"+
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
                $.each(result, function (i, obj) {
                    console.log((obj));
                    $("#tabla-eetakemon").append("<tr class=\"eetakemon\">" +
                        "<td>" +
                        "<img src = \" /images/" + obj.nombre + ".png\" style=\"width:50px;height:50px;\" ' >" +
                        "</td>"+
                        "<td>" +obj.id+ "</td>"+
                        "<td>" +obj.nombre+ "</td>"+
                        "<td>" +obj.tipo+ "</td>"+
                        "<td>" +obj.nivel+ "</td>"+
                        "<td class=\"edit\">" +"Editar"+ "</td>"+
                        "<td class=\"delete\" onclick='deleteEetakemon("+obj.id+")'>" +"Borrar"+ "</td>"+
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

}

$(document).ready(function(){
    $("#opcion-usuario").css({"background-color": "#636363","color":"white"});

    loadUserTable();
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
