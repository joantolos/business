package com.joantolos.spring.mvc.common.entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * PingPong entity containing the kind of motion
 * (ping or pong) and the id
 * Created by jtolos on 14/01/2015.
 */
@XmlRootElement(name = "PingPong")
public class PingPong {
    private String motion;
    private int id;

    @XmlAttribute
    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    @XmlAttribute
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
