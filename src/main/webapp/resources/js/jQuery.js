function controlAjaxErrors() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function add(text) {
    $('#id').val(null);
    $('#modal-title').html(text);
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            fillTable();
            successNoty(messages['deleted']);
        }
    });
}

function updateTableByData(data) {
    datatable.clear().rows.add(data).draw();
}

var form = $('#detailsForm');

function save() {
    $.ajax({
        type: "POST",
        url: ajaxUrl,
        data: form.serialize(),
        success: function () {
            $('#editRow').modal('hide');
            fillTable();
            successNoty(messages['saved']);
        }
    });
}

function updateRow(id)
{
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("input[name='" + key + "']").val(value);
        });
    });
    $('#editRow').modal();
}

function fillTable() {
    $.get(ajaxUrl, updateTableByData);
}

function failNoty(event, jqXHR, options, jsExc) {
    closeNoty();
    failedNote = noty({
        text: messages['Failed'] + ': ' + jqXHR.statusText + '<br>' + $.parseJSON(jqXHR.responseText),
        type: 'error',
        layout: 'bottomRight',
        timeout: 3500
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    noty({
        text: messages['success'] + ' ' + text + '!',
        type: 'success',
        layout: 'bottomRight',
        timeout: 2000
    });
}

function editBtn(data, type, row)
{
    if (type == 'display') {
        return '<a class="btn btn-primary edit" onclick="updateRow(' + row.id + ')">' + messages['update'] + '</a>';
    }
}

function deleteBtn(data, type, row)
{
    if (type == 'display') {
        return '<a class="btn btn-danger delete" onclick="deleteRow(' + row.id + ')">' + messages['delete'] + '</a>';
    }
}