package com.joantolos.business.api.marshaller.impl;

import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.business.api.marshaller.EntityMarshaller;
import com.joantolos.spring.mvc.common.entity.PingPong;
import org.springframework.stereotype.Component;

/**
 *
 * Created by jtolos on 19/1/15.
 */
@Component
public class EntityMarshallerImpl implements EntityMarshaller {

    @Override
    public PingPongOutput marshallPingPong(PingPong pingPong) {
        PingPongOutput pingPongOutput = new PingPongOutput();
        pingPongOutput.setMotion(pingPong.getMotion());
        return pingPongOutput;
    }
}
