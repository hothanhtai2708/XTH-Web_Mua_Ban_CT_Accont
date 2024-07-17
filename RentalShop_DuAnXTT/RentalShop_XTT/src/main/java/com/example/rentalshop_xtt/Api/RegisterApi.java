package com.example.rentalshop_xtt.Api;

import com.example.rentalshop_xtt.Data.Dto.AccountDTO;
import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Service.ServiceImpl.MailerServiceImpl;
import com.example.rentalshop_xtt.Service.ServiceImpl.RegisterServiceImpl;
import com.example.rentalshop_xtt.Utils.PasswordEncoderUtil;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
public class RegisterApi {
    @Autowired
    RegisterServiceImpl registerService;

    @Autowired
    MailerServiceImpl mailerService;

    @Autowired
    HttpSession httpSession;

    @PostMapping("/register")
    public ResponseEntity<?> register(@ModelAttribute AccountDTO accountDTO,
                                      @RequestParam("password") String password,
                                      @RequestParam("confirmPassword") String confirmPassword) {
        Map<String, Object> result = new HashMap<>();
        try {
            boolean checkExistEmail = !registerService.existsByEmail(accountDTO.getEmail());
            boolean checkExistUsername = !registerService.existsByUsername(accountDTO.getUsername());

            if (checkExistEmail) {
                result.put("success", false);
                result.put("message", "Email đã tồn tại!");
                return ResponseEntity.badRequest().body(result);
            } else if (checkExistUsername) {
                result.put("success", false);
                result.put("message", "Username đã tồn tại!");
                return ResponseEntity.badRequest().body(result);
            } else if (password == null || password.length() < 3 || password.length() > 16) {
                result.put("success", false);
                result.put("message", "Password không hợp lệ!");
                return ResponseEntity.badRequest().body(result);
            } else if (confirmPassword == null || confirmPassword.length() < 3 || confirmPassword.length() > 16) {
                result.put("success", false);
                result.put("message", "Password nhập lại không hợp lệ!");
                return ResponseEntity.badRequest().body(result);
            } else if (!password.equals(confirmPassword)) {
                result.put("success", false);
                result.put("message", "Password không giống nhau!");
                return ResponseEntity.badRequest().body(result);
            } else {
                // Lưu tạm thời tài khoản vào session
                httpSession.setAttribute("tempAccount", accountDTO);
                httpSession.setAttribute("tempPassword", confirmPassword);

                String Otp = generateOtp();
                httpSession.setAttribute("otp", Otp);
                httpSession.setAttribute("account", accountDTO);
                mailerService.sendOtpEmail(accountDTO.getEmail(), Otp);
                // Hash mật khẩu
                String hashedPassword = PasswordEncoderUtil.encodePassword(confirmPassword);
                accountDTO.setHashedPassword(hashedPassword);

                result.put("success", true);
                result.put("message", "OTP đã được gửi!");
                result.put("data", accountDTO);
                return ResponseEntity.ok(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call api thất bại");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    @PostMapping("/confirm-register")
    public ResponseEntity<?> confirmRegister(@RequestParam("otp") String confirmotp) {
        Map<String, Object> result = new HashMap<>();
        try {
            String OtpServer = (String) httpSession.getAttribute("otp");
            AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("tempAccount");
            String password = (String) httpSession.getAttribute("tempPassword");

            if (confirmotp.equals(OtpServer)) {
                // Hash mật khẩu
                String hashedPassword = PasswordEncoderUtil.encodePassword(password);
                accountDTO.setHashedPassword(hashedPassword);

                // Lưu tài khoản vào cơ sở dữ liệu
                AccountE accountE = registerService.save(accountDTO);

                // Xóa các session đã lưu
                httpSession.removeAttribute("otp");
                httpSession.removeAttribute("tempAccount");
                httpSession.removeAttribute("tempPassword");

                result.put("success", true);
                result.put("message", "Đăng ký thành công");
                result.put("data", accountE);
                return ResponseEntity.ok(result);
            } else {
                result.put("success", false);
                result.put("message", "Mã OTP không chính xác!");
                return ResponseEntity.badRequest().body(result);
            }
        } catch (Exception e) {
            result.put("success", false);
            result.put("message", "Call api thất bại");
            result.put("data", null);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(result);
        }
    }

    private String generateOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }
}

//public class RegisterApi {
//    @Autowired
//    RegisterServiceImpl registerService;
//
//    @Autowired
//    MailerServiceImpl mailerService;
//
//    @Autowired
//    HttpSession httpSession;
//
//    @PostMapping("/register")
//    public ResponseEntity<?> register(@ModelAttribute AccountDTO accountDTO,
//                                      @RequestParam("password") String password,
//                                      @RequestParam("passwords") String passwords,
//                                      Model model) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            // Xóa dữ liệu bằng cách gọi phương thức reset
//            boolean checkExistEmail = !registerService.existsByEmail(accountDTO.getEmail());
//            boolean checkExistUsername = !registerService.existsByUsername(accountDTO.getUsername());
//
//            if (checkExistEmail) {
//                result.put("success", false);
//                result.put("message", "Email đã tồn tại!");
//                result.put("data", null);
//                model.addAttribute("error", "Email đã tồn tại!");
//                return ResponseEntity.badRequest().body(result);
//            } else if (checkExistUsername) {
//                result.put("success", false);
//                result.put("message", "Username đã tồn tại!");
//                result.put("data", null);
//                model.addAttribute("error", "Username đã tồn tại!");
//                return ResponseEntity.badRequest().body(result);
//            } else if (password == null || password.length() < 3 || password.length() > 16) {
//                result.put("success", false);
//                result.put("message", "Password không hợp lệ!");
//                result.put("data", null);
//                model.addAttribute("error", "Password không hợp lệ!");
//                return ResponseEntity.badRequest().body(result);
//            } else if (passwords == null || passwords.length() < 3 || passwords.length() > 16) {
//                result.put("success", false);
//                result.put("message", "Password nhập lại không hợp lệ!");
//                result.put("data", null);
//                model.addAttribute("error", "Password nhập lại không hợp lệ!");
//                return ResponseEntity.badRequest().body(result);
//            } else if (!password.equals(passwords)) {
//                result.put("success", false);
//                result.put("message", "Password không giống nhau!");
//                result.put("data", null);
//                model.addAttribute("error", "Password không giống nhau!");
//                return ResponseEntity.badRequest().body(result);
//            } else {
//                // Lưu tạm thời tài khoản vào session
//                httpSession.setAttribute("tempAccount", accountDTO);
//                httpSession.setAttribute("tempPassword", passwords);
//
//                String Otp = generateOtp();
//                httpSession.setAttribute("otp", Otp);
//                httpSession.setAttribute("account", accountDTO);
//                mailerService.sendOtpEmail(accountDTO.getEmail(), Otp);
//                // Hash the password
//                String hashedPassword = PasswordEncoderUtil.encodePassword(passwords);
//                accountDTO.setHashedPassword(hashedPassword);
//                result.put("success", true);
//                result.put("message", "OTP đã được gửi!");
//                result.put("data", null);
//
//                return ResponseEntity.ok(result);
//            }
//        } catch (Exception e) {
//            log.error("Call api thất bại: /register ", e);
//            result.put("success", false);
//            result.put("message", "Call api thất bại");
//            result.put("data", null);
//            model.addAttribute("error", "Call api thất bại");
//            return ResponseEntity.badRequest().body(result);
//        }
//    }
//
//
//    @PostMapping("/confirm-register")
//    public ResponseEntity<?> confirmRegister(@RequestParam("otp1") String confirm1,
//                                        @RequestParam("otp2") String confirm2,
//                                        @RequestParam("otp3") String confirm3,
//                                        @RequestParam("otp4") String confirm4,
//                                        @RequestParam("otp5") String confirm5,
//                                        @RequestParam("otp6") String confirm6,
//                                        Model model) {
//        Map<String, Object> result = new HashMap<>();
//        try {
//            model.addAttribute("success", "OTP đã được gửi tới email của bạn.");
//            String Otp = confirm1 + confirm2 + confirm3 + confirm4 + confirm5 + confirm6;
//            String OtpServer = (String) httpSession.getAttribute("otp");
//            AccountDTO accountDTO = (AccountDTO) httpSession.getAttribute("tempAccount");
//            String password = (String) httpSession.getAttribute("tempPassword");
//
//            if (Otp.equals(OtpServer)) {
//                // Hash mật khẩu
//                String hashedPassword = PasswordEncoderUtil.encodePassword(password);
//                accountDTO.setHashedPassword(hashedPassword);
//
//                // Lưu tài khoản vào cơ sở dữ liệu
//                AccountE accountE = registerService.save(accountDTO);
//
//                // Xóa các session đã lưu
//                httpSession.removeAttribute("otp");
//                httpSession.removeAttribute("tempAccount");
//                httpSession.removeAttribute("tempPassword");
//
//                result.put("success", true);
//                result.put("message", "Đăng ký thành công");
//                result.put("data", accountE);
//                model.addAttribute("message", "Bạn đã đăng ký tài khoản thành công!");
//                return ResponseEntity.ok(result);
//            } else {
//                result.put("success", false);
//                result.put("message", "Mã OTP không chính xác!");
//                model.addAttribute("message", "Mã OTP không chính xác!");
//                return ResponseEntity.badRequest().body(result);
//            }
//        } catch (Exception e) {
//            log.error("Call api thất bại: confirm-register ", e);
//            result.put("success", false);
//            result.put("message", "Call api thất bại");
//            result.put("data", null);
//            return ResponseEntity.badRequest().body(result);
//        }
//    }
//
//    private String generateOtp() {
//        Random random = new Random();
//        int otp = 100000 + random.nextInt(900000);
//        return String.valueOf(otp);
//    }
//
//}
