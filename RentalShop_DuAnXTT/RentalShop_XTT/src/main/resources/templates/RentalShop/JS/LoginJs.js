$(document).ready(function() {
    $('#submitLoginId').click(function() {
        var email = $('#emailId').val();
        var password = $('#passwordId').val();

        $.ajax({
            url: '/login',
            type: 'GET',
            data: {
                email: email,
                password: password
            },
            success: function(response) {
                if (response.status) {
                    alert('Login successful!');
                    // You can redirect the user or update the UI as needed
                } else {
                    alert('Login failed: ' + response.massage);
                }
            },
            error: function(xhr, status, error) {
                console.error('Error: ' + error);
                alert('An error occurred during login. Please try again.');
            }
        });
    });
});
