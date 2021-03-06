package com.joantolos.business.frontend.service.impl;

import com.joantolos.business.common.entity.PingPong;
import com.joantolos.business.frontend.service.PingService;
import com.joantolos.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Service
public class PingServiceImpl implements PingService {

    @Value("${pong.service.url}")
    private String pongServiceUrl;
    
    @Autowired
    JSONUtils jsonUtils;
    
    @Override
    public PingPong ping() {
        RestTemplate rest = new RestTemplate();

        String pingPongJson = rest.postForObject(this.pongServiceUrl, null, String.class);
        PingPong pingPong = (PingPong)this.jsonUtils.jsonToObject(pingPongJson, PingPong.class);
        
        return pingPong;
    }
}
