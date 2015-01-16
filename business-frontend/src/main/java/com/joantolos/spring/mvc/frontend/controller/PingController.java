package com.joantolos.spring.mvc.frontend.controller;

import com.joantolos.spring.mvc.frontend.service.PingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 *
 * Created by jtolos on 14/01/2015.
 */
@RestController
public class PingController {

    private Logger logger = LoggerFactory.getLogger(PingController.class);
    
    @Autowired
    PingService pingService;

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ModelAndView ping(final HttpServletRequest request) throws IOException {
        ModelAndView response = new ModelAndView("ping");
        response.addObject("pingResponse", this.pingService.ping().getMotion());

        return response;
    }
}
