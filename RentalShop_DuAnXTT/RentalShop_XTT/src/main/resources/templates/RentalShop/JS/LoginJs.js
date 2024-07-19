$(document).ready(function() {
    $('#loginForm').on('submit', function(e) {
        e.preventDefault();

        var email = $('#email').val();
        var password = $('#password').val();
        var valid = true;

        // Reset error messages
        $('#emailError').text('');
        $('#passwordError').text('');

        // Validate email
        if (!email) {
            Swal.fire({
                title: 'Thất bại!',
                text: 'Email không được để trống.',
                icon: 'error'
            });
            $('#emailError').text('Email không được để trống.');
            valid = false;
        } else if (!validateEmail(email)) {
            Swal.fire({
                title: 'Thất bại!',
                text: 'Email không hợp lệ.',
                icon: 'error'
            });
            $('#emailError').text('Email không hợp lệ.');
            valid = false;
        }

        // Validate password
        if (!password) {
            Swal.fire({
                title: 'Thất bại!',
                text: 'Mật khẩu không được để trống.',
                icon: 'error'
            });
            $('#passwordError').text('Mật khẩu không được để trống.');
            valid = false;
        }

        if (valid) {
            $.ajax({
                url: '/login',
                type: 'POST',
                data: {
                    email: email,
                    password: password
                },
                success: function(response) {
                    if (response.status) {
                        Swal.fire({
                            title: 'Thành công!',
                            text: response.message,
                            icon: 'success'
                        }).then((result) => {
                            if (result.isConfirmed) {
                                window.location.href = "/index";
                            }
                        });

                    } else {
                        Swal.fire({
                            title: 'Thất bại!',
                            text: response.message,
                            icon: 'error'
                        });
                    }
                },
                error: function(error) {
                    console.error('Đã xảy ra lỗi:', error);
                    Swal.fire({
                        title: 'Lỗi!',
                        text: 'Đã xảy ra lỗi, vui lòng thử lại sau.',
                        icon: 'error'
                    });
                }
            });
        }
    });

    function validateEmail(email) {
        var re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }
});