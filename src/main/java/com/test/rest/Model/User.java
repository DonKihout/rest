package com.test.rest.Model;

import java.sql.Date;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class User {

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{2,30}$")
    public String login;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$")
    public String password;

    @NotNull
    public Date date;
    
    @NotNull
    @Email
    public String email;

    public User(String login,String password, Date date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
    }

    public String toString(){
        return String.format("\tLogin: %s\n" +
                            "\tPassword: %s\n" +
                            "\tDate: %s\n" +
                            "\tEmail: %s", login, password, date, email);
    }

}
