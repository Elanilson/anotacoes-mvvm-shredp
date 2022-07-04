package com.apkdoandroid.anotacoesmvvm.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.apkdoandroid.anotacoesmvvm.repositorio.Repositorio;

public class MainViewModel extends AndroidViewModel {
    private Repositorio repositorio;

    private MutableLiveData<String> mAnotacao = new MutableLiveData<>();
    public LiveData<String> anotacao = mAnotacao;

    private MutableLiveData<String> mResposta = new MutableLiveData<>();
    public LiveData<String> resposta = mResposta;

    public MainViewModel(@NonNull Application application) {
        super(application);
        repositorio = Repositorio.getInstance(application.getApplicationContext());
    }

    public void salvar(String texto){
        if(!texto.equals("")){
            repositorio.salvarAnotacao(texto);
            mResposta.setValue("Anotacao salvo com sucesso!");
        }else{
            mResposta.setValue("Digite algo para salvar");

        }
    }

    public void recuperar(){
        mAnotacao.setValue(repositorio.recuperarAnotacao());
    }
}
