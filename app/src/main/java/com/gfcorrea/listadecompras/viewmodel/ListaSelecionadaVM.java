package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.ViewModel;

public class ListaSelecionadaVM extends ViewModel {
    private int id;
    private String nome;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
