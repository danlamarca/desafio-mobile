package com.prototipo_danilo.tasks.business;

import android.content.Context;

import com.prototipo_danilo.tasks.entities.CardEntity;

import java.util.ArrayList;
import java.util.List;

public class CardBusiness {

    private Context mcontext;

    public CardBusiness(Context context){
        this.mcontext = context;
    }

    public static List<CardEntity> FiltraCard(List<CardEntity> list, String campo, String param){
        List<CardEntity> listins=new ArrayList<>();

        listins = CardEntity.getListByparam(list,campo,param);

        return listins;
    }
}
