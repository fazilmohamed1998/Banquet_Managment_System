$(function () {
    $('.selectpicker').selectpicker();
    $('.inputmask').inputmask();
    $('[data-toggle="tooltip"]').tooltip();
    $('.datepicker').datetimepicker({
        icons: {
            previous: "far fa-chevron-left",
            next: "far fa-chevron-right"
        },
        format: "YYYY/MM/DD"
    });
    $('.timepicker').datetimepicker({
        icons: {
            up: "far fa-chevron-up",
            down: "far fa-chevron-down"
        },
        format: 'LT'
    });

    $('.nav-tabs a').click(function (e) {
        e.preventDefault();

        var url = $(this).attr("data-url");
        var href = this.hash;
        var pane = $(this);

        $(this).closest('li').addClass('active');
        $(this).closest('li').siblings().removeClass('active');

        // ajax load from data-url
        $(href).load(url, function (result) {
            pane.tab('show');
        });
    });

    if ($('.tab-content').length) {
        //load first tab content
        $('.tab-content .tab-pane.active').load($('.nav-tabs .active a').attr("data-url"),
            function (result) {
                $(this).tab('show');
            });
    }
});

window.Parsley
    .addValidator('datelte', {
        requirementType: 'string',
        validateString: function (value, toDateField) {
            if (!value || !$(toDateField).val()) {
                return true;
            }
            return new Date(value) <= new Date($(toDateField).val());
        }
    })
    .addValidator('dategte', {
        requirementType: 'string',
        validateString: function (value, fromDateField) {
            if (!value || !$(fromDateField).val()) {
                return true;
            }
            return new Date(value) >= new Date($(fromDateField).val());
        }
    })
    .addValidator('email', {
        requirementType: 'string',
        validateString: function (value) {
            if (!value) {
                return true;
            }
            var re = /^(([^<>()\[\]\.,;:\s@\"]+(\.[^<>()\[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
            return new RegExp(re).test(value);
        }
    });