function webEetacemon(idE) {
    window.location.href="Eetakemon.html?idE="+ idE + "&page=2";
}

$(document).ready(function () {
    checkLoged();

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "Relation/Captured/"+sessionStorage.getItem("ID"),
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode:{
            200: function (result) {
                console.log(result);
                $.each(result, function (i, obj) {
                    console.log((obj));
                    $(".tabla-eetakemon").append("<tr class=\"eetakemon\" onclick='webEetacemon("+obj.idEetakemon+")'>" +
                        "<td>" +
                        "<img src = \" /images/" + obj.name + ".png\" style=\"width:50px;height:50px;\" ' >" +
                        "</td>"+
                        "<td>" +obj.name+ "</td>"+
                        "<td>" +obj.level+ "</td>"+
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
