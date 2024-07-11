package com.test.rest.Model;

import java.text.SimpleDateFormat;
import java.util.Date;
import jakarta.validation.constraints.Pattern;

public class TestModel {

    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z]).{2,30}$")
    public String login;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$")
    public String password;

    public String currentDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
}
