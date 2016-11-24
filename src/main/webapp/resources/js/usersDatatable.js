var ajaxUrl = 'ajax/admin/users/';
var datatable;

csrf_token_check();
controlAjaxErrors();

function checkByAjax(checkbox, id) {
    var enabled = checkbox.is(":checked");
    $.post({
        url: ajaxUrl + id,
        data: {"enabled": enabled},
        success: function () {
            var row = checkbox.parent().parent();
            if (!enabled) {
                row.css('text-decoration', 'line-through').css('opacity', 0.5);
            }
            else {
                row.css('text-decoration', 'none').css('opacity', 1);
            }
            successNoty(enabled ? messages['user.enabled'] : messages['user.disabled']);
        }
    })
}

$(function () {
    datatable = $('#usersTable').DataTable({
        "ajax": {
            "url": ajaxUrl,
            "dataSrc": "" //перевод данных из одного формата в другой(аналог render)
        },
        "paging": false,
        "info": false,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email",
                "render": function (data, type) {
                    if (type == 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a></td>';
                    }
                    return data;
                }
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="checkByAjax($(this), ' + row.id + ')"/>';
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (data, type) {
                    if (type == 'display') {
                        return data.substring(0, 10);
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
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ],
        "createdRow": function (row, data) {
            $(row).css('font-weight', 'bold');
            if (!data.enabled) {
                $(row).css('text-decoration', 'line-through').css('opacity', 0.5);
            }
        },
        "language": {
            "search": messages['search'] + ":"
        }
    });
});
