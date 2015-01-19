package com.joantolos.business.backend.service.impl;

import com.joantolos.business.backend.dao.PingPongDAO;
import com.joantolos.business.backend.service.PongService;
import com.joantolos.business.common.entity.PingPong;
import com.joantolos.business.common.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Service
public class PongServiceImpl implements PongService {
    
    @Autowired
    PingPongDAO pingPongDAO;
    
    @Override
    public PingPong pong() throws DAOException {
        PingPong response = new PingPong();
        response.setId(1);
        response.setMotion(this.pingPongDAO.playPing());

        return response;
    }
}
