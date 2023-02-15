$(document).ready(function () {
    function addMessage(message) {
        let block = '<div class="alert alert-danger alert-dismissible fade show" role="alert">' +
            message +
            '<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button></div>';
        $("main").append(block);
    }

    $("form").submit(function () {
        let method = $(this).attr('method') === undefined ? "get" : $(this).attr('method')
        let action = $(this).attr('action') === undefined ? window.location.pathname : $(this).attr('action')
        let data = JSON.stringify($(this).serializeObject())

        let button = $(this).find('[type="submit"]')
        button.addClass('disabled')

        $.ajax({
            url: action,
            method: method,
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: data,
            success: function (response) {
                let obj = response
                if (obj.location !== undefined){
                    window.location = obj.location
                    return false
                } else {
                    button.removeClass('disabled')
                }
                if (obj.message !== undefined) {
                    addMessage(obj.message)
                }
            }
        });
        return false
    });
})