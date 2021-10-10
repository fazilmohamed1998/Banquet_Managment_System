<%--
  Created by IntelliJ IDEA.
  User: HaShaN
  Date: 6/29/2019
  Time: 11:17 AM
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <jsp:include page="resource.jsp"/>
    <style>
        .details {
            min-width: 160px;
        }

        .details span {
            margin-bottom: 2px;
        }

        .user-name {
            font-size: 15px;
            font-weight: 700;
        }

        .user-mail, .user-contact {
            font-size: 13px;
        }
    </style>
</head>
<body>

<div class="content">
    <%--<h1>HaShaN</h1>--%>
    <div class="row">
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
            <div class="card hover-shadow">
                <div class="add-card card-body d-flex justify-content-between">
                    <i class="far fa-plus hover-translate-y-n3"></i>
                </div>
            </div>
        </div>
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
            <div class="card hover-shadow">
                <div class="card-body d-flex justify-content-between">
                    <a href="#"
                       class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                       data-bw="3px">
                        <img alt="Image placeholder"
                             src="https://keenthemes.com/keen/preview/demo1/assets/media/users/100_14.jpg">
                    </a>
                    <div class="details vertical-middle">
                        <span class="user-name">K.G.HaShaN Sandeepa</span><br>
                        <span class="user-mail">hashans95@gmail.com</span><br>
                        <span class="user-contact">0718761179</span><br>
                        <span class="badge badge-success mt-3">
                            Admin</span>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="actions d-flex justify-content-between px-4">
                        <a href="#" class="action-item hover-translate-y-n3">
                            <i class="far fa-edit"></i>
                        </a>
                        <a href="#" class="action-item text-danger hover-translate-y-n3">
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
            <div class="card hover-shadow">
                <div class="card-body d-flex justify-content-between">
                    <a href="#"
                       class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                       data-bw="3px">
                        <img alt="Image placeholder"
                             src="https://keenthemes.com/keen/preview/demo1/assets/media/users/100_3.jpg">
                    </a>
                    <div class="details vertical-middle">
                        <span class="user-name">K.G.HaShaN Sandeepa</span><br>
                        <span class="user-mail">hashans95@gmail.com</span><br>
                        <span class="user-contact">0718761179</span><br>
                        <span class="badge badge-danger mt-3">
                            Receptionist</span>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="actions d-flex justify-content-between px-4">
                        <a href="#" class="action-item hover-translate-y-n3">
                            <i class="far fa-edit"></i>
                        </a>
                        <a href="#" class="action-item text-danger hover-translate-y-n3">
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
            <div class="card hover-shadow">
                <div class="card-body d-flex justify-content-between">
                    <a href="#"
                       class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                       data-bw="3px">
                        <img alt="Image placeholder"
                             src="https://keenthemes.com/keen/preview/demo1/assets/media/users/100_7.jpg">
                    </a>
                    <div class="details vertical-middle">
                        <span class="user-name">K.G.HaShaN Sandeepa</span><br>
                        <span class="user-mail">hashans95@gmail.com</span><br>
                        <span class="user-contact">0718761179</span><br>
                        <span class="badge badge-warning mt-3">Accountant</span>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="actions d-flex justify-content-between px-4">
                        <a href="#" class="action-item hover-translate-y-n3">
                            <i class="far fa-edit"></i>
                        </a>
                        <a href="#" class="action-item text-danger hover-translate-y-n3">
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-xl-2 col-md-3 col-lg-4 col-sm-6">
            <div class="card hover-shadow">
                <div class="card-body d-flex justify-content-between">
                    <a href="#"
                       class="avatar rounded-circle avatar-sm hover-translate-y-n3 border-secondary shadow-lg"
                       data-bw="3px">
                        <img alt="Image placeholder"
                             src="https://keenthemes.com/keen/preview/demo1/assets/media/users/300_21.jpg">
                    </a>
                    <div class="details vertical-middle">
                        <span class="user-name">K.G.HaShaN Sandeepa</span><br>
                        <span class="user-mail">hashans95@gmail.com</span><br>
                        <span class="user-contact">0718761179</span><br>
                        <span class="badge badge-info mt-3">Manager</span>
                    </div>
                </div>
                <div class="card-footer">
                    <div class="actions d-flex justify-content-between px-4">
                        <a href="#" class="action-item hover-translate-y-n3">
                            <i class="far fa-edit"></i>
                        </a>
                        <a href="#" class="action-item text-danger hover-translate-y-n3">
                            <i class="far fa-trash-alt"></i>
                        </a>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div style="background-color: white;padding: 10px; border-radius: .375rem;">
        <h2>Buttons</h2>
        <button type="button" class="btn btn-sm btn-primary btn-round">
            Primary
        </button>
        <button type="button" class="btn btn-sm btn-danger btn-round">
            Danger
        </button>
        <button type="button" class="btn btn-sm btn-success btn-round">
            Success
        </button>
        <button type="button" class="btn btn-sm btn-info btn-round">
            Info
        </button>
        <button type="button" class="btn btn-sm btn-warning btn-round">
            Warning
        </button>
        <br><br>

        <h2>Alerts</h2>
        <div class="alert alert-danger alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <i class="icon far fa-ban"></i>
            <div class="content">
                <strong>Holy guacamole!</strong> You should check in on some of those fields below.
            </div>
        </div>

        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <i class="icon far fa-check"></i>
            <div class="content">
                <strong>Holy guacamole!</strong> You should check in on some of those fields below.
            </div>
        </div>

        <div class="alert alert-info alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <i class="icon far fa-info-circle"></i>
            <div class="content">
                <strong>Holy guacamole!</strong> You should check in on some of those fields below.
            </div>
        </div>

        <div class="alert alert-warning alert-sm alert-dismissible fade show" role="alert">
            <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
            </button>
            <i class="icon far fa-exclamation-triangle"></i>
            <div class="content">
                <strong>Holy guacamole!</strong> You should check in on some of those fields below.
            </div>
        </div>
        <br><br>
        <h2>Form</h2>
        <form action="">

            <div class="row">
                <div class="col-lg-3">
                    <div class="form-group">
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="customCheck1"
                                   checked>
                            <label class="custom-control-label"
                                   for="customCheck1">Checkbox(checked)</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="custom-control custom-checkbox">
                            <input type="checkbox" class="custom-control-input" id="customCheck2"
                                   data-toggle="indeterminate">
                            <label class="custom-control-label" for="customCheck2">Checkbox(unchecked)</label>
                        </div>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" id="customRadio1" name="customRadio"
                                   class="custom-control-input"
                                   checked>
                            <label class="custom-control-label"
                                   for="customRadio1">Radio(checked)</label>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="custom-control custom-radio">
                            <input type="radio" id="customRadio2" name="customRadio"
                                   class="custom-control-input">
                            <label class="custom-control-label"
                                   for="customRadio2">Radio(unchecked)</label>
                        </div>
                    </div>
                </div>
            </div>
            <br>
            <div class="row">
                <div class="col-lg-3">
                    <div class="form-group">
                        <input name="text" id="text" placeholder="Text Field" type="text"
                               class="form-control">
                    </div>
                    <div class="form-group">
                        <input name="text" id="password"
                               type="password" class="form-control" placeholder="Password Field">
                    </div>
                    <div class="form-group">
                        <select class="selectpicker form-control" data-hide-disabled="true"
                                data-live-search="true"
                                title="Single Select">
                            <option>Active</option>
                            <option>Inactive</option>
                            <option>Blocked</option>
                        </select>
                    </div>
                </div>
                <div class="col-lg-3">
                    <div class="form-group">
                        <input type="text" class="datepicker form-control" placeholder="Date"/>
                    </div>

                    <div class="form-group">
                        <input type="text" class="timepicker form-control"
                               placeholder="Time"/>
                    </div>
                </div>
            </div>
        </form>

        <h2>Table</h2>
        <table id="table"
               data-toolbar="#toolbar"
               data-search="true"
               data-show-refresh="true"
               data-show-toggle="true"
               data-show-fullscreen="true"
               data-show-columns="true"
               data-detail-view="true"
               data-show-export="true"
               data-click-to-select="true"
               data-detail-formatter="detailFormatter"
               data-minimum-count-columns="2"
               data-show-pagination-switch="true"
               data-pagination="true"
               data-id-field="id"
               data-page-list="[10, 25, 50, 100, all]"
               data-show-footer="true"
               data-side-pagination="server"
               data-url="https://examples.wenzhixin.net.cn/examples/bootstrap_table/data"
               data-response-handler="responseHandler">
        </table>

        <h2>Model</h2>
        <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
                data-target="#sampleSmallModel" data-backdrop="static">Show Small Modal
        </button>
        <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
                data-target="#sampleMediumModel" data-backdrop="static">Show Medium Modal
        </button>
        <button type="button" class="btn btn-primary btn-round btn-sm" data-toggle="modal"
                data-target="#sampleLargeModel" data-backdrop="static">Show Large Modal
        </button>
        <div id="sampleMediumModel" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-md" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Model Title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        This is a medium sized model
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary btn-round btn-sm">Submit
                        </button>
                        <button type="button" class="btn btn-danger btn-round btn-sm"
                                data-dismiss="modal">Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div id="sampleSmallModel" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-sm" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Model Title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        This is a small sized model
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary btn-round btn-sm">Submit
                        </button>
                        <button type="button" class="btn btn-danger btn-round btn-sm"
                                data-dismiss="modal">Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
        <div id="sampleLargeModel" class="modal fade" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Model Title</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        This is a large sized model
                    </div>

                    <div class="modal-footer">
                        <button type="submit" class="btn btn-primary btn-round btn-sm">Submit
                        </button>
                        <button type="button" class="btn btn-danger btn-round btn-sm"
                                data-dismiss="modal">Cancel
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="nav.jsp"/>

