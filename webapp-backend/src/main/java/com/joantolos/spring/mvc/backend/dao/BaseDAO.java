package com.joantolos.spring.mvc.backend.dao;

import org.springframework.beans.factory.annotation.Value;

import java.sql.*;

/**
 *  
 * Created by jtolos on 14/01/2015.
 */
public abstract class BaseDAO {
    
    public Connection con = null;

    @Value("${db.url}")
    private String url = "jdbc:mysql://localhost:3306/";
    @Value("${db.schema}")
    private String schema = "webapp_db_wc";
    @Value("${db.driver}")
    private String driver = "com.mysql.jdbc.Driver";
    @Value("${db.user}")
    private String user = "root";
    @Value("${db.password}")
    private String password = "1234";
    
    public BaseDAO(){
        try {
            if(this.con == null) {
                Class.forName(this.driver).newInstance();
                this.con = DriverManager.getConnection(url + schema, user, password);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param preparedStatement
     * @param resultSet
     */
    public void closeAll(PreparedStatement preparedStatement, ResultSet resultSet){
        try {
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
