package com.joantolos.spring.mvc.api.entry;

import com.joantolos.spring.mvc.common.entity.PingPong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

/**
 *
 * Created by jtolos on 15/01/2015.
 */
@Path("/")
public interface WebappAPIEntry {

    @GET
    @Path("/playPing")
    @Produces({ "text/xml" })
    public PingPong playPing(@QueryParam("motion")String motion);

}
