package com.prototipo_danilo.tasks.repository.local;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.prototipo_danilo.tasks.entities.ImageT;

import java.util.ArrayList;
import java.util.List;

public class ImageRepository {

    private static ImageRepository INSTANCE;
    private TaskDataBaseHelper taskDataBaseHelper;

    //region construtor
    //nao permite instancia da classe
    private ImageRepository(Context context){
        this.taskDataBaseHelper = new TaskDataBaseHelper(context);
    }

    public static ImageRepository getINSTANCE(Context context){
        if(INSTANCE ==null){
            INSTANCE = new ImageRepository(context);
        }
        return INSTANCE;
    }
    //endregion

    //region metodos

    //adciona lista de imagens no banco
    public void insert(List<ImageT> list){
        String sql="insert into imagens (url,tag,realidprod) values(?,?,?);";
        SQLiteDatabase db = this.taskDataBaseHelper.getWritableDatabase();
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(ImageT imageT : list){
            stmt.bindString(1,imageT.getImageUrl());
            stmt.bindString(2,imageT.getImageTag());
            stmt.bindString(3,imageT.getRealIdProduto());

            stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    //recupera lista de imagens
    public List<ImageT> getList(){
        List<ImageT> list = new ArrayList<>();
        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select url,tag,realidprod from imagens", null);
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    ImageT entity = new ImageT();
                    entity.setImageUrl( cursor.getString(cursor.getColumnIndexOrThrow("url")) );
                    entity.setImageTag( cursor.getString(cursor.getColumnIndexOrThrow("tag")) );
                    entity.setRealIdProduto( cursor.getString(cursor.getColumnIndexOrThrow("realidprod")) );

                    list.add(entity);
                }
            }

        }
        catch(Exception ex){
            return list;
        }

        return list;
    }


    public List<ImageT> getListByProd(String id){
        List<ImageT> list = new ArrayList<>();
        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select url,tag,realidprod from imagens where realidprod=?", new String[]{id});
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    ImageT entity = new ImageT();
                    entity.setImageUrl( cursor.getString(cursor.getColumnIndexOrThrow("url")) );
                    entity.setImageTag( cursor.getString(cursor.getColumnIndexOrThrow("tag")) );
                    entity.setRealIdProduto( cursor.getString(cursor.getColumnIndexOrThrow("realidprod")) );

                    list.add(entity);
                }
            }

        }
        catch(Exception ex){
            return list;
        }

        return list;
    }

    public void clear(){
        SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();
        db.delete("imagens",null,null);
        db.close();
    }
    //endregion
}
