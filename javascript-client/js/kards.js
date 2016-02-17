// The root URL for the RESTful services
$("head").append($('<script type="text/javascript" src="js/properties.js"></script>'));
var KARD_URL = PREFIX_URL+"/kard";
var KARDS_DTO_URL = PREFIX_URL+"/karddto";

var startDate;
var endDate;
findAll();

// Register listeners
$('#btnSave').click(function () {
        addKard();
    return false;
});

$('#btnReset').click(function(){
    findAll();
});

$('#btnFilter').click(function () {
    filterDate($('#startDate').val(), $('#endDate').val());
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
        url: KARDS_DTO_URL,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}

function filterDate(startJsDate, endJsDate){
    console.log('filterDate');
    if(checkDate(startJsDate))
    {
        startDate="1/1/1970";
    }
    else
    {
        startDate=transformDate(startJsDate);
    }
    if(checkDate(endJsDate))
    {
        endDate=transformDate(new Date());
    }
    else
    {
        endDate=transformDate(endJsDate);
    }
    alert("Выбран промежуток с " + startDate + " до  " + endDate);
    $.ajax({
        type: 'GET',
        url: KARDS_DTO_URL+"/filter?startDate="+startDate+"&endDate="+endDate,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('Ошибка фильтра: ' + textStatus);
        }
    });
}

function checkDate(date)
{
    return ((date=="")||(date==null));
}
function transformDate(date)
{
    var newDate=new Date(date);
    var month=newDate.getMonth()+1;
    return newDate.getDate()+"/"+month + "/"+newDate.getFullYear();
}

function drawRow(kard) {
    var row = $("<tr />")
    $("#kardList").append(row);
    row.append($("<td>" + kard.login + "</td>"));
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
                alert('Карта удалена успешно.');
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
                alert('Ошибка операции: '+textStatus);
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
            alert('Карта создана успешно.');
            $('#kardId').val(data.kardId);
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Карта не создана!: ' + textStatus);
        }
    });
}

function formToJSON() {
    return JSON.stringify({
        "login": $('#login').val(),
        "kardName": $('#kardName').val(),
        "startDate": $('#startDate').val(),
        "endDate": $('#endDate').val()
    });
}