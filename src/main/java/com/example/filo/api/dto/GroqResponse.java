package com.example.filo.api.dto;

public class GroqResponse {
    private String reply;

    public GroqResponse() {}

    public GroqResponse(String reply) {
        this.reply = reply;
    }

    public String getReply() {
        return reply;
    }

    public void setReply(String reply) {
        this.reply = reply;
    }
}
