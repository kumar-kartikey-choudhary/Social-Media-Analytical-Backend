package com.socialanalyzer.user.model;

import com.socialanalyzer.enums.Role;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;


@Entity
@Data
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID" ,columnDefinition = "CHAR(50) NOT NULL DEFAULT(UUID())" , nullable = false)
    @NotNull
    private String uuid;

    @NotNull(message = "First name can not be null...")
    @Column(name = "FIRST_NAME", columnDefinition = "CHAR(20) NOT NULL " , nullable = false)
    private String firstName;

    @Column(name = "MIDDLE_NAME" , columnDefinition = "CHAR(20) NOT NULL")
    private String middleName;

    @NotNull(message = "Last name can not be null...")
    @Column(name = "LAST_NAME" , columnDefinition = "CHAR(20) NOT NULL " , nullable = false)
    private String lastName;


    @NotNull
    @Column(name = "USERNAME", columnDefinition = "CHAR(20) NOT NULL")
    private String username;

    @NotNull
    @Email
    @Pattern(regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]" , message = "Email must be valid..")
    @Column(name = "EMAIL", columnDefinition = "VARCHAR(50) NOT NULL")
    private String email;


    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", columnDefinition = "VARCHAR(20) NOT NULL")
    private Role role;

    @NotNull
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\\\S+$).{8,20}$",
                         message =" Must contain - at least one digit " +
                                 "at least one lowercase" +
                                 "at least one uppercase" +
                                 "at least one special char" +
                                 "minimum 8 characters")
    @Column(name = "PASSWORD" , columnDefinition = "VARCHAR(20) NOT NULL" , nullable = false)
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(()->"Role" + role);
    }
}
