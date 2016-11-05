function controlAjaxErrors() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function add() {
    $('#id').val(null);
    $('#editRow').modal();
}

function deleteRow(id) {
    $.ajax({
        url: ajaxUrl + id,
        type: 'DELETE',
        success: function () {
            fillTable();
            successNoty('deleted');
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
            successNoty('saved');
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
        text: 'Failed: ' + jqXHR.statusText,
        type: 'error',
        layout: 'bottomRight'
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
        text: 'Successfully ' + text + '!',
        type: 'success',
        layout: 'bottomRight',
        timeout: 2000
    });
}