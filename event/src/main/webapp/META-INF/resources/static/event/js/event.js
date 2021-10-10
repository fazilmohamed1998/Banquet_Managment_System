var $table = $('#event');
var $remove = $('#remove');
var selections = [];

$(function () {
    $('#event-menu').addClass('active');
    $('#event-menu').siblings().removeClass('active');
    initTable();
});



function getIdSelections() {
    return $.map($table.bootstrapTable('getSelections'), function (row) {
        return row.id;
    });
}

function responseHandler(res) {
    $.each(res.rows, function (i, row) {
        row.state = $.inArray(row.id, selections) !== -1;
    });
    return res;
}

function actionFormatter(value, row, index) {
    return [
        '<button class="btn btn-link link-info edit" href="javascript:void(0)" title="Edit">',
        '<i class="far fa-edit"></i>',
        '</button>',
        '<button class="btn btn-link link-danger refund" href="javascript:void(0)" title="Refund">',
        '<i class="far fa-undo"></i>',
        '</button>',
        '<button class="btn btn-link link-success print" href="javascript:void(0)" title="Print">',
        '<i class="fal fa-print"></i>',
        '</button>'
    ].join('');
}



function operateFormatter() {
    return [
        '<button type="button" onclick="myfunction()">'+'More Details'+'</button>'
    ].join('');
}



function myfunction() {
    document.getElementById("topic").innerHTML = "General information";
    document.getElementById("demo").innerHTML = "<h5>Location</h5>" +"<h5>Duration</h5>"+"<h5>Engineering</h5>"+"<h5>Room setup</h5>"+"<h5>Sign Board</h5>"+"<h5>Flower decor</h5>"+"<h5>Food Service</h5>"+"<h5>Participant</h5>";
}





function priceFormatter(value, row, index) {
    return formatCurrency(value);
}

function idFormatter(value, row, index) {
    return formatCurrency(value);
}


function initTable() {
    $table.bootstrapTable('destroy').bootstrapTable({
        height: '550',
        //locale: $('#locale').val(),
        columns: [{
            title: colId,
            field: 'id',
            align: 'center',
            valign: 'middle',
            sortable: true,
            formatter: idFormatter

        }, {
            title: colCust,
            field: 'Customer',
            align: 'center',
            valign: 'middle'
        }, {
            title: colEvent,
            field: 'Event',
            align: 'center',
            valign: 'middle',

        }, {
            title: colDescription,
            field: 'Description',
            align: 'right',
            valign: 'middle',

        }, {
            title: colLocation,
            field: 'Location',
            align: 'right',
            valign: 'middle',

        },
            {
            title: colFrom,
            field: 'From',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colFrom,
            field: 'To',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colStatus,
            field: 'status',
            align: 'center',
            valign: 'middle',
            sortable: true
        }, {
            title: colactions,
            align: 'center',
            events: window.actionEvents,
            formatter: actionFormatter
        }, {
                field: 'Details',
                title: 'Details',
                align: 'center',
                formatter: operateFormatter
            }],
        classes: 'table',
        data: [{
            "id": 1,
            "Customer": "Aravinda.k",
            "Event": "Birthday",
            "Description": "Aravinda's party",
            "Location": "Grand Ballroom",
            "From": "July 20,2019 07:00PM",
            "To": "July 20,2019 11:00PM",
            "status": "Done",
        }, {
            "id": 2,
            "Customer": "Dilshan.u",
            "Event": "Wedding ",
            "Description": "Dilshan wedding",
            "Location": "Tuscany Ballroom",
            "From": "July 21,2019 9:00PM",
            "To": "July 21,2019 4:00AM",
            "status": "Done",
        },
            {
                "id": 3,
                "Customer": "Sandaru.u",
                "Event": "Corparate Evet",
                "Description": "LB finace",
                "Location": "Tuscany Ballroom",
                "From": "July 22,2019 9:00PM",
                "To": "July 22,2019 4:00AM",
                "status": "Done",
            }
        ]
    });
    $table.on('check.bs.table uncheck.bs.table ' +
        'check-all.bs.table uncheck-all.bs.table',
        function () {
            $remove.prop('disabled', !$table.bootstrapTable('getSelections').length);

            // save your data, here just save the current page
            selections = getIdSelections()
            // push or splice the selections if you want to save all data selections
        });
    $table.on('all.bs.table', function (e, name, args) {
        console.log(name, args);
    });
    $remove.click(function () {
        var ids = getIdSelections();
        $table.bootstrapTable('remove', {
            field: 'id',
            values: ids
        });
        $remove.prop('disabled', true);
    })
}
