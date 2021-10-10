var typeIcons = [];
typeIcons['info'] = 'icon far fa-info-circle';
typeIcons['success'] = 'icon far fa-check';
typeIcons['warning'] = 'icon far fa-exclamation-triangle';
typeIcons['danger'] = 'icon far fa-ban';

function showNotification(type, msg) {
    $.notify({
        icon: typeIcons[type],
        message: msg

    }, {
        type: type,
        timer: 5000,
        placement: {
            from: 'top',
            align: 'right'
        }
    });
}

function showProgress() {
    $('#progress').modal({
        backdrop: 'static',
        keyboard: false
    }).modal('show');
    $('body').css('overflow-y', 'hidden');
}

function hideProgress(e) {
    setTimeout(function () {
        $('#progress').modal('hide');
    }, 465);
}

function showConfirm(title, text, callbackFn, data) {
    $('#confirm .modal-title').html(title);
    $('#confirm .modal-text').html(text);

    $('#confirm button[type="submit"]').on('click', function (e) {
        e.preventDefault();
        callbackFn(data);
    });

    $('#confirm').modal({
        backdrop: 'static',
        keyboard: false,
    }).on('hide.bs.modal', function (e) {
        $('#confirm button[type="submit"]').unbind("click");
    }).modal('show');
}

function hideConfirm() {
    $('#confirm').modal('hide');
}

function ajaxGet(url, enableCache, async, /*onSubmit,*/ success) {
    $.ajax({
        type: "GET",
        url: url,
        cache: enableCache,
        async: async,/*
        beforeSend: onSubmit(),*/
        success: function (response, status, xhr) {
            commonResponseHandler(response, status, xhr, success);
        },
        error: commonErrorHandler
    });
}

function ajaxDelete(url, onSubmit, success) {
    $.ajax({
        type: "DELETE",
        url: url,
        beforeSend: function () {
            showProgress();
            if (onSubmit) {
                onSubmit();
            }
        },
        success: function (response, status, xhr) {
            commonResponseHandler(response, status, xhr, success);
        },
        error: commonErrorHandler
    });
}

function ajaxPost($form, formData, onSubmit, success) {
    var postData;
    var url = $form.attr('action');
    var type = 'POST';
    if ($form.attr('enctype') == "multipart/form-data") {
        postData = formData ? formData : new FormData($form[0]);
        $.ajax({
            url: url,
            type: type,
            data: postData,
            beforeSend: onSubmit,
            success: function (response, status, xhr) {
                commonResponseHandler(response, status, xhr, success);
            },
            cache: false,
            contentType: false,
            processData: false,
            error: commonErrorHandler
        });
    } else {
        postData = formData ? formData : $form.serializeArray();
        $.ajax({
            url: url,
            type: type,
            data: postData,
            beforeSend: onSubmit,
            processData: false,
            contentType: false,
            success: function (response, status, xhr) {
                commonResponseHandler(response, status, xhr, success);
            },
            error: commonErrorHandler
        });
    }
}

function commonErrorHandler(jqXHR, textStatus, errorThrown) {
    showNotification('danger', "Problem with server call.<br>Please try again.<br>Technical" +
        " details: " + jqXHR.status + ':' + jqXHR.statusText);
    hideProgress();
}

function commonResponseHandler(response, status, xhr, callback) {
    //response = $.parseJSON(response);
    if (response && 'hasError' in response) {
        if (response.hasError) {
            hideProgress();
            showNotification('warning', response.errors.join("\n"));
            return;
        }
    }

    if (callback) {
        callback(response, status, xhr);
    }
}

$(window).on('load', function () {
    $.ajaxSetup({
        statusCode: {
            401: function () {
                location.reload();
            }
        }
    });
});

function getExtension(filename) {
    var parts = filename.split('.');
    return parts[parts.length - 1];
}

function isImage(filename) {
    var imgExts = ['gif', 'jpeg', 'jpg', 'png', 'webp', 'svg+xml', 'x-icon'];
    return imgExts.includes(getExtension(filename).toLowerCase());
}

function isVideo(filename) {
    var vdoExts = ['ogg', 'webm', 'mp4', 'wmv', 'avi', 'mov', 'm4v', 'm4a', 'm4p', 'm4b', 'm4r', 'm1v', '3gp'];
    return vdoExts.includes(getExtension(filename).toLowerCase());
}

function hashCode(str) {
    var hash = 0;
    if (str.length == 0) return hash;
    for (i = 0; i < str.length; i++) {
        char = str.charCodeAt(i);
        hash = ((hash << 5) - hash) + char;
        hash = hash & hash; // Convert to 32bit integer
    }
    return hash;
}

function formatCurrency(val) {
    return new Number(val).toFixed(2).toString().replace(/(\d)(?=(\d\d\d)+([^\d]|$))/g, '$1,');
}
