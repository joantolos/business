package com.joantolos.business.common.entity;

/**
 * PingPong entity containing the kind of motion
 * (ping or pong) and the id
 * Created by jtolos on 14/01/2015.
 */
public class PingPong {
    private String motion;
    private int id;

    public String getMotion() {
        return motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
