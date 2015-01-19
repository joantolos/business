package com.joantolos.business.api.ct.server;

import com.joantolos.business.api.ct.entity.PingPongInput;
import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.business.common.exception.DAOException;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElement;

/**
 *
 * Created by jtolos on 19/1/15.
 */
@WebService
public interface BusinessAPISoap {

    @WebMethod
    @WebResult(name = "PingPongOutput")
    public PingPongOutput playPing(@WebParam(name = "PingPongInput") @XmlElement(required = true) PingPongInput input) throws DAOException;
}
