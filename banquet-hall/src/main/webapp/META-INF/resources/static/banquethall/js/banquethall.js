$(function () {
    $('#banquethall-menu').addClass('active');
    $('#banquethall-menu').siblings().removeClass('active');
});

$('#editBanquetHallModal form #save').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response) {
        hideProgress();

        $('#editBanquetHallModal').modal('hide');
        location.reload();
    });
});


$('#reportModal form #generate').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response, status, xhr) {
        hideProgress();
        $('#reportModal').modal('hide');
    });
});

$('#reportModal form #generate').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    var queryStr = $form.serialize();
    printJS($form.attr('action') + "?" + queryStr);
});
