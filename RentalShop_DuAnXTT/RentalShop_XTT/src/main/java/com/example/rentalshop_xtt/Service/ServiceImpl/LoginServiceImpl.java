package com.example.rentalshop_xtt.Service.ServiceImpl;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Data.Model.LoginM;
import com.example.rentalshop_xtt.Repository.LoginRepo;
import com.example.rentalshop_xtt.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginRepo loginRepo;

    @Override
    public LoginM findByEmail(String email) {
        AccountE accountE = loginRepo.findByEmail(email);
        return LoginM.convertAccountEToLoginM(accountE);
    }
}
