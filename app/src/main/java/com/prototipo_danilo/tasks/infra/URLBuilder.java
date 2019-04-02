package com.prototipo_danilo.tasks.infra;

public class URLBuilder {

    private String url;

    public URLBuilder(String mainURL){
        this.url = mainURL;
    }

    public void addResource(String value){
        this.url += "/" + value;
    }

    public String getURL(){
        return this.url;
    }
}
