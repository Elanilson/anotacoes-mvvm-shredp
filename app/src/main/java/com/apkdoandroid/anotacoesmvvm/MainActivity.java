package com.apkdoandroid.anotacoesmvvm;

import android.os.Bundle;

import com.apkdoandroid.anotacoesmvvm.repositorio.Repositorio;
import com.apkdoandroid.anotacoesmvvm.viewmodel.MainViewModel;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;

import com.apkdoandroid.anotacoesmvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;
    private Repositorio repositorio;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String textoRecuperado = binding.editAnotacao.getText().toString();
                viewModel.salvar(textoRecuperado);

            }
        });

        setObserves();


    }

    @Override
    protected void onResume() {
        super.onResume();
        viewModel.recuperar();

    }

    private void setObserves(){
        viewModel.resposta.observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Snackbar.make(binding.getRoot(), s, Snackbar.LENGTH_LONG).show();
            }
        });
        viewModel.anotacao.observe(MainActivity.this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.editAnotacao.setText(s);
            }
        });


    }

}