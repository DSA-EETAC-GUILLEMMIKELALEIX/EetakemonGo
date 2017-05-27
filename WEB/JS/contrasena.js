var ctxPath = "http://localhost:8081/EetakemonGo/";

$(document).ready(function(){
    $("#btn1").click(function (){
        var email= $("#email").val();
        if(email===""){
            $("#email").css({"border":"1.2px solid red"});
        }
        else {
            var o = {"email": email};
            console.log(o);
            $.ajax({
                type: "POST",
                url: ctxPath + "User/Password",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(o),
                statusCode:{
                    200: function(){
                        alert("Abre el correo para obtener su contraseña!"); //alerta
                        window.location.href="../index.html";
                    },
                    500: function(){
                        alert("Error al recuperar contraseña: error en el servidor"); //alerta
                    }
                }
            })
        }
    });
});
