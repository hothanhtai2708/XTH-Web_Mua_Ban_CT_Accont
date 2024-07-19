package com.example.rentalshop_xtt.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login111")
    public String login111() {
        return "RentalShop/HTML/login";
    }

    @GetMapping("/index")
    public String index() {
        return "RentalShop/HTML/test";
    }

    @GetMapping("/login123")
    public String login() {
        return "RentalShop/HTML/login";
    }
}
