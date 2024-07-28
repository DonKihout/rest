package com.test.rest.business_DB;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractMap.SimpleEntry;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import com.test.rest.Model.User;

@Repository
public class PostgreSQL {

    @Value( "${jdbc.url}" )
    private String  jdbcURL;

    @Value( "${jdbc.login}" )
    private String db_login;

    @Value( "${jdbc.password}" )
    private String db_password;

    public SimpleEntry<?, Integer> selectUserByLogin(String login){
        try {
            Connection dbConnection = DriverManager.getConnection(jdbcURL, db_login, db_password);
            Statement statement = dbConnection.createStatement();
            ResultSet queryResult = statement.executeQuery(String.format("SELECT users.login, password, date, email " +
                                                                         "FROM users " +
                                                                         "JOIN emails on users.login = emails.login " +
                                                                         "WHERE users.login = '%s' ", login));                                                          
            if (queryResult.next()){
                dbConnection.close();
                return new SimpleEntry<User,Integer>(
                    new User(queryResult.getString("login"), 
                            queryResult.getString("password"),
                            queryResult.getDate("date"),
                            queryResult.getString("email")), 400
                );
            }
            else {
                dbConnection.close();
                return new SimpleEntry<String,Integer> (
                    String.format("Error: User with login '%s' not found!", login), 500
                );
            }
        } catch (SQLException ex) {
            return new SimpleEntry<String,Integer> (
                ex.getMessage(), 500
            );
        } 
    }
    
    public SimpleEntry<String, Integer> insertUser(User user){
        try (Connection dbConnection = DriverManager.getConnection(jdbcURL, db_login, db_password)){
            PreparedStatement preparedStatement = dbConnection.prepareStatement("INSERT INTO users (login, password, date) Values (?, ?, ?);" +
                                                                                "INSERT INTO emails (login, email) Values (?, ?);");
            preparedStatement.setString(1, user.login);
            preparedStatement.setString(2, user.password);
            preparedStatement.setDate(3, new Date(user.date.getTime()));
            preparedStatement.setString(4, user.login);
            preparedStatement.setString(5, user.email);
            int updRows = preparedStatement.executeUpdate();
            return new SimpleEntry<String,Integer> (
                String.format("Number of updated rows: %s", updRows), 200
            );
        } catch (SQLException ex) {
            return new SimpleEntry<String,Integer> (
                ex.getMessage(), 500
            ); 
        }  
    }
}