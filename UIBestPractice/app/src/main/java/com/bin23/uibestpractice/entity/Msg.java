package com.bin23.uibestpractice.entity;

public class Msg {
    private static final Integer TYPE_RECEIVED = 0;
    private static final Integer TYPE_SENT = 1;

    private String content;
    private Integer type;

    public Msg() {
    }

    public Msg(String content, Integer type) {
        this.content = content;
        this.type = type;
    }

    public static Integer getTypeReceived() {
        return TYPE_RECEIVED;
    }

    public static Integer getTypeSent() {
        return TYPE_SENT;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }


}
