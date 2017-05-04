$(document).ready(function () {
    var typeSelector = $('#typeSelector');
    var modelSelector = $('#modelSelector');
    typeSelector.on('change', function () {
        var typeId = $(this).val();
        $.ajax('models', {
            method: 'GET',
            data: {typeId: typeId},
            success: function(result) {
                var models = JSON.parse(result);
                modelSelector.find('option').remove();
                var options = '';
                $.each(models, function() {
                    options += '<option value="' + this.modelId + '">' + this.modelName + '</option>';
                });
                modelSelector.html(options);
            }
        })
    })
});