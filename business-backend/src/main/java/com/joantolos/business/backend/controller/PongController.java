package com.joantolos.business.backend.controller;

import com.joantolos.business.backend.service.PongService;
import com.joantolos.business.common.exception.DAOException;
import com.joantolos.utils.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@RestController
public class PongController {

    @Autowired
    PongService pongService;
    
    @Autowired
    JSONUtils jsonUtils;

    @RequestMapping(value = "/pong", method = RequestMethod.POST)
    @ResponseBody
    public String pong() throws DAOException {
        return this.jsonUtils.objectToJson(pongService.pong());
    }
}
