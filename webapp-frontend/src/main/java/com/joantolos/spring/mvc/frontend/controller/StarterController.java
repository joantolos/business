package com.joantolos.spring.mvc.frontend.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by u6019943 on 13/01/2015.
 */
@Controller
public class StarterController {
    private Logger logger = LoggerFactory.getLogger(StarterController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView getRoot(final HttpServletRequest request) throws IOException {

        return new ModelAndView("index");
    }
}
