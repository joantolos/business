package com.joantolos.business.backend.service;

import com.joantolos.business.common.entity.PingPong;
import com.joantolos.business.common.exception.DAOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
public interface PongService {
    
    public PingPong pong() throws DAOException;
}
