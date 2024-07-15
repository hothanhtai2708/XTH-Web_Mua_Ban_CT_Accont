package com.example.rentalshop_xtt.Data.Model;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginM {
    private String name;
    private String email;
    private String password;
    private Boolean admin;

    public static LoginM convertAccountEToLoginM(AccountE accountE){
        return LoginM.builder()
                .name(accountE.getUsername())
                .email(accountE.getEmail())
                .password(accountE.getHashedPassword())
                .admin(accountE.getIsAdmin())
                .build();
    }
}
