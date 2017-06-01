var ctxPath = "http://localhost:8081/EetakemonGo/";

function saveToken(t) {
    var TOKEN=t;
    sessionStorage.setItem("Token",TOKEN);
    console.log(sessionStorage.getItem("Token"));
}

function decodeToken(t){
    var token = t // jwt token';
    var decoded = jwt_decode(token);
    return decoded;
}

function saveAdminID(decoded){
    var admin=decoded.Admin;
    var id=decoded.sub;
    sessionStorage.setItem("ID",id);
    sessionStorage.setItem("Admin",admin);
    console.log(sessionStorage);
}

$(document).ready(function(){
    $('.registrarse, .entrar').click(function(){;
        $('.login-form, .register-form').slideToggle();//animate({height: "toggle", opacity: "toggle"});
        //$('.login-form, .register-form').toggle();
    });


    $("#login-btn").click(function (){
        $(this).prop('disabled', true);
        var email= $("#email1").val();
        var password= $("#pass1").val();
        if(email==="" || password===""){
            $(".login-form input").each(function () {
               if($(this).val()==="")
                   $(this).css({"border":"1.2px solid red"});
               else
                   $(this).css({"border":"none"});
            });
            $(this).prop('disabled', false);
        }
        else {
            var o = {"email": email, "contrasena": password};
            $.ajax({
                type: "POST",
                url: ctxPath + "User/Login",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(o),
                headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
                statusCode:{
                    200: function(result){
                        saveToken(result.responseText);
                        var decoded=decodeToken(result.responseText);
                        saveAdminID(decoded);
                        window.location.replace("./forms/Inicio.html");
                    },
                    401: function(){
                        alert("Error al loguearse"); //alerta

                    }
                },
                complete: function (result) {
                    $("#login-btn").prop('disabled', false);
                }
            })
        }

    });

    $("#register-btn").click(function (){
        $("#register-btn").prop('disabled', true);
        var name= $("#name").val();
        var password= $("#pass2").val();
        var email= $("#email2").val();
        if (name==="" || password==="" || email===""){
            $(".register-form input").each(function () {
                if($(this).val()==="")
                    $(this).css({"border":"1.2px solid red"});
                else
                    $(this).css({"border":"none"});
            });
            $(this).prop('disabled', false);
        }else {
            var o = {"nombre": name, "contrasena": password, "email": email};
            console.log(o);
            $.ajax({
                type: "POST",
                url: ctxPath + "User/Register",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(o),
                statusCode:{
                    201: function(result){
                        //window.location.href= "../index.html";
                        $(".register-form input").val("")
                        $('.login-form, .register-form').toggle();
                        $('.alert').slideToggle();
                    },
                    202: function(){
                        alert("Usuario ya utilizado");//alerta
                    },
                    400: function(){
                        alert("Error al registrarse: petición errónea");//alerta
                    },
                },
                complete: function (result) {
                    $("#register-btn").prop('disabled', false);
                }
            })
        }

    });
});