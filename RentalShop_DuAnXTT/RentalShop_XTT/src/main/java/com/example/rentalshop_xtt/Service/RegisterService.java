package com.example.rentalshop_xtt.Service;

import com.example.rentalshop_xtt.Data.Dto.AccountDTO;
import com.example.rentalshop_xtt.Data.Entity.AccountE;

public interface RegisterService {
    AccountE save(AccountDTO accountDTO);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
