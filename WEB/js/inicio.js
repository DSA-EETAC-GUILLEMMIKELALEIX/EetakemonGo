function loadInfo() {
    $.ajax({
        type: 'GET',
        contentType: 'application/json',
        url: ctxPath + "User/info",
        headers: {"Authorization": "Bearer " + sessionStorage.getItem("Token")},
        statusCode: {
            200: function (result) {
                var info=result.responseText.split("/");
                console.log(info);
                $("#num-users").append(info[0]);
                $("#num-eetakemons").append(info[1]);
                $("#num-captured").append(info[2]);
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
    checkLoged();
    loadInfo();

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

    console.log(sessionStorage.getItem("Token"))
});
