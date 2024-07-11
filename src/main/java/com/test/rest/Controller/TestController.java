package com.test.rest.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RestController;
import com.test.rest.Model.TestModel;
import jakarta.validation.Valid;


@RestController
public class TestController {

  
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

}