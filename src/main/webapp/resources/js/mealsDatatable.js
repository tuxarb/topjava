var ajaxUrl = 'ajax/profile/meals/';

csrf_token_check();
controlAjaxErrors();

function updateTableByFilter() {
    $.ajax({
        type: "POST",
        url: ajaxUrl + 'filter',
        data: $('#filterForm').serialize(),
        success: function (data) {
            updateTableByData(data);
            $('#filterForm')[0].reset();
            successNoty(messages['meal.filtered'])
        }
    });
}

var datatable;


$(function () {
    datatable = $('#mealsTable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": false,
        "columns": [
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "data": "dateTime",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return data.toString().replace('T', ' ').substring(0, 16);
                    }
                    return data;
                }
            },
            {
                "defaultContent": "",
                "render": editBtn
            },
            {
                "defaultContent": "",
                "render": deleteBtn
            }],
        "order": [
            [
                2,
                "desc"
            ]
        ],
        "createdRow": function (row, data) {
            $(row).css('font-weight', 'bold');
            data.exceed ? $(row).css('background-color', '#ff4f5a').css('color', 'black') : $(row).css('color', 'green')
        },
        "language": {
            "search": messages['search'] + ':'
        }
    });
});