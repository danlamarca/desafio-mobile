package com.prototipo_danilo.tasks.entities;

public class APIResponse {

    public String json;
    public int statusCode;

    public APIResponse(String json, int StatusCode){
        this.json = json;
        this.statusCode = StatusCode;
    }
}
