package com.joantolos.spring.mvc.backend.service.impl;

import com.joantolos.spring.mvc.backend.service.PongService;
import com.joantolos.spring.mvc.common.entity.PingPong;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Service
public class PongServiceImpl implements PongService {
    
    @Override
    public PingPong pong() {
        PingPong response = new PingPong();
        response.setId(1);

        String url = "jdbc:mysql://localhost:3306/"; 
        String dbName = "webapp_db_wc";
        String driver = "com.mysql.jdbc.Driver"; 
        String userName = "root"; 
        String password = "Mrfalken3";
        
        try { 
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url + dbName, userName, password);
            PreparedStatement preparedStatement = conn.prepareStatement("select motion from pingpong where id = 3");
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                response.setMotion(resultSet.getString("motion"));
            }
            conn.close(); 
        } catch (Exception e) {
            e.printStackTrace(); 
        }
        
        return response;
    }
}
