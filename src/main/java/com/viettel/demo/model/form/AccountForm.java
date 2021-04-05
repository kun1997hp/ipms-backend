package com.viettel.demo.model.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

public class AccountForm {
    @Email(message = "Email address is invalid.")
    private String email;
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
            message = "Passwords must contain at least eight characters, including at least 1 letter and 1 number.")
    private String password;
}
