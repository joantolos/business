package com.joantolos.spring.mvc.backend.service;

import com.joantolos.spring.mvc.common.entity.PingPong;
import com.joantolos.spring.mvc.common.exception.DAOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
public interface PongService {
    
    public PingPong pong() throws DAOException;
}
