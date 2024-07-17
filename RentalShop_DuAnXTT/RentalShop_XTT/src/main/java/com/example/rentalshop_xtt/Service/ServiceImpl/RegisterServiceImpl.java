package com.example.rentalshop_xtt.Service.ServiceImpl;

import com.example.rentalshop_xtt.Data.Dto.AccountDTO;
import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Repository.RegisterRepo;
import com.example.rentalshop_xtt.Service.RegisterService;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    @Autowired
    RegisterRepo registerRepo;

    @Autowired
    private JavaMailSender emailSender;

    @Autowired
    HttpSession session;

    @Override
    public AccountE save(AccountDTO accountDTO) {
        AccountE accountE = AccountDTO.convertAccountDTOToAccountsE(accountDTO);
        accountE.setIsAdmin(false); // Set is_admin to 0
        return registerRepo.save(accountE);
    }

    @Override
    public boolean existsByEmail(String email) {
        AccountE accountE = registerRepo.existsByEmail(email);
        return accountE == null ? true : false;
    }

    @Override
    public boolean existsByUsername(String username) {
        AccountE accountE = registerRepo.existsByUsername(username);
        return accountE == null ? true : false;
    }

}
