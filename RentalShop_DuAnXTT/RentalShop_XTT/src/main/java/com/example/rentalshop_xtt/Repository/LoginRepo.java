package com.example.rentalshop_xtt.Repository;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepo extends JpaRepository<AccountE , Integer> {
    AccountE findByEmail(String email);
}
