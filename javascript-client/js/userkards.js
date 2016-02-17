// The root URL for the RESTful services
$("head").append($('<script type="text/javascript" src="js/properties.js"></script>'));
var KARD_URL = PREFIX_URL+"/kard";
var KARDS_DTO_URL = PREFIX_URL+"/karddto";
var USER_URL = PREFIX_URL + "/user"
var userId =getQueryVariable("userId");
findAll();

function getQueryVariable(variable)
{
    var query = window.location.search.substring(1);
    var vars = query.split("/");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if(pair[0] == variable){return pair[1];}
    }
    return(false);
}

// Register listeners
$('#btnSave').click(function () {
        addKard();
    return false;
});

function goToMain()
{
    window.location="index.html";
}


function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: KARDS_DTO_URL+"/"+userId,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });


}

function drawRow(kard) {
    var row = $("<tr />")
    $("#kardList").append(row);
    row.append($("<td>" + kard.login + "</a></td>"));
    row.append($("<td>" + kard.kardName +"</td>"));
    row.append($("<td>" + kard.balance +"</td>"));
    row.append($("<td>" + kard.createdDate +"</td>"));
    row.append($("<td>" + kard.updatedDate + "</td>"));
    row.append($("<td>" + '<button onclick="deleteKard('+ kard.kardId+')">Удалить</button>' + '<button onclick="operation('+ kard.kardId +')">Операция</button>' + "</td>"));
}

function renderList(data) {
    var dto = data.kards == null ? [] : (data.kards instanceof Array ? data.kards : [data.kards]);
    $('#kardList tr').remove();
    $.each(dto, function (index, kard) {
        drawRow(kard);
    });
}


function deleteKard(kardId) {

    if (confirm("Вы уверены, что хотите удалить карту?"))
    {
        console.log('deleteKard' + kardId);
        $.ajax({
            type: 'DELETE',
            url: KARD_URL + "/delete/"+ kardId,
            success: function (data, textStatus, jqXHR) {
                alert('Карта успешно удалена.');
                findAll();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('Ошибка удаления карты: ' + textStatus + kardId);
            }
        })
    }
}

function operation(userId)
{
    var operation = prompt("Введите сумму (добавить (положительное число), снять (отрицательно число))", '');
    if (operation != "") {
        $.ajax({
            type: 'POST',
            url: KARD_URL + "/operation/"+userId+"/"+operation,
            success: function (data, textStatus, jqXHR) {
                alert('Операция успешно проведена.');
                findAll();
            },
            error: function(jqXHR, textStatus, errorThrown) {
                console.log(jqXHR, textStatus, errorThrown);
                alert('Ошибка опирации: ' + textStatus);
            }

        })
    } else {
        alert('Операция не может быть пустой');
    }
}


function addKard() {
    console.log('addKard');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: KARD_URL,
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Карта успешно создана.');
            $('#kardId').val(data.kardId);
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Ошибка добавления карты: ' + textStatus);
        }
    });
}

function formToJSON() {
    return JSON.stringify({
        "userId": userId,
        "kardName": $('#kardName').val()
    });
}