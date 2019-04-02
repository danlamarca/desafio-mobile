package com.prototipo_danilo.tasks.repository.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.prototipo_danilo.tasks.constants.DataBaseConstants;

public class TaskDataBaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "UltraTask.db";

    // Criação da tabela de produto
    private static final String SQL_CREATE_TABLE_PRODUCTS =
            "create table " + DataBaseConstants.PRODUCTS.TABLE_NAME + " ("
                    + DataBaseConstants.PRODUCTS.COLUMNS.ID + " INT primary key, "
                    + DataBaseConstants.PRODUCTS.COLUMNS.DESCRICAO + " text not null, "
                    + DataBaseConstants.PRODUCTS.COLUMNS.REALID + " text not null, "
                    + DataBaseConstants.PRODUCTS.COLUMNS.NOME + " text not null);";


    // Criação da tabela de imagens
    private static final String SQL_CREATE_TABLE_IMAGENS =
            "create table " + DataBaseConstants.IMAGENS.TABLE_NAME + " ("
                    + DataBaseConstants.IMAGENS.COLUMNS.ID + " INT primary key, "
                    + DataBaseConstants.IMAGENS.COLUMNS.URL + " text not null, "
                    + DataBaseConstants.IMAGENS.COLUMNS.REALIDPROD + " text not null, "
                    + DataBaseConstants.IMAGENS.COLUMNS.TAG + " text not null);";

    // Criação da tabela de Sellers
    private static final String SQL_CREATE_TABLE_SELLERS =
            "create table " + DataBaseConstants.SELLERS.TABLE_NAME + " ("
                    + DataBaseConstants.SELLERS.COLUMNS.ID + " INT primary key, "
                    + DataBaseConstants.SELLERS.COLUMNS.LASTPRICE + " text not null, "
                    + DataBaseConstants.SELLERS.COLUMNS.REALIDPROD + " text not null, "
                    + DataBaseConstants.SELLERS.COLUMNS.PRICE + " text not null);";


    // Criação da tabela de BESTINSTALLMENT
    private static final String SQL_CREATE_TABLE_BESTINSTALLMENT =
            "create table " + DataBaseConstants.BESTINSTALLMENT.TABLE_NAME + " ("
                    + DataBaseConstants.BESTINSTALLMENT.COLUMNS.ID + " INT primary key, "
                    + DataBaseConstants.BESTINSTALLMENT.COLUMNS.PARCELAMENTO + " text not null, "
                    + DataBaseConstants.BESTINSTALLMENT.COLUMNS.TOTAL + " text not null, "
                    + DataBaseConstants.BESTINSTALLMENT.COLUMNS.VALOR + " text not null, "
                    + DataBaseConstants.BESTINSTALLMENT.COLUMNS.REALIDPROD + " text not null, "
                    + DataBaseConstants.BESTINSTALLMENT.COLUMNS.DESCONTO + " text not null);";

    // Remoção de tabelas
    private static final String DROP_TABLE_PRODUTO = "DROP TABLE IF EXISTS " + DataBaseConstants.PRODUCTS.TABLE_NAME;

    public TaskDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {
            sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PRODUCTS);
            sqLiteDatabase.execSQL(SQL_CREATE_TABLE_IMAGENS);
            sqLiteDatabase.execSQL(SQL_CREATE_TABLE_SELLERS);
            sqLiteDatabase.execSQL(SQL_CREATE_TABLE_BESTINSTALLMENT);
        }
        catch(Exception ex){
            String exc = ex.toString();
            String bb="";
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // sqLiteDatabase.execSQL(SQL_CREATE_TABLE_PRODUCTS);
     }

     public void Delete(SQLiteDatabase sqLiteDatabase){
         sqLiteDatabase.execSQL("DROP TABLE IF EXISTS clientes");
     }

}