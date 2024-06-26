package com.example.rentalshop_xtt.Api;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginApi {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    private ResponseEntity<?> Login(@RequestParam String email) {
        AccountE accountE = loginService.findByEmail(email);
        Map<String,Object> map = new HashMap<>();
        map.put("status",true);
        map.put("data",accountE);
        map.put("massage","Call Api ok ?");
        return ResponseEntity.ok(map);
    }
}
