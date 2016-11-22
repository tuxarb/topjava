var ajaxUrl = 'ajax/admin/users/';
var datatable;

csrf_token_check();
controlAjaxErrors();

function check(checkbox, id) {
    var enabled = checkbox.is(":checked");
    $.post({
        url: ajaxUrl + id,
        data: {"enabled": enabled},
        success: function () {
            checkbox.parent().parent().css('text-decoration', enabled ? 'none' : 'line-through');
            successNoty(enabled ? messages['user.enabled'] : messages['user.disabled']);
        }
    })
}

$(function () {
    datatable = $('#usersTable').DataTable({
        "ajax" : {
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
                "render": function(data, type){
                    if (type == 'display'){
                        return '<a href="mailto:'+data+'">'+data+'</a></td>';
                    }
                }
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type == 'display') {
                        return '<input type="checkbox" ' + (data ? 'checked' : '') + ' onclick="check($(this), '+ row.id + ')"/>';
                    }
                }
            },
            {
                "data": "registered",
                "render": function (data, type) {
                    if (type == 'display'){
                        return data.substring(0, 10);
                    }
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
        "language": {
            "search": messages['search'] + ":"
        }
    });
});
