// Assume you have 6 input fields with IDs otp1, otp2, ..., otp6
let otpInputDTO = {
    otp1: document.getElementById('otp1').value,
    otp2: document.getElementById('otp2').value,
    otp3: document.getElementById('otp3').value,
    otp4: document.getElementById('otp4').value,
    otp5: document.getElementById('otp5').value,
    otp6: document.getElementById('otp6').value
};

// Send OTP data to backend API
fetch('/confirm-register', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
    },
    body: JSON.stringify(otpInputDTO)
})
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        // Handle success response from API
    })
    .catch((error) => {
        console.error('Error:', error);
        // Handle error response from API
    });
