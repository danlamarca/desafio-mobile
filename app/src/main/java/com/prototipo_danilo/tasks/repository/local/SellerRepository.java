package com.prototipo_danilo.tasks.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.prototipo_danilo.tasks.entities.ProductsT;
import com.prototipo_danilo.tasks.entities.SellersT;

import java.util.ArrayList;
import java.util.List;

public class SellerRepository {
    private static SellerRepository INSTANCE;
    private TaskDataBaseHelper taskDataBaseHelper;

    //region construtor
    private SellerRepository(Context context){
        this.taskDataBaseHelper = new TaskDataBaseHelper(context);
    }

    public static SellerRepository getINSTANCE(Context context){
        if(INSTANCE ==null){
            INSTANCE = new SellerRepository(context);
        }
        return INSTANCE;
    }
    //endregion


    //region metodos
    public void insert(List<SellersT> list){
        String sql="insert into sellerst (lastprice,realidprod,price) values(?,?,?);";
        SQLiteDatabase db = this.taskDataBaseHelper.getWritableDatabase();
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(SellersT seller : list){
            stmt.bindString(1, String.valueOf(seller.getListPrice()));
            stmt.bindString(2, seller.getId());
            stmt.bindString(3, String.valueOf(seller.getPrice()));
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }


    public void insert2(List<SellersT> list)
    {
        SQLiteDatabase db = this.taskDataBaseHelper.getWritableDatabase();

        for(SellersT sll : list) {
            ContentValues Seller = new ContentValues();
            Seller.put("lastprice", sll.getListPrice());
            Seller.put("realidprod", sll.getId());
            Seller.put("price", sll.getPrice());

            Long ttt = db.insert("sellerst", null, Seller);
            String nnnn="";
        }
        db.close();

    }


    public List<SellersT> getList(){
        List<SellersT> list = new ArrayList<>();

        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select lastprice,realidprod,price from sellerst", null);
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    SellersT entity = new SellersT();
                    entity.setListPrice( cursor.getLong(cursor.getColumnIndexOrThrow("lastprice")) );
                    entity.setId( cursor.getString(cursor.getColumnIndexOrThrow("realidprod")) );
                    entity.setPrice( cursor.getLong(cursor.getColumnIndexOrThrow("price")) );

                    list.add(entity);
                }
            }

        }
        catch(Exception ex){
            return list;
        }

        return list;
    }

    public List<SellersT> getListByProd(String id){
        List<SellersT> list = new ArrayList<>();

        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select lastprice,realidprod,price from sellerst where realidprod=?", new String[]{id});
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    SellersT entity = new SellersT();
                    entity.setListPrice( cursor.getLong(cursor.getColumnIndexOrThrow("lastprice")) );
                    entity.setId( cursor.getString(cursor.getColumnIndexOrThrow("realidprod")) );
                    entity.setPrice( cursor.getLong(cursor.getColumnIndexOrThrow("price")) );

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
        db.delete("sellerst",null,null);
        db.close();
    }
    //endregion

}
