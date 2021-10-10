$(function () {
    $('#food-menu').addClass('active');
    $('#food-menu').siblings().removeClass('active');
});

$('#foodReportModal form #generate').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response, status, xhr) {
        hideProgress();
        $('#foodReportModal').modal('hide');
    });
});