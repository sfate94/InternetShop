$(document).ready(function () {
    var modelFilter = $('#modelFilter');
    modelFilter.on('change', function () {
        var typeId = $(this).val();
        $.ajax('productList', {
            method: 'GET',
            data: {typeId: typeId},
            success: function(result) {
                $('.products_container').html(result);
                initListeners();
            }
        })
    });

    var initListeners = function() {
        $('.nextButton, .previousButton').on('click', function () {
            var page = $(this).attr('page');
            var typeId = $(this).attr('typeId');
            $.ajax('productList', {
                method: 'GET',
                data: {page: page, typeId: typeId},
                success: function(result) {
                    $('.products_container').html(result);
                    initListeners();
                }
            })
        });
    };

    initListeners();
});
