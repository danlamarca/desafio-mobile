package com.prototipo_danilo.tasks.entities;

import java.util.AbstractMap;
import java.util.HashMap;

public class FullParameters {

    //atributos
    public String method;
    public String url;
    public String id;
    public AbstractMap<String,String> headerParameters; //mapa de valores onde: AbastractMap(chave,valor) - plano cartesiano
    public AbstractMap<String,String> parameters;

    //construtor
    public FullParameters(String method, String url, HashMap params, HashMap headerParams){
        this.method = method;
        this.url = url;
        this.headerParameters = headerParams;
        this.parameters = params;
    }

    public FullParameters(String method, String url, String id){
        this.method = method;
        this.url = url;
        this.id = id;
    }
}
