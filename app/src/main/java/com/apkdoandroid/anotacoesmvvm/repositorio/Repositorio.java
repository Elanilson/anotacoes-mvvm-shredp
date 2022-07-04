package com.apkdoandroid.anotacoesmvvm.repositorio;

import android.content.Context;
import android.content.SharedPreferences;

public class Repositorio {
    private Context context;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String NOME_ARQUIVO ="anotacao.preferencias";
    private static final String CHAVE_NOME = "nome";

    private static Repositorio INSTACE;

    private Repositorio(Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(NOME_ARQUIVO,0);
        editor = preferences.edit();
    }

    public static Repositorio getInstance(Context context){
        if(INSTACE == null){
            INSTACE = new Repositorio(context);
        }
        return INSTACE;
    }

    public void salvarAnotacao(String anotacao){
        editor.putString(CHAVE_NOME,anotacao);
        editor.commit();

    }
    public String recuperarAnotacao(){
        String nota = "";
        if (preferences.getString(CHAVE_NOME,"") != null){
            nota = preferences.getString(CHAVE_NOME,"");
        }
        return nota ;
    }
}
