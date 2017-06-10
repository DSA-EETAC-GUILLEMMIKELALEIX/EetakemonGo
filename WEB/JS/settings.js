$(document).ready(function(){
    checkLoged();

    $("#btn1").click(function () {
        var name = $("#name").val();
        var password = $("#password").val();
        /*if (name==="" || password===""){
            alert("Rellena todos los campos");
        }else {*/
            var o = {"nombre": name, "contrasena": password};
            $.ajax({
                type: "POST",
                url: ctxPath + "User/" + sessionStorage.getItem("ID"),
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(o),
                headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
                statusCode: {
                    200: function () {
                        alert("Usuario modificado"); //alerta
                    },
                    401: function () {
                        alert("No autorizado");
                        sessionStorage.clear();
                        window.location.replace("../index.html");
                    }
                    ,
                    403: function () {
                        alert("No tiene permisos suficientes"); //alerta
                    },
                    500: function () {
                        alert("No modificado: error en el servidor"); //alerta
                    }
                }
            })
        //}
    });
});