package com.joantolos.business.api.ct.entity;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlRootElement(name = "PingPongOutput")
@XmlType(name="PingPongOutput",propOrder={"motion"})
public class PingPongOutput implements Serializable {
    String motion;

    public PingPongOutput() {

    }

    @XmlElement(name="motion")
    public String getMotion() {
        return this.motion;
    }

    public void setMotion(String motion) {
        this.motion = motion;
    }
}
