package com.example.rentalshop_xtt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login111")
    public String login111() {
        return "RentalShop/HTML/login";
    }
}
