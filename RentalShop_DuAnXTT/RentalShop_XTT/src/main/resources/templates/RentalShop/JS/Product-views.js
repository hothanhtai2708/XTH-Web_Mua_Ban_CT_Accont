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

<div class="row">
  <div class="col-6 col-md-4 col-lg-3">
    <div class="text-decoration-none h-100 d-flex flex-column" href="detail/${product.slug}">
      <div class="card border-0 bg-transparent h-100 d-flex flex-column">
        <img src="/Library/${product.pictures}" class="card-img-top rounded img-fluid" id="productImage" alt="${product.product_name}">
        <div class="card-body px-0 d-flex flex-column justify-content-between">
          <h5 class="card-title text-truncate" style="font-size: 1rem;">${product.product_name}</h5>
          <p class="card-text" style="font-size: 0.9rem;">${product.description}</p>
          <div class="d-flex justify-content-between align-items-center mt-auto">
            <p class="m-0 fw-bold text-danger" style="font-size: 1rem;">${product.price}&nbsp;₫</p>
            
            <!-- Nút bấm để mint NFT -->
            <button class="btn btn-danger" onclick="Mint_NFT()" type="button">Mua</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>




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

// <div class="row">
//   <div class="col-6 col-md-4 col-lg-3">
//     <div class="text-decoration-none h-100 d-flex flex-column" href="detail/${product.slug}">
//       <div class="card border-0 bg-transparent h-100 d-flex flex-column">
//         <img src="/Library/${product.pictures}" class="card-img-top rounded img-fluid" id="productImage" alt="${product.product_name}">
//         <div class="card-body px-0 d-flex flex-column justify-content-between">
//           <h5 class="card-title text-truncate" style="font-size: 1rem;">${product.product_name}</h5>
//           <p class="card-text" style="font-size: 0.9rem;">${product.description}</p>
//           <div class="d-flex justify-content-between align-items-center mt-auto">
//             <p class="m-0 fw-bold text-danger" style="font-size: 1rem;">${product.price}&nbsp;₫</p>
//             <button class="btn btn-danger" onclick="Mint_NFT()" type="button">Mua</button>
//           </div>
//         </div>
//       </div>
//     </div>
//   </div>
// </div>



//   // <img src="/templates/RentalShop/Library/${product.pictures}" class="card-img-top rounded" alt="${product.product_name}">
