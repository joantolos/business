package com.joantolos.business.common.entity;

public class Mail {
    private String to;
    private String subject;
    private String htmlContent;
    private byte[] attach;
    private String attachName;

    public String getAttachName() {
        return attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String text) {
        this.htmlContent = text;
    }

    public byte[] getAttach() {
        return attach;
    }

    public void setAttach(byte[] attach) {
        this.attach = attach;
    }

    public boolean hasAttach(){
        return this.getAttach()!=null&&this.getAttachName()!=null;
    }
}
