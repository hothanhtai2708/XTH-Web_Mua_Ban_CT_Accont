let publicKey;

// //tự động kết nối
// (async () => {
//     await window.phantom.solana.connect();
//     publicKey = window.phantom.solana.publicKey.toBase58();
// })();

//kết nối bằng tay
const connectVi = async () => {
    await window.phantom.solana.connect();
    publicKey = window.phantom.solana.publicKey.toBase58();
    console.log(publicKey);
};

// ================= [ MINT NFT] ==================
const Prive_key = "T6i4xYb82eJKE5ov";

const Mint_NFT = async () => {
    var myHeaders = new Headers();
    myHeaders.append("x-api-key", Prive_key);
    myHeaders.append("Content-Type", "multipart/form-data");

    var formdata = new FormData();
    formdata.append("network", "devnet");
    formdata.append("wallet", publicKey);
    formdata.append("name", "FPOLY NFT");
    formdata.append("symbol", "FPNF");
    formdata.append("description", "FPNF Token makes Web3 so easy!");
    formdata.append("attributes", '[{"trait_type":"dev power","value":"over 900"}]');
    formdata.append("external_url", "https://shyft.to");
    formdata.append("max_supply", "0");
    formdata.append("royalty", "5");
    formdata.append("file", fileInput.files[0], "index.png");
    formdata.append("nft_receiver", "5KW2twHzRsAaiLeEx4zYNV35CV2hRrZGw7NYbwMfL4a2");
    formdata.append('service_charge', '{ "receiver": "499qpPLdqgvVeGvvNjsWi27QHpC8GPkPfuL5Cn2DtZJe",  "token": "DjMA5cCK95X333t7SgkpsG5vC9wMk7u9JV4w8qipvFE8",  "amount": 0.01}');

    var requestOptions = {
        method: 'POST',
        headers: myHeaders,
        body: formdata,
        redirect: 'follow'
    };

    fetch("https://api.shyft.to/sol/v1/nft/create_detach", requestOptions)
        .then(response => response.text())
        .then(result => console.log(result))
        .catch(error => console.log('error', error));
};
