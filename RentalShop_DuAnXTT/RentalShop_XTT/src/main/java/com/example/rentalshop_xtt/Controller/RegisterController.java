package com.example.rentalshop_xtt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterController {

    @GetMapping("/registers")
    public String regesters(){
        return "RentalShop/HTML/register";
    }
    @GetMapping("/confirm-registers")
    public String confirmOTPs() {
        return "RentalShop/HTML/ConfirmOtp";
    }

    @GetMapping("/indexs")
    public String index() {
        return "RentalShop/HTML/index";
    }
}
