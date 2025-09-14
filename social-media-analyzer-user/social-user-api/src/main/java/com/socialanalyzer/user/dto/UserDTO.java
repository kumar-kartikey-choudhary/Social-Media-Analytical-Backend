package com.socialanalyzer.user.dto;

import com.fasterxml.jackson.databind.jsontype.impl.StdTypeResolverBuilder;
import com.socialanalyzer.enums.Role;
import lombok.Data;

@Data
public class UserDTO {

    private String uuid;
    private String firstName;
    private String middleName;
    private  String lastName;
    private String username;
    private String email;
    private Role role;
    private String password;

}
