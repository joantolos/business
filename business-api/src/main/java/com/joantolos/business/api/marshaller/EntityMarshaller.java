package com.joantolos.business.api.marshaller;

import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.business.common.entity.PingPong;

/**
 * Created by joan on 19/1/15.
 */
public interface EntityMarshaller {

    public PingPongOutput marshallPingPong(PingPong pingPong);
}
