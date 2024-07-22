$(document).ready(function() {
    $.ajax({
        url: '/views-product',
        type: 'GET',
        success: function(response) {
            if (response.status) {
                var products = response.data;
                var productHtml = '';

                products.forEach(function(product) {
                    productHtml += `
                        <a class="col-6 col-md-4 col-lg-3 text-decoration-none" href="detail/${product.slug}">
                            <div class="card border-0 bg-transparent">
                                <img src="${product.image}" class="card-img-top rounded" alt="${product.name}">
                                <div class="card-body px-0">
                                    <h5 class="card-title" style="font-size: 0.9rem;">${product.name}</h5>
                                    <div class="d-flex gap-2 align-items-center">
                                        <p style="font-size: 0.9rem;" class="m-0 fw-bold">${product.price}&nbsp;₫</p>
                                    </div>
                                </div>
                            </div>
                        </a>
                    `;
                });

                $('#div-san-pham-noi-bac').html(productHtml);
            } else {
                console.error(response.message);
            }
        },
        error: function(error) {
            console.error("Call API product error!", error);
        }
    });
});
