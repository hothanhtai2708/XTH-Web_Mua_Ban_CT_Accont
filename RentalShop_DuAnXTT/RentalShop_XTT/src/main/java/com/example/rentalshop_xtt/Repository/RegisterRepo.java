package com.example.rentalshop_xtt.Repository;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterRepo extends JpaRepository<AccountE, Integer> {
    AccountE findByEmail(String email);

    AccountE save(AccountE account);

    @Query("select a from AccountE a where a.email = :email")
    AccountE existsByEmail(@Param("email") String email);

    @Query("select a from AccountE a where a.username = :username")
    AccountE existsByUsername(@Param("username") String username);

}
