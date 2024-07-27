package com.test.rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import com.test.rest.Model.TestModel;
import com.test.rest.Model.User;
import com.test.rest.business_Controller.PostgreBusinessLayer;
import jakarta.validation.Valid;


@RestController
public class TestController {

    @Autowired
    PostgreBusinessLayer PostgreBusinessLayer;
  
    @GetMapping(value = "getTest", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> getTest() throws InterruptedException {
      Thread.sleep(2500);
      return new ResponseEntity<String>("{\"name\" : \"zaglushka\"}", HttpStatus.OK);
    }

    @PostMapping(value = "postTest", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TestModel> postTest(@Valid @RequestBody TestModel testModel, BindingResult bindingResult) throws InterruptedException {
      Thread.sleep(2000);
      if (bindingResult.hasErrors()) return new ResponseEntity<TestModel>(HttpStatus.valueOf(400));
      return new ResponseEntity<TestModel>(testModel, HttpStatus.OK);  
    }

    @GetMapping(value = "getUser/{login}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> getUser(@PathVariable String login){
      try {
        Thread.sleep(2500);
        return PostgreBusinessLayer.getUserByLogin(login);
      } catch (InterruptedException ex) {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.valueOf(500));
      }     
    }

    @PostMapping(value = "postUser", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> postUser(@Valid @RequestBody User user, BindingResult bindingResult){
      try {
        if (bindingResult.hasErrors()) return new ResponseEntity<>("Incorrect user data!",HttpStatus.valueOf(400));
        Thread.sleep(2000);
        return PostgreBusinessLayer.postUser(user);
      } catch (InterruptedException ex) {
        return new ResponseEntity<String>(ex.getMessage(),HttpStatus.valueOf(500));
      }    
    }

}