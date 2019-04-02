package com.prototipo_danilo.tasks.repository.api;

import android.content.Context;

import com.prototipo_danilo.tasks.constants.APIConstants;
import com.prototipo_danilo.tasks.entities.APIResponse;
import com.prototipo_danilo.tasks.entities.FullParameters;
import com.prototipo_danilo.tasks.infra.InternetNotAvailableException;
import com.prototipo_danilo.tasks.utils.NetworkUtils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.AbstractMap;
import java.util.Iterator;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

//classe GENERICA implementadora, FOCADA APENAS EM CHAMADAS API de INTEGRACAO
public class ExternalRepository {

    private Context mContext;

    public ExternalRepository(Context context){
        this.mContext = context;
    }

    public APIResponse Execute(FullParameters parameters) throws InternetNotAvailableException{

        //caso nao exista conectividade
        if(!NetworkUtils.isConnectionAvailable(this.mContext)){
            throw new InternetNotAvailableException();
        }

        APIResponse response; //classe q recebe o json  e StatusCode
        InputStream inputStream; //retorno da api
        HttpsURLConnection conn; //parametros de conexao para url de chamada

        try{
            //region REQUISICAO HTTP

            //URL
            URL url = new URL(parameters.url);
            if(parameters.method == APIConstants.OPERATION_METHOD.GET){
                //GET
                if(parameters.parameters!=null) {
                    //possui queryString
                    url = new URL(parameters.url + getQuery(parameters.parameters, parameters.method));
                }
                else{
                    url = new URL(parameters.url);
                }
            }

            //CONEXAO:
            conn = (HttpsURLConnection) url.openConnection(); //abre a conexao com a URL.
            conn.setReadTimeout(100000); //qto tempo de espera na falha
            conn.setConnectTimeout(150000); //qto tempo de espera antes da conexao falha;
            conn.setRequestMethod(parameters.method); //get,post,put....
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");//valor padrao
            conn.setRequestProperty("charset","utf-8");
            conn.setUseCaches(false); //nao use caches..

            //HEADER
            if(parameters.headerParameters !=null){
                Iterator it = parameters.headerParameters.entrySet().iterator(); //será interado dos valores, parecido com o for
                while(it.hasNext()){
                    //enqto houver valor na chave
                    Map.Entry pair = (Map.Entry) it.next();
                    conn.setRequestProperty(pair.getKey().toString(),pair.getValue().toString());

                    it.remove(); //remover o valor da lista, pois ja foi utilizado
                }

            }

            if(parameters.method != APIConstants.OPERATION_METHOD.GET){
                //PUT,POST,DELETE
                //tratando valores do BODY:
                String query = getQuery(parameters.parameters, parameters.method);
                byte[] postDataBytes = query.getBytes("UTF-8");
                int postDataBytesLength = postDataBytes.length;

                conn.setRequestProperty("Content-Length", Integer.toString(postDataBytesLength));//a conexao precisa saber o tamanho do body
                conn.setDoOutput(true);//possui corpo
                conn.getOutputStream().write(postDataBytes);//escrevendo o corpo
            }

            conn.connect(); //ABRE a conexao.

            //REPOSTA DA API
            if(conn.getResponseCode() == APIConstants.STATUS_CODE.SUCCESS){
                //caso a conexao tenha sido bem sucedida:
                inputStream = conn.getInputStream();//LER O RETORNO DO RESPONSE
                response = new APIResponse(getStringFromInputStream(inputStream),conn.getResponseCode());//retorna o json e Status integracao

            }else{
                inputStream = conn.getErrorStream();
                response = new APIResponse(getStringFromInputStream(inputStream),conn.getResponseCode());
            }

            //FECHA CONEXÕES
            inputStream.close();
            conn.disconnect();

            //endregion
        }catch (Exception ex){
            //region FALHA REQUISICAO HTTP
            response = new APIResponse("", APIConstants.STATUS_CODE.NOT_FOUND);
            //endregion
        }

        return response;
    }

    private String getStringFromInputStream(InputStream inputStream) {
        //region RETORNA O JSON
        if(inputStream==null){return "";}

        BufferedReader br;
        StringBuilder builder = new StringBuilder();
        String line;
        try{
            //ler o inputStream:
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            br = new BufferedReader(inputStreamReader);
            while( (line = br.readLine() )!= null){
                builder.append(line);
            }
            br.close();
        }
        catch(Exception ex){
            return "";
        }

        return builder.toString();
        //endregion
    }

    private String getQuery(AbstractMap<String,String> params, String method) throws UnsupportedEncodingException {
        //queryString
        //Task/Overdue?pagesize=50&page=3

        if(params==null)
            return "";

        StringBuilder builder = new StringBuilder();
        boolean first=true;

        for(Map.Entry<String,String> e: params.entrySet()){
            if(first){
                if(method == APIConstants.OPERATION_METHOD.GET){
                    builder.append("?");
                }
                first=false;
            }else{
                builder.append("&");
            }

            builder.append(URLEncoder.encode(e.getKey(),"UTF-8"));//retirar os carateres especiais
            builder.append("=");
            builder.append(URLEncoder.encode(e.getValue(),"UTF-8"));//retirar os carateres especiais
        }

        return builder.toString();
    }
}
