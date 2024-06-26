package com.example.rentalshop_xtt.Data.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Table(name = "[user]")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false, unique = true, length = 63)
    private String username;

    @Column(name = "email", nullable = false, unique = true, length = 63)
    private String email;

    @Column(name = "is_admin")
    private boolean isAdmin = false;

    @Column(name = "is_disable")
    private boolean isDisable = false;

    @Column(name = "fullname", length = 63)
    private String fullname = "Your full name";

    @Column(name = "gender", length = 10)
    private String gender = "Male";

    @Column(name = "avatar", length = 63)
    private String avatar = "user.jpg";

    @Column(name = "dob")
    private Date dob = new Date();

    @Column(name = "description", length = 300)
    private String description;

    @Column(name = "job_title", length = 63)
    private String jobTitle;

    @Column(name = "role_id", nullable = false)
    private int roleId = 3;

    @Column(name = "role_name", nullable = false, length = 63)
    private String roleName = "User";

    @Column(name = "processed")
    private boolean processed = false;

    @Column(name = "sdt" , length = 15)
    private String sdt;

    @Column(name = "address", length = 255)
    private String address;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "account_id", referencedColumnName = "user_id")
    private AccountE account;

}
