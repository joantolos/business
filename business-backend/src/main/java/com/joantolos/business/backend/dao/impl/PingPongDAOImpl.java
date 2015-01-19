package com.joantolos.business.backend.dao.impl;

import com.joantolos.business.backend.dao.BaseDAO;
import com.joantolos.business.backend.dao.PingPongDAO;
import com.joantolos.business.common.exception.DAOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

/**
 *  
 * Created by jtolos on 14/01/2015.
 */
@Component
public class PingPongDAOImpl extends BaseDAO implements PingPongDAO {

    @Value("${sql.select.playPing}")
    private String selectPlayPing;

    public PingPongDAOImpl() throws DAOException {
        
    }

    public String playPing() throws DAOException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        
        try {
            preparedStatement = super.con.prepareStatement(selectPlayPing.replace("#1", this.randomMotionId()));
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                return resultSet.getString("motion");
            }
        } catch (SQLException e) {
            throw new DAOException(e.getMessage());
        } finally {
            super.closeAll(preparedStatement, resultSet);
        }

        return "TILT";
    }

    /**
     * Creates a random number between 1 and 3 and returns it
     * as String
     * @return
     */
    private String randomMotionId(){
        return String.valueOf(new Random().nextInt(3 - 1 + 1) + 1);
    }
}
