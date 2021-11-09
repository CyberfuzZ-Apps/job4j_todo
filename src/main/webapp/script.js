$(document).ready(takeList);

getUserName();

getCategories();

function getCategories() {
    $.ajax({
        type: "GET",
        url: "/todo/category.do",
        dataType: "json"
    }).done(function (categories) {
        let catHtml = '';
        for (let i = 0; i < categories.length; i++) {
            let cat = categories[i]['name'];
            let catId = categories[i]['id'];
            catHtml += '<option value="' + catId + '">' + cat + '</option>';
        }
        $('#cIds').html(catHtml);
    }).fail(function () {
        console.log("ERROR LOAD CATEGORIES")
    });
}

function getUserName() {
    $.ajax({
        type: "POST",
        url: "/todo/index.do",
        dataType: "json"
    }).done(function (user) {
        document.getElementById('username').innerHTML = user.name;
    }).fail(function () {
        console.log('ERROR LOAD USERNAME')
    });
}

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
        let author = data[i]['user'].name;
        let categories = data[i]['categoryList'];
        let cats = '';
        for (let j = 0; j < categories.length; j++) {
            cats += categories[j]['name'] + '\r\n';
        }
        $('#table tr:last').after(
            '<tr>' +
            '<td>' + '<input type="checkbox" id="' + id + '" onchange="itemDone(id)">' + '</td>' +
            '<td>' + description + '</td>' +
            '<td>' + created + '</td>' +
            '<td>' + author + '</td>' +
            '<td>' + cats + '</td>' +
            '</tr>');
        if (done) {
            document.getElementById(id).setAttribute(
                'checked', 'true');
            document.getElementById(id).setAttribute(
                'disabled', 'true');
        }
    }
}

function itemDone(id) {
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
    let cats = $('#cIds').val();
    let categories = '';
    for (let i = 0; i < cats.length; i++) {
        categories += cats[i] + ',';
    }
    $.ajax({
        type: "POST",
        url: "/todo/item.do",
        dataType: "json",
        data: {
            description: description,
            categories: categories
        }
    }).done(function () {
        setTimeout(takeList, 1000);
    }).fail(function () {
        console.log('ERROR ADD ITEM');
    });

}