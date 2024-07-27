package com.test.rest.business_Controller;


import java.util.AbstractMap.SimpleEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.test.rest.Model.User;
import com.test.rest.business_DB.PostgreSQL;

@Repository
public class PostgreBusinessLayer {

    @Autowired
    PostgreSQL postgreSQL;

    public ResponseEntity<?> getUserByLogin(String login){
        SimpleEntry<?, Integer> dbResponse = postgreSQL.selectUserByLogin(login);
        return new ResponseEntity<>(dbResponse.getKey(), HttpStatus.valueOf(dbResponse.getValue()));
    }

    public ResponseEntity<?> postUser(User user){
        SimpleEntry<?, Integer> dbResponse = postgreSQL.insertUser(user);
        return new ResponseEntity<>(dbResponse.getKey(), HttpStatus.valueOf(dbResponse.getValue()));
    }
}
