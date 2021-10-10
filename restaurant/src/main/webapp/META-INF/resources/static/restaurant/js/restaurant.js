$(function () {
    $('#restaurant-menu').addClass('active');
    $('#restaurant-menu').siblings().removeClass('active');
});

$('#editTableModal form #save').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response) {
        hideProgress();
        $('#editTableModal').modal('hide');
        //TODO: refresh page
    });
});