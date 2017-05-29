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
    var ctxPath = "http://localhost:8081/EetakemonGo/";
    $("#btn1").click(function () {
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
            var o = {"nombre": name, "tipo": tipo, "nivel": nivel, "foto": foto};
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
                        }
                        alert("Eetakemon creado"); //alerta
                        window.location.replace("./Add-Eetakemon.html");
                    },
                    202: function () {
                        alert("Eetakemon ya existente"); //alerta
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
