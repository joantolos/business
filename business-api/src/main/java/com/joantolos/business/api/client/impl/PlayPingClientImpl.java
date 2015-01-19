package com.joantolos.business.api.client.impl;

import com.joantolos.business.api.ct.entity.PingPongInput;
import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.business.api.marshaller.EntityMarshaller;
import com.joantolos.business.api.client.PlayPingClient;
import com.joantolos.spring.mvc.common.entity.PingPong;
import com.joantolos.spring.mvc.common.exception.DAOException;
import com.joantolos.spring.mvc.common.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@Component
public class PlayPingClientImpl implements PlayPingClient {
    
    @Autowired
    JSONUtils jsonUtils;

    @Autowired
    EntityMarshaller entityMarshaller;

    @Value("${pong.service.url}")
    private String pongServiceUrl;
    
    @Override
    public PingPongOutput playPing(PingPongInput pingPongInput) throws DAOException {
        RestTemplate rest = new RestTemplate();

        String pingPongJson = rest.postForObject(this.pongServiceUrl, null, String.class);
        PingPong pingPong = (PingPong)this.jsonUtils.jsonToObject(pingPongJson, PingPong.class);

        return this.entityMarshaller.marshallPingPong(pingPong);
    }
}
