package com.example.rentalshop_xtt.Service;

import com.example.rentalshop_xtt.Data.Entity.AccountE;

public interface LoginService {
    AccountE findByEmail(String email);
}
