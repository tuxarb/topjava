function controlAjaxErrors() {
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(event, jqXHR, options, jsExc);
    });
}

function csrf_token_check() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function add(text) {
    $('#id').val(null);
    $('#detailsForm')[0].reset();
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

function updateRow(id) {
    $.get(ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            if (key == 'dateTime')
                value = value.toString().replace('T', ' ').substr(0, 16);
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
    var exceptionMessage = $.parseJSON(jqXHR.responseText);
    checkOnDuplicateEmailAndErrorsWithDate(exceptionMessage);
    failedNote = noty({
        text: messages['failed'] + ':<br>' + exceptionMessage.details.join('<br>'),
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

function editBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-primary edit" onclick="updateRow(' + row.id + ')">' + messages['update'] + '</a>';
    }
}

function deleteBtn(data, type, row) {
    if (type == 'display') {
        return '<a class="btn btn-danger delete" onclick="deleteRow(' + row.id + ')">' + messages['delete'] + '</a>';
    }
}

function dateTimePicker() {
    $.datetimepicker.setLocale(localeCode);

    $('.datepicker').datetimepicker({
        timepicker: false,
        format: 'Y-m-d'
    });

    $('.timepicker').datetimepicker({
        datepicker: false,
        format: 'H:i'
    });

    $('.datetimepicker').datetimepicker({
        format: 'Y-m-d H:i'
    });
}

function checkOnDuplicateEmailAndErrorsWithDate(exceptionMessage) {
    var cause = exceptionMessage.cause;
    var url = exceptionMessage.url;
    var details = exceptionMessage.details.toString();

    if (cause.includes("DataIntegrityViolationException") && url.includes("meals")) {
        exceptionMessage.details = [messages['meal.duplicatedDate']];
    }
    else if (cause.includes("DataIntegrityViolationException")) {
        exceptionMessage.details = [messages['user.duplicatedMail']];
    }

    if (details.includes("DateTimeParseException")) {
        exceptionMessage.details = [messages['dateIncorrect']];
    }
}
