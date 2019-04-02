package com.prototipo_danilo.tasks.repository.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.prototipo_danilo.tasks.entities.ProductsT;

import java.util.ArrayList;
import java.util.List;

public class ProdutoRepository {
    private static ProdutoRepository INSTANCE;
    private TaskDataBaseHelper taskDataBaseHelper;

    //region construtor
    //nao permite instancia da classe
    private ProdutoRepository(Context context){
        this.taskDataBaseHelper = new TaskDataBaseHelper(context);
    }

    public static ProdutoRepository getINSTANCE(Context context){
        if(INSTANCE ==null){
            INSTANCE = new ProdutoRepository(context);
        }
        return INSTANCE;
    }
    //endregion

    //region metodos

    //adciona lista de produtos no banco
    public void insert(List<ProductsT> list){
        String sql="insert into produtos (descricao,realid,nome) values(?,?,?);";
        SQLiteDatabase  db = this.taskDataBaseHelper.getWritableDatabase();
        db.beginTransaction();

        SQLiteStatement stmt = db.compileStatement(sql);
        for(ProductsT productsT : list){
            stmt.bindString(1,productsT.getDescription());
            stmt.bindString(2,productsT.getRealId());
            stmt.bindString(3,productsT.getName());

            stmt.executeInsert();
            stmt.clearBindings();
        }

        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }

    //recupera lista de produtos
    public List<ProductsT> getList(){
        List<ProductsT> list = new ArrayList<>();
        try{
            Cursor cursor;
            SQLiteDatabase db = this.taskDataBaseHelper.getReadableDatabase();

            //lista de produtos
            cursor = db.rawQuery("select descricao,realid,nome from produtos", null);
            if(cursor !=null && cursor.getCount() > 0){
                while(cursor.moveToNext()){
                    ProductsT entity = new ProductsT();
                    entity.setDescription( cursor.getString(cursor.getColumnIndexOrThrow("descricao")) );
                    entity.setRealId( cursor.getString(cursor.getColumnIndexOrThrow("realid")) );
                    entity.setName( cursor.getString(cursor.getColumnIndexOrThrow("nome")) );

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
        db.delete("produtos",null,null);
        db.close();
    }
    //endregion
}
