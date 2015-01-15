package com.joantolos.spring.mvc.api.entry.impl;

import com.joantolos.spring.mvc.api.entry.WebappAPIEntry;
import com.joantolos.spring.mvc.api.services.PlayPingService;
import com.joantolos.spring.mvc.common.entity.PingPong;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * Created by jtolos on 15/01/2015.
 */
public class WebappAPIEntryImpl implements WebappAPIEntry {
    
    @Autowired
    PlayPingService playPingService;
    
    @Override
    public PingPong playPing(String motion) {
        
        return this.playPingService.playPing();
    }
}
