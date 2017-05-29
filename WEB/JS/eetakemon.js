function getUrlParameter(name) {
    name = name.replace(/[\[]/, '\\[').replace(/[\]]/, '\\]');
    var regex = new RegExp('[\\?&]' + name + '=([^&#]*)');
    var results = regex.exec(location.search);
    return results === null ? '' : decodeURIComponent(results[1].replace(/\+/g, ' '));
};



window.onload = function () {
    var ctxPath = "http://localhost:8081/EetakemonGo/";
    var urlParams = new URLSearchParams(window.location.search);
    var page = getUrlParameter('page');
    var idE = getUrlParameter('idE');

    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "Eetakemon/"+idE,
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode:{
            200: function (result) {
                $("#image-div").append(
                    "<img id=\"imagen\" src = \" /images/" + result.nombre + ".png\" style=\"width:20%;height:20%;\" '>"
                );

                $("#tabla-info").append("<tr class=\"eetakemon\">" +
                    "<td>" +result.nombre+ "</td>"+
                    "<td>" +result.tipo+ "</td>"+
                    "<td>" +result.nivel+ "</td>"+
                    "</tr>");
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
