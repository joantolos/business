package com.joantolos.spring.mvc.api.services.impl;

import com.joantolos.spring.mvc.api.services.PlayPingService;
import com.joantolos.spring.mvc.common.entity.PingPong;
import com.joantolos.spring.mvc.common.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Service
public class PlayPingServiceImpl implements PlayPingService {

    @Value("${pong.service.url}")
    private String pongServiceUrl;
    
    @Autowired
    JSONUtils jsonUtils;
    
    @Override
    public PingPong playPing() {
        RestTemplate rest = new RestTemplate();

        String pingPongJson = rest.postForObject(this.pongServiceUrl, null, String.class);
        PingPong pingPong = (PingPong)this.jsonUtils.jsonToObject(pingPongJson, PingPong.class);
        
        return pingPong;
    }
}
