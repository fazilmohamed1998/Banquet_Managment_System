$(function () {
    $('#user-menu').addClass('active');
    $('#user-menu').siblings().removeClass('active');
});

$('#editUserModal').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    if (id) {
        ajaxGet('/app/user/' + $invoker.data('id'), false, false, function (response) {
            console.log('response - ', response);
            $('#id').val(response.result.id);
            $('#name').val(response.result.name);
            $('#nic').val(response.result.nic);
            $('#epfNo').val(response.result.epfNo);
            $('#email').val(response.result.email);
            $('#contactNo').val(response.result.contactNo);
            $('#address').val(response.result.address);
            $('#userName').val(response.result.userName);
            $('#role').selectpicker('val', response.result.userRole.id);
            $('#userStatus').selectpicker('val', response.result.status.id);
        });
    }
}).on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    $('#cancel').trigger('click');
});

$('#editUserModal form #save').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        ajaxPost($form, null, function () {
            showProgress();
        }, function (responce) {
            hideProgress();
            $('#editUserModal').modal('hide');
            location.reload();
        });
    }
});

$('#deleteUser').on('show.bs.modal', function (e) {
    var $invoker = $(e.relatedTarget);
    var id = $invoker.data('id');
    $('#delId').val(id);
});

$('#deleteUser #delete').on('click', function (e) {
    e.preventDefault();
    ajaxDelete("/app/user/" + $('#delId').val(), function () {
        showProgress();
    }, function (response) {
        setTimeout(function () {
            hideProgress();
        }, 35);
        $('#deletefoodmodal').modal('hide');
        location.reload();
    });
});

$('#reportModal form #generate').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    var queryStr = $form.serialize();
    printJS($form.attr('action') + "?" + queryStr);
});
