var $table = $('#reservation-tbl');
$(function () {
    $('#reservation-menu').addClass('active');
    $('#reservation-menu').siblings().removeClass('active');

    $('#from, #To').datetimepicker({
        icons: {
            previous: "far fa-chevron-left",
            next: "far fa-chevron-right"
        },
        defaultDate: null,
        maxDate: new Date(),
        format: "DD-MMM-YYYY"
    }).on('dp.change', function (e) {
        var $form = $(this).parents('form:first');
        if ($form) {
            $form.parsley().validate()
        } else {
            $(this).parsley().isValid();
        }
    });
    $('#from, #To').val("");

    $('#from1, #To1').datetimepicker({
        icons: {
            previous: "far fa-chevron-left",
            next: "far fa-chevron-right"
        },
        defaultDate: null,
        maxDate: new Date(),
        format: "DD-MMM-YYYY"
    }).on('dp.change', function (e) {
        var $form = $(this).parents('form:first');
        if ($form) {
            $form.parsley().validate()
        } else {
            $(this).parsley().isValid();
        }
    });
    $('#from1, #To1').val("");

    $('#from3, #To2').datetimepicker({
        icons: {
            previous: "far fa-chevron-left",
            next: "far fa-chevron-right"
        },
        defaultDate: null,
        maxDate: new Date(),
        format: "DD-MMM-YYYY"
    }).on('dp.change', function (e) {
        var $form = $(this).parents('form:first');
        if ($form) {
            $form.parsley().validate()
        } else {
            $(this).parsley().isValid();
        }
    });
    $('#from3, #To2').val("");

    initTable();
});

function actionFormatter(value, row, index) {
    var action = '<button class="btn btn-link link-info edit" data-toggle="modal" data-target="#modifyReservationModal" '
        + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + actionEdit + '">'
        + '<i class="far fa-edit"></i></button>';

    action += '<button class="btn btn-link link-danger refund" data-toggle="modal" data-target="#cancelReservationModal" '
        + 'data-backdrop="static" data-toggle="tooltip" data-id="' + row.id + '" title="' + actionCancel + '">'
        + '<i class="fa fa-times"></i></button>';
    return action;
}


function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: '550',
        columns: [{
            title: colId,
            field: 'id',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colReservatioNo,
            field: 'reservationNo',
            align: 'center',
            valign: 'middle'
        }, {
            title: colCust,
            field: 'customer',
            align: 'center',
            valign: 'middle'
        }, {
            title: colEvent,
            field: 'event',
            align: 'center',
            valign: 'middle',
        }, {
            title: colType,
            field: 'type',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colResOn,
            field: 'reservedOn',
            align: 'center',
            valign: 'middle',
        }, {
            title: colResBy,
            field: 'reservedBy',
            align: 'center',
            valign: 'middle',
        }, {
            title: colStatus,
            field: 'status',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colActions,
            align: 'center',
            events: window.actionEvents,
            formatter: actionFormatter
        }],
        classes: 'table'
    });
}

$('#makeReservationModal').on('show.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    ajaxGet('/app/reservation/reference', false, false, function (response) {
        $('#reference').val(response.result);
        $('#refMkReservation').html(response.result);
    });
}).on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    $('#id').val('');
    $('#customer').selectpicker('val', $("#customer option:first").val());
    $('#event').selectpicker('val', $("#event option:first").val());
    $('#location').selectpicker('val', $("#location option:first").val());
    $('#from').selectpicker('val', $("#from option:first").val());
    $('#to').selectpicker('val', $("#to option:first").val());
    //$('#type').selectpicker('val', $("#type option:first").val());
    $('#description').val('');
});

$('#makeReservationModal form #proceed').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        var formData = new FormData($($form).get(0));
        ajaxPost($form, formData, function () {
            showProgress();
        }, function (response) {
            setTimeout(function () {
                hideProgress();
            }, 100);
            $('#makeReservationModal').modal('hide');
            showNotification('success', lblReservationSuccess);
            $table.bootstrapTable('refresh');
        });
    }
});

$('#reservationReportModal').on('hidden.bs.modal', function (e) {
    $(this).find('form:first').parsley().reset();
    $('#from').val('');
    $('#to').val('');
});

$('#reservationReportModal form #generate').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        var formData = new FormData($($form).get(0));
        ajaxPost($form, formData, function () {
            showProgress();
        }, function (response) {
            setTimeout(function () {
                hideProgress();
            }, 100);
            $('#reservationReportModal').modal('hide');
            showNotification('success', lblReservationReportSuccess);
            $table.bootstrapTable('refresh');
        });
    }
});

    $('#modifyReservationModal').on('show.bs.modal', function (e) {
        var $invoker = $(e.relatedTarget);
        ajaxGet('/app/reservation/' + $invoker.data('id'), false, false, function (response) {
            $('#id').val(response.result.id);
            $('#customer').html(response.result.customer.name);
            $('#event').html(response.result.event.eventType.type);
            $('#type').html(response.result.type.type);
            $('#reservedOn').html(response.result.reservedOn);
            $('#from').html(response.from.from);
            $('#to').html(response.to.to);
            $('#status').selectpicker('val', response.result.status.id);
        });
    }).on('hidden.bs.modal', function (e) {
        $(this).find('form:first').parsley().reset();
    });

    $('#modifyReservationModal form #modify').on('click', function (e) {
        e.preventDefault();
        var $form = $(this).parents('form:first');
        if ($form.parsley().validate()) {
            var formData = new FormData($($form).get(0));
            ajaxPost($form, formData, function () {
                showProgress();
            }, function (response) {
                hideProgress();

                $('#modifyReservationModal').modal('hide');
                showNotification('success', lblReservationUpdateSuccess);
                $table.bootstrapTable('refresh');
            });
        }
    });

    $('#cancelReservationModal').on('show.bs.modal', function (e) {
        var $invoker = $(e.relatedTarget);
        var id = $invoker.data('id');
        var selRow = $table.bootstrapTable('getRowByUniqueId', id);
        var confMsg = $(this).find('.modal-body').html();
        confMsg = confMsg.replace('{0}', selRow.event + '( ' + selRow.reservationNo + ' )');
        $(this).find('.modal-body').html(confMsg);
        $('#idCancel').val(id);
    }).on('hidden.bs.modal', function (e) {
        $(this).find('.modal-body').html(lblConfCancel);
    });

    $('#cancelReservationModal form #confirm').on('click', function (e) {
        e.preventDefault();
        var $form = $(this).parents('form:first');

        ajaxDelete($form.attr('action') + $('#idCancel').val(), function () {
            showProgress();
        }, function (response) {
            setTimeout(function () {
                hideProgress();
            }, 35);
            $('#cancelReservationModal').modal('hide');
            showNotification('success', lblReservationCancelSuccess);
            $table.bootstrapTable('refresh');
        });
    });

$table.on('load-success.bs.table', function (data) {
    $('.info').popover({
        html: true,
        placement: 'bottom'
    });
});

$('#reservationReportModal form #generate').on('click', function (e) {
    e.preventDefault();
    var $form = $(this).parents('form:first');
    if ($form.parsley().validate()) {
        var queryStr = $form.serialize();
        printJS($form.attr('action') + "?" + queryStr);
        hideProgress();
    }
});