<script type="text/javascript">
    var $table = $('#table');
    var $remove = $('#remove');
    var selections = [];

    $(function () {
        $('#dashboard-menu').addClass('active');
        $('#dashboard-menu').siblings().removeClass('active');

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

    function detailFormatter(index, row) {
        var html = [];
        $.each(row, function (key, value) {
            html.push('<p><b>' + key + ':</b> ' + value + '</p>');
        });
        return html.join('');
    }

    function operateFormatter(value, row, index) {
        return [
            '<button class="btn btn-link link-success like" href="javascript:void(0)" title="Like">',
            '<i class="fa fa-heart"></i>',
            '</button>  ',
            '<button class="btn btn-link link-danger remove" href="javascript:void(0)" title="Remove">',
            '<i class="fa fa-trash"></i>',
            '</button>'
        ].join('');
    }

    window.operateEvents = {
        'click .like': function (e, value, row, index) {
            alert('You click like action, row: ' + JSON.stringify(row.price));
        },
        'click .remove': function (e, value, row, index) {
            $table.bootstrapTable('remove', {
                field: 'id',
                values: [row.id]
            });
        }
    };

    function totalTextFormatter(data) {
        return 'Total';
    }

    function totalNameFormatter(data) {
        return data.length;
    }

    function totalPriceFormatter(data) {
        var field = this.field;
        return '$' + data.map(function (row) {
            return +row[field].substring(1)
        }).reduce(function (sum, i) {
            return sum + i
        }, 0);
    }

    function initTable() {
        $table.bootstrapTable('destroy').bootstrapTable({
            height: 550,
            //locale: $('#locale').val(),
            columns: [
                [{
                    title: 'Item ID',
                    field: 'id',
                    rowspan: 2,
                    align: 'center',
                    valign: 'middle',
                    sortable: true,
                    footerFormatter: totalTextFormatter
                }, {
                    title: 'Item Detail',
                    colspan: 3,
                    align: 'center'
                }],
                [{
                    field: 'name',
                    title: 'Item Name',
                    sortable: true,
                    footerFormatter: totalNameFormatter,
                    align: 'center'
                }, {
                    field: 'price',
                    title: 'Item Price',
                    sortable: true,
                    align: 'center',
                    footerFormatter: totalPriceFormatter
                }, {
                    field: 'operate',
                    title: 'Item Operate',
                    align: 'center',
                    events: window.operateEvents,
                    formatter: operateFormatter
                }]
            ],
            classes: 'table'
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
</script>
</body>
</html>
<%--Documentations--%>
<%--Bootstrap 4 - https://getbootstrap.com/docs/4.3/components --%>
<%--Bootstrap Table - https://examples.bootstrap-table.com --%>
<%--Parsley Validation - https://parsleyjs.org/doc/ --%>
<%--Bootstrap Select - https://developer.snapappointments.com/bootstrap-select/examples/ --%>
