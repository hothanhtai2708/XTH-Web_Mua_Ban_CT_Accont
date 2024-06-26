package com.example.rentalshop_xtt.Api;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Data.Model.LoginM;
import com.example.rentalshop_xtt.Service.LoginService;
import com.example.rentalshop_xtt.Variable.StaticVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

import static com.example.rentalshop_xtt.Utils.PasswordEncoderUtil.verifyPassword;
import static com.example.rentalshop_xtt.Variable.StaticVariable.sessionEmail;

@RestController
public class LoginApi {
    @Autowired
    private LoginService loginService;

    @GetMapping("/login")
    private ResponseEntity<?> Login(@RequestParam String email, @RequestParam String password) {
        LoginM loginM = loginService.findByEmail(email);
        boolean checkPassword = verifyPassword(password,loginM.getPassword());
        Map<String,Object> result = new HashMap<>();
        if (checkPassword) {
            sessionEmail = email;
            System.out.println(sessionEmail);
            result.put("status",true);
            result.put("data",loginM);
            result.put("massage","Call Api success !");
            return ResponseEntity.ok(result);
        }else {
            result.put("status",false);
            result.put("data",null);
            result.put("massage","Call Api false !");
            return ResponseEntity.ok(result);
        }
    }
}
