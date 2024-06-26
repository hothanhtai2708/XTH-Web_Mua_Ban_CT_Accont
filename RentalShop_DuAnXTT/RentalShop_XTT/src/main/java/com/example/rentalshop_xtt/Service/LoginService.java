package com.example.rentalshop_xtt.Service;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Data.Model.LoginM;

public interface LoginService {
    LoginM findByEmail(String email);
}
