package com.example.rentalshop_xtt.Api;

import com.example.rentalshop_xtt.Data.Model.LoginM;
import com.example.rentalshop_xtt.Service.LoginService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;
import static com.example.rentalshop_xtt.Utils.PasswordEncoderUtil.verifyPassword;

@RestController
public class LoginApi {
    @Autowired
    private LoginService loginService;
    @Autowired
    private HttpSession session;

    @PostMapping("/login")
    public ResponseEntity<?> Login(@RequestParam String email, @RequestParam String password) {
        LoginM loginM = loginService.findByEmail(email);
        Map<String,Object> result = new HashMap<>();
        if(loginM != null){
            boolean checkPassword = verifyPassword(password,loginM.getPassword());
            if (checkPassword) {
                session.setAttribute("email", email);
                System.out.println(session.getAttribute("email"));
                session.setAttribute("username", loginM.getName());
                System.out.println(session.getAttribute("username"));
                result.put("status", true);
                result.put("data", loginM);
                result.put("message", "Đăng nhập thành công!");
                return ResponseEntity.ok(result);
            } else {
                result.put("status", false);
                result.put("data", null);
                result.put("message", "Đăng nhập thất bại!");
                return ResponseEntity.ok(result);
            }
        } else {
            result.put("status", false);
            result.put("data", null);
            result.put("message", "Tài khoản không tồn tại!");
            return ResponseEntity.ok(result);
        }
    }

//    @GetMapping("/session-data")
//    public ResponseEntity<?> getSessionData() {
//        Map<String, Object> result = new HashMap<>();
//        String email = session.getAttribute("email").toString();
//        LoginM loginM = loginService.findByEmail(email);
//        if (email != null) {
//            result.put("status", true);
//            result.put("data", loginM);
//            result.put("message","Call API lấy dữ liệu thành công !");
//            return ResponseEntity.ok(result);
//        } else {
//            result.put("status", false);
//            result.put("data", null);
//            return ResponseEntity.ok(result);
//        }
//    }
}
