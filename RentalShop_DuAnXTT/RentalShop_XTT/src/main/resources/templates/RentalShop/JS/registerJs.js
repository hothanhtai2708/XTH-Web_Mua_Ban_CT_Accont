$(document).ready(function () {
    $('#SubmitRegisterId').click(function (event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit

        var email = $('#email').val();
        var username = $('#username').val();
        var password = $('#password').val();
        var confirmPassword = $('#confirmPassword').val();

        // Gửi yêu cầu POST
        $.ajax({
            type: 'POST',
            url: '/register', // Địa chỉ API của bạn
            data: {
                email: email,
                username: username,
                password: password,
                confirmPassword: confirmPassword
            },
            success: function (response) {
                if (response.success) {
                    Swal.fire({
                        title: "Thành công!",
                        text: "OTP đã được gửi đến email của bạn. Vui lòng kiểm tra và nhập OTP để xác nhận đăng ký.",
                        icon: "success"
                    }).then(() => {
                        // Chuyển hướng tới trang OTP
                        location.href = '/confirm-register';
                    });
                } else {
                    Swal.fire({
                        title: "Thất bại!",
                        text: "Có lỗi xảy ra. Vui lòng thử lại.",
                        icon: "error"
                    }).then(() => {
                        // Chuyển hướng tới trang OTP
                        location.href = '/register';
                    });;
                }
            },
            error: function (xhr, status, error) {
                Swal.fire({
                    title: "Thất bại!",
                    text: "Có lỗi xảy ra. Vui lòng thử lại.",
                    icon: "error"
                });
            }
        });
    });
});
s