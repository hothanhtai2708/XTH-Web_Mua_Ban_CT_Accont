$(document).ready(function () {
    // Kiểm tra dữ liệu phiên làm việc khi tải trang
    $.ajax({
        url: '/session-data',
        type: 'GET',
        success: function (response) {
            if (response.status) {
                // Cập nhật UI với dữ liệu phiên làm việc
                $('#userContainer').html(`
            <button type="button" class="btn btn-outline-light rounded-circle"><i class="fa-solid fa-user"></i></button>
            <span style="font-size: 0.83rem;">${response.data.name}</span>
          `);
            }
        }
    });

    $('#submitLoginId').click(function () {
        var email = $('#emailId').val();
        var password = $('#passwordId').val();

        if (email && password) {
            $.ajax({
                url: '/login',
                type: 'POST',
                data: {
                    email: email,
                    password: password
                },
                success: function (response) {
                    if (response.status) {
                        Swal.fire({
                            title: "Thành công!",
                            text: response.message,
                            icon: "success"
                        }).then((result) => {
                            if (result.isConfirmed) {
                                // Gọi lại API session-data để cập nhật giao diện người dùng
                                location.reload();
                                $.ajax({
                                    url: '/session-data',
                                    type: 'GET',
                                    success: function (response) {
                                        if (response.status) {
                                            // Cập nhật UI với dữ liệu phiên làm việc
                                            $('#userContainer').html(`
                          <button type="button" class="btn btn-outline-light rounded-circle"><i class="fa-solid fa-user"></i></button>
                          <span style="font-size: 0.83rem;">${response.data.name}</span>
                        `);
                                        }
                                    }
                                });
                            }
                        });
                    } else {
                        Swal.fire({
                            title: "Thất bại!",
                            text: response.message,
                            icon: "error"
                        });
                    }
                }
            });
        } else {
            Swal.fire({
                title: "Thất bại!",
                text: "Không được để trống email và mật khẩu.",
                icon: "error"
            });
        }
    });
});

