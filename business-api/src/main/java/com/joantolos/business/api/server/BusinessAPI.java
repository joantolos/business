package com.joantolos.business.api.server;

import com.joantolos.business.api.ct.entity.PingPongInput;
import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.business.api.ct.server.BusinessAPISoap;
import com.joantolos.business.api.ct.server.BusinessAPIRest;
import com.joantolos.business.api.client.PlayPingClient;
import com.joantolos.spring.mvc.common.exception.DAOException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * Created by jtolos on 19/1/15.
 */
@WebService(endpointInterface = "com.joantolos.business.api.ct.server.BusinessAPISoap")
public class BusinessAPI implements BusinessAPISoap, BusinessAPIRest {

    @Autowired
    PlayPingClient playPingClient;

    @Override
    public PingPongOutput playPing(String motion) throws DAOException {
        PingPongInput pingPongInput = new PingPongInput();
        pingPongInput.setId(1);
        pingPongInput.setMotion(motion);

        return this.playPing(pingPongInput);
    }

    @Override
    public PingPongOutput playPing(@XmlElement(required = true) PingPongInput input) throws DAOException {
        return this.playPingClient.playPing(input);
    }


}
