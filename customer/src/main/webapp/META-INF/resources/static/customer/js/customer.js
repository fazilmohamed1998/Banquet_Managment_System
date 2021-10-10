$(function () {
    $('#customer-menu').addClass('active');
    $('#customer-menu').siblings().removeClass('active');
});
$('#addcustomer form #save').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response) {
        hideProgress();

        $('#addcustomer').modal('hide');
        //TODO: refresh page
    });
});


$('#editcustomer form #save').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response) {
        hideProgress();

        $('#editcustomer').modal('hide');
        //TODO: refresh page
    });
});

$('#editco_operativecustomer form #save').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response) {
        hideProgress();

        $('#editco_operativecustomer').modal('hide');
        //TODO: refresh page
    });
});


$('#customersearch form #save').on('click', function (e) {
    e.preventDefault();
    ajaxPost($(this).parents('form:first'), null, function () {
        showProgress();
    }, function (response) {
        hideProgress();

        $('#customersearch').modal('hide');
        //TODO: refresh page
    });
});

