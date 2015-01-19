package com.joantolos.business.backend.dao;

import com.joantolos.business.common.exception.DAOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
public interface PingPongDAO {
    
    public String playPing() throws DAOException;
}
