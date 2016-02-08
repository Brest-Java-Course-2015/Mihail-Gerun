// The root URL for the RESTful services
$("head").append($('<script type="text/javascript" src="js/properties.js"></script>'));
var USER_URL = PREFIX_URL+"/user";
var USERS_DTO_URL = PREFIX_URL+"/userdto";
var KARDS_URL = PREFIX_URL+"/kards";
findAll();

// Register listeners
$('#btnSave').click(function () {
        addUser();
    return false;
});

function goToUserKards(userId)
{
    window.location="userkards.html?userId="+userId;
}


function goToKards()
{
    window.location="kards.html";
}

function findAll() {
    console.log('findAll');
    $.ajax({
        type: 'GET',
        url: USERS_DTO_URL,
        dataType: "json", // data type of response
        success: renderList,
        error: function(jqXHR, textStatus, errorThrown) {
            console.log(jqXHR, textStatus, errorThrown);
            alert('findAll: ' + textStatus);
        }
    });
}



function drawRow(user) {
    var row = $("<tr />")
    $("#userList").append(row);
    row.append($("<td>" + user.login + "</a></td>"));
    row.append($("<td>" + user.countKard +"</td>"));
    row.append($("<td>" + user.balance +"</td>"));
    row.append($("<td>" + user.createdDate + "</td>"));
    row.append($("<td>" + '<button onclick="deleteUser('+ user.userId +')">Удалить</button>' + '<button onclick="goToUserKards('+user.userId+')">Перейти</button>' + "</td>"));
}

function renderList(data) {
    var dto = data.users == null ? [] : (data.users instanceof Array ? data.users : [data.users]);
    $('#userList tr').remove();
    $.each(dto, function (index, user) {
        drawRow(user);
    });
}


function deleteUser(userId) {

    if (confirm("Вы уверены, что хотите удалить пользователя, удалятся так же все его карточки?"))
    {
        console.log('deleteUser' + userId);
        $.ajax({
            type: 'DELETE',
            url: KARDS_URL + "/"+ userId,
            success: function (data, textStatus, jqXHR) {
                $.ajax({
                    type: 'DELETE',
                    url: USER_URL + "/"+ userId,
                });
                alert('Пользователь успешно удалён!');
                findAll();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                alert('Ошибка удаления пользователя!: ' + textStatus + userId);
            }
        })

    }
}


function addUser() {
    console.log('addUser');
    $.ajax({
        type: 'POST',
        contentType: 'application/json',
        url: USER_URL,
        dataType: "json",
        data: formToJSON(),
        success: function (data, textStatus, jqXHR) {
            alert('Пользователь успешно создан');
            findAll();
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert('Ошибка создания пользователя!: ' + textStatus);
        }
    });
}

function formToJSON() {
    return JSON.stringify({
        "login": $('#login').val()
    });
}
