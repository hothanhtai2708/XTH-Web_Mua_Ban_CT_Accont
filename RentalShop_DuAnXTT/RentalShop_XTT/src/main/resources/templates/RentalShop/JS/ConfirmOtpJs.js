$(document).ready(function () {
    $('#ConfirmOtpId').click(function (event) {
        event.preventDefault(); // Ngăn chặn hành vi mặc định của nút submit
        // Collect OTP inputs
        var otp1 = $('#otp1').val();
        var otp2 = $('#otp2').val();
        var otp3 = $('#otp3').val();
        var otp4 = $('#otp4').val();
        var otp5 = $('#otp5').val();
        var otp6 = $('#otp6').val();

        // Combine OTP
        var otp = otp1 + otp2 + otp3 + otp4 + otp5 + otp6;

        // Prepare data to send
        var formData = {
            otp
        };

        // Send POST request
        $.ajax({
            type: 'POST',
            url: '/confirm-register', // Your API endpoint
            data: formData,
            success: function (response) {
                if (response.success) {
                    Swal.fire({
                        title: "Thành công !",
                        text: "Bạn đã đăng kí tài khoảng Thành công !",
                        icon: "success"
                    }).then(() => {
                        location.href = "/login111";
                    });
                } else {
                    // Xử lý khi yêu cầu thất bại
                    console.error('Error: ' + error);
                    Swal.fire({
                        title: "Thất bại !",
                        text: "Có lỗi xảy ra. Vui lòng thử lại.",
                        icon: "error"
                    }).then(() => {
                        location.href = "/confirm-register";
                    });
                }
            },
            error: function (xhr, status, error) {
                // Xử lý khi yêu cầu thất bại
                console.error('Error: ' + error);
                Swal.fire({
                    title: "Thất bại !",
                    text: "Có lỗi xảy ra. Vui lòng thử lại.",
                    icon: "error"
                });
            }
        });
    });
});