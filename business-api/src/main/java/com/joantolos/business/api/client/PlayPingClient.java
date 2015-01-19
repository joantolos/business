package com.joantolos.business.api.client;

import com.joantolos.business.api.ct.entity.PingPongInput;
import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.spring.mvc.common.exception.DAOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
public interface PlayPingClient {
    
    public PingPongOutput playPing(PingPongInput pingPongInput) throws DAOException;
}
