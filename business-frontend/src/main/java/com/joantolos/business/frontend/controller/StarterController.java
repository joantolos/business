package com.joantolos.business.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

/**
 * Starter Controller just for PING the front end App so it's alive and the Spring context
 * is correctly setted.
 * Created by jtolos on 13/01/2015.
 */
@Controller
public class StarterController {
    private Logger logger = LoggerFactory.getLogger(StarterController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getRoot() throws IOException {

        return new ModelAndView("index");
    }
}
