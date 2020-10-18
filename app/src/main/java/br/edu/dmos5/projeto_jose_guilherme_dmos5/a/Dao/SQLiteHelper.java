package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "pesquisa.db";
    public static final String TABELA_PESQUISA = "pesquisa";
    public static final String COLUNA_UID = "uid";
    public static final String COLUNA_UF = "uf";
    public static final String COLUNA_ESTADO ="estado";
    public static final String COLUNA_CASOS = "casos";
    public static final String COLUNA_MORTES = "mortes";
    public static final String COLUNA_SUSPEITOS = "suspeitos";
    public static final String COLUNA_RECUSADOS = "recusados";
    public static final String COLUNA_DATA = "data";

    private Context mContext;

    public SQLiteHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        mContext = context;
    }

    public Context getContext(){

        return mContext;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String sql;

        sql = "CREATE TABLE " + TABELA_PESQUISA + " (";
        sql += COLUNA_UID + " TEXT NOT NULL, ";
        sql += COLUNA_UF + " TEXT NOT NULL, ";
        sql += COLUNA_ESTADO + " TEXT NOT NULL, ";
        sql += COLUNA_CASOS + " TEXT NOT NULL, ";
        sql += COLUNA_MORTES + " TEXT NOT NULL, ";
        sql += COLUNA_SUSPEITOS + " TEXT NOT NULL, ";
        sql += COLUNA_RECUSADOS + " TEXT NOT NULL, ";
        sql += COLUNA_DATA + " TEXT NOT NULL );";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}

