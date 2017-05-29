var ctxPath = "http://localhost:8081/EetakemonGo/";

function webEetacemon(idE) {
    window.location.href="Eetakemon.html?idE="+ idE + "&page=1";
}

$(document).ready(function () {


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
                    $(".tabla-eetakemon").append("<tr class=\"eetakemon\" onclick='webEetacemon("+obj.id+")'>" +
                        "<td>" +
                        "<img src = \" /images/" + obj.nombre + ".png\" style=\"width:50px;height:50px;\" ' >" +
                        "</td>"+
                        "<td>" +obj.nombre+ "</td>"+
                        "<td>" +obj.tipo+ "</td>"+
                        "<td>" +obj.nivel+ "</td>"+
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

});
