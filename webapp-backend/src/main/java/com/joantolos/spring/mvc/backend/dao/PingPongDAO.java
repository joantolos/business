package com.joantolos.spring.mvc.backend.dao;

import com.joantolos.spring.mvc.common.exception.DAOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
public interface PingPongDAO {
    
    public String playPing() throws DAOException;
}
