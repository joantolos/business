package com.joantolos.spring.mvc.backend.service.impl;

import com.joantolos.spring.mvc.backend.service.PongService;
import com.joantolos.spring.mvc.common.entity.PingPong;
import org.springframework.stereotype.Service;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Service
public class PongServiceImpl implements PongService {
    
    @Override
    public PingPong pong() {
        //TODO go to database to retrieve Pong information
        PingPong response = new PingPong();
        response.setId(1);
        response.setMotion("PONG");

        return response;
    }
}
