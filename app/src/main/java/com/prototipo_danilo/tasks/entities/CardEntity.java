package com.prototipo_danilo.tasks.entities;

import java.util.ArrayList;
import java.util.List;

public class CardEntity {

    private String Name;
    private String Description;
    private String Price;
    private String LastPrice;
    private String Count;
    private String Total;
    private String Value;
    private String Rate;
    private String imageURL;
    private String imageTAG;


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getLastPrice() {
        return LastPrice;
    }

    public void setLastPrice(String lastPrice) {
        LastPrice = lastPrice;
    }

    public String getCount() {
        return Count;
    }

    public void setCount(String count) {
        Count = count;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }

    public String getRate() {
        return Rate;
    }

    public void setRate(String rate) {
        Rate = rate;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getImageTAG() {
        return imageTAG;
    }

    public void setImageTAG(String imageTAG) {
        this.imageTAG = imageTAG;
    }

    //metodos
    public static List<CardEntity> getListByparam(List<CardEntity> list, String campo, String param){
        List<CardEntity> listins=new ArrayList<>();

        //filtra por nome
        if(campo.contains("Nome")) {

            for (CardEntity card : list) {
                if (card.getName().toUpperCase().contains(param.toUpperCase()))
                {
                    listins.add(card);
                }
            }
        }

        return listins;
    }
}
