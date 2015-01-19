package com.joantolos.business.backend.dao;

import com.joantolos.business.common.exception.DAOException;
import com.joantolos.business.common.security.Decrypter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.sql.*;

/**
 *  
 * Created by jtolos on 14/01/2015.
 */
public abstract class BaseDAO {
    
    public Connection con = null;

    @Value("${db.url}")
    private String url;
    @Value("${db.schema}")
    private String schema;
    @Value("${db.driver}")
    private String driver;
    @Value("${db.user}")
    private String user;
    @Value("${db.password}")
    private String password;
    
    @Autowired
    private Decrypter decrypter;
    
    public BaseDAO() {

    }
    
    @PostConstruct
    public void init() throws DAOException {
        try {
            if(this.con == null) {
                Class.forName(this.driver).newInstance();
                this.con = DriverManager.getConnection(url + schema, user, this.decrypter.decrypt(password));
            }
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }

    /**
     *
     * @param preparedStatement
     * @param resultSet
     */
    public void closeAll(PreparedStatement preparedStatement, ResultSet resultSet) throws DAOException {
        try {
            if(preparedStatement!=null){
                preparedStatement.close();
            }
            if(resultSet!=null){
                resultSet.close();
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        }
    }
}
