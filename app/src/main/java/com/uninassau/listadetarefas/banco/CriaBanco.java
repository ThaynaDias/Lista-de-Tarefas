package com.uninassau.listadetarefas.banco;

import android.app.TaskStackBuilder;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    public static final String NOME_BANCO = "exemplo";
    public static final String TABELA = "tarefas";
    public static final String ID = "_id";
    public static final String TAREFA = "tarefa";
    public static final String OBSERVACAO = "observacao";
    public static final int VERSAO = 1;

    public CriaBanco(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = " CREATE TABLE " + TABELA + "("
                + ID + " integer primary key autoincrement,"
                + TAREFA + " text,"
                + OBSERVACAO + " text"
                + ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

    }
}