package com.prototipo_danilo.tasks.business;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.gson.Gson;
import com.prototipo_danilo.tasks.constants.APIConstants;
import com.prototipo_danilo.tasks.entities.APIResponse;
import com.prototipo_danilo.tasks.entities.BestInstallment;
import com.prototipo_danilo.tasks.entities.CarroselEntity;
import com.prototipo_danilo.tasks.entities.FullParameters;
import com.prototipo_danilo.tasks.entities.ImageT;
import com.prototipo_danilo.tasks.entities.ProductsT;
import com.prototipo_danilo.tasks.entities.SellersT;
import com.prototipo_danilo.tasks.entities.SkusT;
import com.prototipo_danilo.tasks.entities.SpecificationsT;
import com.prototipo_danilo.tasks.infra.SecurityPreferences;
import com.prototipo_danilo.tasks.infra.URLBuilder;
import com.prototipo_danilo.tasks.infra.operation.OperationResult;
import com.prototipo_danilo.tasks.repository.api.ExternalRepository;
import com.prototipo_danilo.tasks.repository.local.BestInstallmentRepository;
import com.prototipo_danilo.tasks.repository.local.ImageRepository;
import com.prototipo_danilo.tasks.repository.local.ProdutoRepository;
import com.prototipo_danilo.tasks.repository.local.SellerRepository;
import com.prototipo_danilo.tasks.repository.local.TaskDataBaseHelper;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class APIBusiness {

    private ExternalRepository mExternalRepository;
    private Context mcontext;

    public APIBusiness(Context context){
        this.mExternalRepository = new ExternalRepository(context);
        this.mcontext = context;
    }

    public OperationResult<Boolean> create(){
        OperationResult<Boolean> result = new OperationResult<>();

        try {
            //constroi a URL:
            URLBuilder builder = new URLBuilder(APIConstants.ENDPOINT.ROOT);
            String URLfinal = builder.getURL();

            //HEADER
            AbstractMap<String, String> headers = new HashMap<>();
            headers.put(APIConstants.PARAMETERS.ContentType, "application/x-www-form-urlencoded");

            //BODY
            AbstractMap<String, String> params = new HashMap<>();
            params.put(APIConstants.PARAMETERS.Query, "");
            params.put(APIConstants.PARAMETERS.Offset,"0");
            params.put(APIConstants.PARAMETERS.Size,"10");

            FullParameters full = new FullParameters(APIConstants.OPERATION_METHOD.POST, URLfinal, (HashMap) params, (HashMap) headers);

            //chamada da API
            APIResponse response = this.mExternalRepository.Execute(full);
            String aaa="";

            //tratar o json
            if(response.statusCode== APIConstants.STATUS_CODE.SUCCESS){
                CarroselEntity carroselEntity = new Gson().fromJson(response.json,CarroselEntity.class);
                SecurityPreferences securityPreferences = new SecurityPreferences(this.mcontext);
                securityPreferences.storeString("executouapi","S");

                //region INSERE DB
                List<ProductsT> produtos = new ArrayList<>();
                List<SellersT> sellers = new ArrayList<>();
                List<ImageT> imagets = new ArrayList<>();
                List<BestInstallment> bestinstTs = new ArrayList<>();

                //region TRATA DADOS API
                int conta=0;
                for(int i=0; i < carroselEntity.Products.size();i++)
                {
                    Object formataprod = carroselEntity.Products.get(i);
                    String json_formatadoprod = new Gson().toJson(formataprod);
                    ProductsT prodins = new Gson().fromJson(json_formatadoprod,ProductsT.class);//recupera objeto formatado

                    //region RECUPERA SKUS
                    for(int sk=0; sk < prodins.Skus.size();sk++){
                        Object formataskus = prodins.Skus.get(sk);
                        String json_formatadoskus = new Gson().toJson(formataskus);
                        SkusT skusT = new Gson().fromJson(json_formatadoskus,SkusT.class);//recupera object Skus

                        //region RECUPERA IMAGENS
                        for(int img=0; img < skusT.Images.size(); img++){
                            Object formataimg = skusT.Images.get(img);
                            String json_formatoimg = new Gson().toJson(formataimg);
                            ImageT imageT = new Gson().fromJson(json_formatoimg,ImageT.class);

                            ImageT newimageT = new ImageT();
                            newimageT.setRealIdProduto(prodins.getRealId());//REAL ID DO PRODUTO
                            newimageT.setImageTag(imageT.getImageTag());
                            newimageT.setImageUrl(imageT.getImageUrl());
                            imagets.add(newimageT);
                        }
                        //endregion

                        //region RECUPERA SELLERS:
                        for(int sl=0; sl < skusT.Sellers.size(); sl++){
                            Object formatasellers = skusT.Sellers.get(sl);
                            String json_formatadosellers = new Gson().toJson(formatasellers);
                            SellersT sellersT = new Gson().fromJson(json_formatadosellers,SellersT.class);//recupera objeto sellers

                            SellersT newsellerT = new SellersT();
                            newsellerT.setId(prodins.getRealId());//SEMPRE SETAR COM O ID
                            newsellerT.setListPrice(sellersT.getListPrice());
                            newsellerT.setPrice(sellersT.getPrice());
                            sellers.add(newsellerT);

                            //region RECUPERA BESTINSTALLMENT
                            BestInstallment bestInstallment = sellersT.getBestInstallment();
                            try {
                                BestInstallment newbestinstallment = new BestInstallment();
                                newbestinstallment.setRealId(prodins.getRealId());//SEMPRE SETAR COM O ID
                                newbestinstallment.setCount(bestInstallment.getCount());
                                newbestinstallment.setRate(bestInstallment.getRate());
                                newbestinstallment.setTotal(bestInstallment.getTotal());
                                newbestinstallment.setValue(bestInstallment.getValue());
                                bestinstTs.add(newbestinstallment);
                            }
                            catch(Exception ex){
                                //posiveis valores nulos
                            }
                            //endregion
                        }
                        //endregion
                    }
                    //endregion

                    //armazena produto
                    produtos.add(prodins);
                    conta++;
                }
                //endregion

                //region PERSISTE NO REPOSITORIO
                try {
                    //GRAVA PRODUTO
                    if (produtos.size() > 0) {
                        ProdutoRepository produtoRepository = ProdutoRepository.getINSTANCE(this.mcontext);
                        produtoRepository.clear();
                        //escreve produtos
                        produtoRepository.insert(produtos);
                        //lê produtos:
                        //List<ProductsT> prodLe = produtoRepository.getList();
                    }

                    //GRAVA SELLERS
                    if (sellers.size() > 0) {
                        SellerRepository sellerRepository = SellerRepository.getINSTANCE(this.mcontext);
                        sellerRepository.clear();
                        //escreve seller
                        sellerRepository.insert2(sellers);
                        //le seller
                        //List<SellersT> sellle = sellerRepository.getList();
                    }

                    //GRAVA IMAGEM
                    if (imagets.size() > 0) {
                        ImageRepository imageRepository = ImageRepository.getINSTANCE(this.mcontext);
                        imageRepository.clear();
                        //escreve image
                        imageRepository.insert(imagets);
                        //le image
                        //List<ImageT> imagetle = imageRepository.getList();
                    }

                    //GRAVA bestinstalmment
                    if (bestinstTs.size() > 0) {
                        BestInstallmentRepository bestInstallmentRepository = BestInstallmentRepository.getINSTANCE(this.mcontext);
                        bestInstallmentRepository.clear();
                        //escreve bestinstalmment
                        bestInstallmentRepository.insert(bestinstTs);
                        //le bestinstalmment
                        //List<BestInstallment> bestInstallmentsle = bestInstallmentRepository.getList();
                    }
                }
                catch (Exception ex){
                    throw new Exception("Ocorreu um erro ao criar o banco de dados!");
                }
                //endregion

                //endregion

                result.setResult(true);
            }else{
                result.setResult(false);
            }
        }
        catch(Exception e){

            return result;
        }


        return result;
    }
}
