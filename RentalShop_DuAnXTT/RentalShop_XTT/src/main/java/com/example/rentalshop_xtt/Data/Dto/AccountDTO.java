package com.example.rentalshop_xtt.Data.Dto;

import com.example.rentalshop_xtt.Data.Entity.AccountE;
import com.example.rentalshop_xtt.Utils.PasswordEncoderUtil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountDTO {
    private String username;
    private String email;
    private String hashedPassword ;
    private Boolean isAdmin;

    public void reset() {
        this.email = null;
        this.username = null;
        this.hashedPassword = null;
    }

    public static AccountE convertAccountDTOToAccountsE(AccountDTO accountDTO) {
        return AccountE.builder()
                .username(accountDTO.getUsername())
                .email(accountDTO.getEmail())
                .hashedPassword(PasswordEncoderUtil.encodePassword(accountDTO.getHashedPassword()))
                .isAdmin(accountDTO.getIsAdmin())
                .build();
    }
}
