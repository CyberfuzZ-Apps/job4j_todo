$(document).ready(takeList);

function takeList() {
    $.ajax({
        type: "GET",
        url: "/todo/item.do",
        dataType: "json",
        data: {
            show_all: $('#show_all').is(':checked')
        }
    }).done(function (data) {
        showAllItems(data);
    }).fail(function () {
        console.log('take list error');
    });
}

function showAllItems(data) {
    $('#table tbody > tr').html('');
    for (let i = 0; i < data.length; i++) {
        let id = data[i]['id'];
        let description = data[i]['description'];
        let created = data[i]['created'];
        let done = data[i]['done'];
        $('#table tr:last').after(
            '<tr>' +
            '<td>' + '<input type="checkbox" id="' + id + '" onchange="itemDone(id)">' + '</td>' +
            '<td>' + description + '</td>' +
            '<td>' + created + '</td>' +
            '</tr>');
        if (done) {
            document.getElementById(id).setAttribute('checked', 'true');
        }
    }
    console.log('TAKE LIST OK');
}

function itemDone(id) {
    console.log('CHECKED=' + id);
    $.ajax({
        type: "GET",
        url: "/todo/update.do",
        dataType: "json",
        data: {
            id : id
        }
    });
    setTimeout(takeList, 1000);
}

function addItem() {
    let description = $('#new_item').val();
    $.ajax({
        type: "POST",
        url: "/todo/item.do",
        dataType: "json",
        data: {
            description: description
        }
    });
    setTimeout(takeList, 1000);
}