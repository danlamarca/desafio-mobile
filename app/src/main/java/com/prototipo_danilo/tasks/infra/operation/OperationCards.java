package com.prototipo_danilo.tasks.infra.operation;

import android.content.Context;

import com.prototipo_danilo.tasks.entities.BestInstallment;
import com.prototipo_danilo.tasks.entities.CardEntity;
import com.prototipo_danilo.tasks.entities.ImageT;
import com.prototipo_danilo.tasks.entities.ProductsT;
import com.prototipo_danilo.tasks.entities.SellersT;
import com.prototipo_danilo.tasks.repository.local.BestInstallmentRepository;
import com.prototipo_danilo.tasks.repository.local.ImageRepository;
import com.prototipo_danilo.tasks.repository.local.ProdutoRepository;
import com.prototipo_danilo.tasks.repository.local.SellerRepository;

import java.util.ArrayList;
import java.util.List;

public class OperationCards {

    public static List<CardEntity> CarregaCardsByProd(Context context){
        List<CardEntity> cards = new ArrayList<>();
        List<ProductsT> prods = new ArrayList<>();
        List<SellersT> sellers = new ArrayList<>();
        List<ImageT> images = new ArrayList<>();
        List<BestInstallment> bestInstallments = new ArrayList<>();

        ProdutoRepository produtoRepository = ProdutoRepository.getINSTANCE(context);
        prods = produtoRepository.getList();

        //resgata cada propriedade pela chave do prod:
        for(ProductsT prodlaco : prods)
        {
            CardEntity card = new CardEntity();
            String Price="";
            String LastPrice="";
            String Count="";
            String Total="";
            String Valuet="";
            String Ratet="";
            String imageTag="";
            String imageURL="";

            //dados produto
            card.setName(prodlaco.getName());
            card.setDescription(prodlaco.getDescription());

            //dados da Seller
            SellerRepository seller = SellerRepository.getINSTANCE(context);
            sellers = seller.getListByProd(prodlaco.getRealId());
            for(SellersT sellerins : sellers)
            {
                try {
                    Price = String.valueOf(sellerins.getPrice());
                    LastPrice = String.valueOf(sellerins.getListPrice());
                }catch(Exception ex){
                    Price="";
                    LastPrice="";
                }

            }
            card.setPrice(Price);
            card.setLastPrice(LastPrice);

            //dados de Imagem
            ImageRepository img = ImageRepository.getINSTANCE(context);
            images = img.getListByProd(prodlaco.getRealId());
            int contaimg=0;
            for(ImageT imgins: images){

                try {
                    imageTag = imgins.getImageTag();
                    imageURL = imgins.getImageUrl();
                }catch(Exception ex){
                    imageTag="";
                    imageURL="";
                }
                contaimg++;
            }
            card.setImageTAG(images.get(0).getImageTag());
            card.setImageURL(images.get(0).getImageUrl());

            //dados de Bestinstallment
            BestInstallmentRepository bestInstallmentRepository = BestInstallmentRepository.getINSTANCE(context);
            bestInstallments = bestInstallmentRepository.getListByProd(prodlaco.getRealId());
            for(BestInstallment bestins : bestInstallments){

                try {
                    Count = String.valueOf(bestins.getCount());
                    Total = String.valueOf(bestins.getTotal());
                    Valuet = String.valueOf(bestins.getValue());
                    Ratet = String.valueOf(bestins.getRate());
                }catch (Exception ex){
                    Count = "";
                    Total = "";
                    Valuet = "";
                    Ratet = "";
                }
            }
            card.setCount(Count);
            card.setTotal(Total);
            card.setValue(Valuet);
            card.setRate(Ratet);

            cards.add(card);//para cada produto adiciona o card
        }

        return cards;
    }
}
