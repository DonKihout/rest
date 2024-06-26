package com.test.rest.Controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RestController;
import com.test.rest.Model.TestModel;


@RestController
public class TestController {
    
    @GetMapping(value = "getTest", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Object getTest() {
      Map<String, Object> object = new HashMap<>();
      object.put("name", "zaglushka");
      return object;
    }

    @PostMapping(value = "postTest", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Object postTest(@RequestBody TestModel testModel) {
      Map<String, String> object = new HashMap<>();
      object.put("login", testModel.login);
      object.put("password", testModel.password);
      object.put("currentTime", new SimpleDateFormat("dd-MM-yyyy").format(new Date()));
      return object;
    }

}