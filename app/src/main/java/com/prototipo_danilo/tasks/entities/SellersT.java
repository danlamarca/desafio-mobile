package com.prototipo_danilo.tasks.entities;

import java.util.ArrayList;

public class SellersT {
    private String Id;
    private String Name;
    private float Quantity;
    private float Price;
    private float ListPrice;
    BestInstallment BestInstallment;

    // Getter Methods

    public String getId() {
        return Id;
    }

    public String getName() {
        return Name;
    }

    public float getQuantity() {
        return Quantity;
    }

    public float getPrice() {
        return Price;
    }

    public float getListPrice() {
        return ListPrice;
    }

    public BestInstallment getBestInstallment() {
        return BestInstallment;
    }

    // Setter Methods

    public void setId(String Id) {
        this.Id = Id;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setQuantity(float Quantity) {
        this.Quantity = Quantity;
    }

    public void setPrice(float Price) {
        this.Price = Price;
    }

    public void setListPrice(float ListPrice) {
        this.ListPrice = ListPrice;
    }

    public void setBestInstallment(BestInstallment BestInstallmentObject) {
        this.BestInstallment = BestInstallmentObject;
    }
}
