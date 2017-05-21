$(document).ready(function () {
    var modelFilter = $('#modelFilter');
    modelFilter.on('change', function () {
        var typeId = $(this).val();
        $.ajax('productList', {
            method: 'GET',
            data: {typeId: typeId},
            success: function(result) {
                $('body').html(result);
            }
        })
    })
});
