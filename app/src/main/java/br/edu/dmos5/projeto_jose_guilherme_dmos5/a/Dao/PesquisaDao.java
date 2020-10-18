package br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.edu.dmos5.projeto_jose_guilherme_dmos5.a.Model.Pesquisa;

public class PesquisaDao {

    private SQLiteDatabase mSqLiteDatabase;
    private SQLiteHelper mHelper;

    public PesquisaDao(Context context) {
        mHelper = new SQLiteHelper(context);
    }

    public void adicionar(Pesquisa pesquisa) throws NullPointerException{

        if(pesquisa == null){
            throw new NullPointerException();
        }

        ContentValues valores = new ContentValues();

        valores.put(SQLiteHelper.COLUNA_UID, pesquisa.getUid());
        valores.put(SQLiteHelper.COLUNA_UF, pesquisa.getUf());
        valores.put(SQLiteHelper.COLUNA_ESTADO, pesquisa.getState());
        valores.put(SQLiteHelper.COLUNA_CASOS, pesquisa.getCasos());
        valores.put(SQLiteHelper.COLUNA_MORTES, pesquisa.getMortes());
        valores.put(SQLiteHelper.COLUNA_SUSPEITOS, pesquisa.getSuspeitos());
        valores.put(SQLiteHelper.COLUNA_RECUSADOS, pesquisa.getRecusados());
        valores.put(SQLiteHelper.COLUNA_DATA, pesquisa.getData());

        mSqLiteDatabase = mHelper.getWritableDatabase();

        mSqLiteDatabase.insert(SQLiteHelper.TABELA_PESQUISA, null, valores);

        mSqLiteDatabase.close();
    }

    public List<Pesquisa> recuperaTodos(){

        List<Pesquisa> pesquisaList;
        Pesquisa pesquisa;
        Cursor mCursor;
        pesquisaList = new ArrayList<>();

        String colunas[] = new String[]{
            SQLiteHelper.COLUNA_UID,
            SQLiteHelper.COLUNA_UF,
            SQLiteHelper.COLUNA_ESTADO,
            SQLiteHelper.COLUNA_CASOS,
            SQLiteHelper.COLUNA_MORTES,
            SQLiteHelper.COLUNA_SUSPEITOS,
            SQLiteHelper.COLUNA_RECUSADOS,
            SQLiteHelper.COLUNA_DATA
        };

        mSqLiteDatabase = mHelper.getReadableDatabase();

        mCursor = mSqLiteDatabase.query(
            SQLiteHelper.TABELA_PESQUISA,
            colunas,
            null,
            null,
            null,
            null,
            SQLiteHelper.COLUNA_ESTADO
        );

        while (mCursor.moveToNext()){

            pesquisa = new Pesquisa(
                mCursor.getString(0),
                mCursor.getString(1),
                mCursor.getString(2),
                mCursor.getString(3),
                mCursor.getString(4),
                mCursor.getString(5),
                mCursor.getString(6),
                mCursor.getString(7)
            );

            pesquisaList.add(pesquisa);
        }

        mCursor.close();

        mSqLiteDatabase.close();

        return pesquisaList;
    }
}
