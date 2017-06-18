function imagePreview() {
    var file = document.querySelector('input[type=file]').files[0];
    var reader = new FileReader();
    reader.onloadend = function () {
        document.getElementById('preview').src= reader.result;
    };
    if (file) {
        reader.readAsDataURL(file);
    } else {
        document.getElementById('preview').src = "";
    }
}

$(document).ready(function() {

    checkLoged();

    if (sessionStorage.getItem("Admin")!=1){
        alert("No tiene suficientes permisos para entrar aqui");
        window.location.replace("inicio.html");
    }
    $("#add-eetakemon").click(function () {
        var name = $("#nombre").val();
        var tipo = $("#tipo").val();
        var nivel = $("#nivel").val();
        var foto = $('#imagen').val();
        if(name==="" || tipo==="" || nivel===""|| foto===""){
            $(".add-form input").each(function () {
                if($(this).val()==="")
                    $(this).css({"border":"1.2px solid red"});
                else
                    $(this).css({"border":"none"});
            });
        }
        else {
            var file = document.querySelector('input[type="file"]').files[0];
            console.log(file);
            var reader = new FileReader();
            reader.readAsDataURL(file);
            console.log(reader);

            reader.onload = function() {
                var encodedImage = reader.result;
                var o = {"nombre": name, "tipo": tipo, "nivel": nivel, "foto": encodedImage};

                console.log(o);
                $.ajax({
                    type: "POST",
                    url: ctxPath + "Eetakemon",
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(o),
                    headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
                    statusCode: {
                        201: function () {
                            /*var file = document.querySelector('input[type="file"]').files[0];
                             console.log(file);
                             var reader = new FileReader();
                             reader.readAsDataURL(file);
                             console.log(reader);
                             reader.onload = function() {
                             var encodedImage = reader.result;
                             var o = {"nombre": name, "tipo": tipo, "nivel": nivel, "foto": encodedImage};
                             console.log(o);
                             $.ajax({
                             type: "POST",
                             url: ctxPath + "Eetakemon/Image",
                             contentType: "application/json",
                             data: JSON.stringify(o),
                             success: [function () {
                             }],
                             error: [
                             function (request, status) {
                             alert(status);
                             }
                             ]
                             });
                             }*/
                            $("#writed-message").remove();
                            setAlertMessage("Eetakemon creado","alert-success");
                            $(".alert").slideToggle();
                            hideAlert(".alert");
                            $("input").each(function () {
                                $(this).val("");
                            });
                        },
                        202: function () {
                            $("#writed-message").remove();
                            setAlertMessage("Eetakemon ya existente","alert-error");
                            $(".alert").slideToggle();
                            hideAlert(".alert");
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
                });
            }
        }
    });
    $("#add-user").click(function () {
        var name = $("#nombre").val();
        var email = $("#email").val();
        var password = $("#password").val();
        var admin = $('#admin').val();
        if(name==="" || email==="" || password===""){
            $(".add-form input").each(function () {
                if($(this).val()==="")
                    $(this).css({"border":"1.2px solid red"});
                else
                    $(this).css({"border":"none"});
            });
        }
        else {
            var o = {"nombre": name, "email": email, "contrasena": password, "admin": admin};
            console.log(o);
            $.ajax({
                type: "POST",
                url: ctxPath + "User/new",
                contentType: "application/json",
                dataType: "json",
                data: JSON.stringify(o),
                headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
                statusCode: {
                    201: function () {
                        $("#writed-message").remove();
                        setAlertMessage("Usuario creado","alert-success");
                        $(".alert").slideToggle();
                        hideAlert(".alert");
                        $("input").each(function () {
                            $(this).val("");
                        });
                    },
                    202: function () {
                        $("#writed-message").remove();
                        setAlertMessage("Usuario ya existente","alert-error");
                        $(".alert").slideToggle();
                        hideAlert(".alert");
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
            });
        }
    });
});
