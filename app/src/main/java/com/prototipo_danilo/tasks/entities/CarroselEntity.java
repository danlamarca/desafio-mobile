package com.prototipo_danilo.tasks.entities;

import java.util.ArrayList;

public class CarroselEntity {
    private float Size;
    private float Offset;
    private float Total;
    private float Delay;
    public ArrayList< Object > Products = new ArrayList < Object > ();
    private String ApiQuery;


    // Getter Methods

    public float getSize() {
        return Size;
    }

    public float getOffset() {
        return Offset;
    }

    public float getTotal() {
        return Total;
    }

    public float getDelay() {
        return Delay;
    }

    public String getApiQuery() {
        return ApiQuery;
    }

    // Setter Methods

    public void setSize(float Size) {
        this.Size = Size;
    }

    public void setOffset(float Offset) {
        this.Offset = Offset;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public void setDelay(float Delay) {
        this.Delay = Delay;
    }

    public void setApiQuery(String ApiQuery) {
        this.ApiQuery = ApiQuery;
    }

}
