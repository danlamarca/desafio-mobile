package com.prototipo_danilo.tasks.entities;

public class BestInstallment {
    private float Count;
    private float Value;
    private float Total;
    private float Rate;
    private String RealId;


    // Getter Methods

    public float getCount() {
        return Count;
    }

    public float getValue() {
        return Value;
    }

    public float getTotal() {
        return Total;
    }

    public float getRate() {
        return Rate;
    }

    // Setter Methods

    public void setCount(float Count) {
        this.Count = Count;
    }

    public void setValue(float Value) {
        this.Value = Value;
    }

    public void setTotal(float Total) {
        this.Total = Total;
    }

    public void setRate(float Rate) {
        this.Rate = Rate;
    }

    public String getRealId() {
        return this.RealId;
    }

    public void setRealId(String realIdProduto) {
        this.RealId = realIdProduto;
    }
}