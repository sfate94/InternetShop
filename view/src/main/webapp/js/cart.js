$(document).ready(function () {
    var container;
    var initListeners = function () {
        $('.quantityInput').on('change', function () {
            container = $(this).closest('.cartLinesContainer');
            var options = {
                success:  showResponse
            };
            $("#cartForm").ajaxSubmit(options);
        });
    };

    function showResponse(response)  {
        container.html(response);
        initListeners();
    }

    initListeners();
});