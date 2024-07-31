package com.test.rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.AbstractMap.SimpleEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import com.test.rest.Model.User;
import com.test.rest.business_DB.PostgreSQL;

import jakarta.validation.Valid;


@RestController
public class TestController {

    @Autowired
    PostgreSQL postgreSQL;

    @GetMapping(value = "getUser/{login}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable String login){
      try {
        Thread.sleep(2500);
        SimpleEntry<?, Integer> dbResponse = postgreSQL.selectUserByLogin(login);
        return new ResponseEntity<>(dbResponse.getKey(), HttpStatus.valueOf(dbResponse.getValue()));        
      } catch (Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.valueOf(500));
      }     
    }

    @PostMapping(value = "postUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> postUser(@Valid @RequestBody User user, BindingResult bindingResult){
      try {
        if (bindingResult.hasErrors()) return new ResponseEntity<>("Incorrect user data!",HttpStatus.valueOf(400));
        Thread.sleep(2000);
        SimpleEntry<?, Integer> dbResponse = postgreSQL.insertUser(user);
        return new ResponseEntity<>(dbResponse.getKey(), HttpStatus.valueOf(dbResponse.getValue()));
      } catch (Exception ex) {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.valueOf(500));
      }    
    }

}