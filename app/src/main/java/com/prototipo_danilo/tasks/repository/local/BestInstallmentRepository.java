package com.prototipo_danilo.tasks.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import com.prototipo_danilo.tasks.entities.BestInstallment;

import java.util.ArrayList;
import java.util.List;

public class BestInstallmentRepository {
    private static BestInstallmentRepository INSTANCE;
    private TaskDataBaseHelper taskDataBaseHelper;

    //region construtor
    //nao permite instancia da classe
    private BestInstallmentRepository(Context context){
        this.taskDataBaseHelper = new TaskDataBaseHelper(context);
    }

    public static BestInstallmentRepository getINSTANCE(Context context){
        if(INSTANCE ==null){
            INSTANCE = new BestInstallmentRepository(context);
        }
        return INSTANCE;
    }
    //endregion

    //region metodos

    //adciona lista de bestinstalmment
    public void insert(List<BestInstallment> list){
        String sql="insert into bestinstalmment (parcelamento,total,valor,desconto,realidprod) values(?,?,?,?,?);";
        SQLiteDatabase  db = this.taskDataBaseHelper.getWritableDatabase();
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(BestInstallment bestInstallment : list){
            stmt.bindString(1, String.valueOf(bestInstallment.getCount()));
            stmt.bindString(2, String.valueOf(bestInstallment.getTotal()));
            stmt.bindString(3, String.valueOf(bestInstallment.getValue()));
            stmt.bindString(4, String.valueOf(bestInstallment.getRate()));
            stmt.bindString(5, bestInstallment.getRealId());

            stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    //recupera lista de bestinstalmment
    public List<BestInstallment> getList(){
        List<BestInstallment> list = new ArrayList<>();
        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select parcelamento,total,valor,desconto,realidprod from bestinstalmment", null);
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    BestInstallment entity = new BestInstallment();
                    entity.setCount( cursor.getFloat(cursor.getColumnIndexOrThrow("parcelamento")) );
                    entity.setTotal( cursor.getFloat(cursor.getColumnIndexOrThrow("total")) );
                    entity.setValue( cursor.getFloat(cursor.getColumnIndexOrThrow("valor")) );
                    entity.setRate ( cursor.getFloat(cursor.getColumnIndexOrThrow("desconto")) );
                    entity.setRealId( cursor.getString(cursor.getColumnIndexOrThrow("realidprod")) );

                    list.add(entity);
                }
            }

        }
        catch(Exception ex){
            return list;
        }

        return list;
    }

    public List<BestInstallment> getListByProd(String id){
        List<BestInstallment> list = new ArrayList<>();
        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select parcelamento,total,valor,desconto,realidprod from bestinstalmment where realidprod=?", new String[]{id});
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    BestInstallment entity = new BestInstallment();
                    entity.setCount( cursor.getFloat(cursor.getColumnIndexOrThrow("parcelamento")) );
                    entity.setTotal( cursor.getFloat(cursor.getColumnIndexOrThrow("total")) );
                    entity.setValue( cursor.getFloat(cursor.getColumnIndexOrThrow("valor")) );
                    entity.setRate ( cursor.getFloat(cursor.getColumnIndexOrThrow("desconto")) );
                    entity.setRealId( cursor.getString(cursor.getColumnIndexOrThrow("realidprod")) );

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
        db.delete("bestinstalmment",null,null);
        db.close();
    }
    //endregion
}
