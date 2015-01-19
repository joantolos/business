package com.joantolos.business.api.ct.server;

import com.joantolos.business.api.ct.entity.PingPongOutput;
import com.joantolos.spring.mvc.common.exception.DAOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * Created by jtolos on 19/1/15.
 */
@Path("/")
public interface BusinessAPIRest {

    @GET
    @Path("/playPing")
    @Produces({"text/xml"})
    public PingPongOutput playPing(@QueryParam("motion") String motion) throws DAOException;

}
