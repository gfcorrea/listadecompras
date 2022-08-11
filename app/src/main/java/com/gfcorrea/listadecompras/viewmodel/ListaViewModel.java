package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.model.ListaModel;
import com.gfcorrea.listadecompras.repository.ListaRepository;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListaViewModel extends ViewModel {

    //Utilizado para passar a lista ao adapter e atualizar a tela pelo observer.
    private MutableLiveData<List<ListaModel>> listaModel;

    //Utilizado para identificar qual foi a lista selecionada para carregar os itens.
    private ListaModel lista = new ListaModel();

    //Utilizado para atualizar as totalizações na tela inicial.
    private MutableLiveData<String> numListas;
    private MutableLiveData<String> valorTotal;

    private ListaRepository listaRepository = new ListaRepository();
    private DecimalFormat precision = new DecimalFormat("0.00");
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    public ListaViewModel() {
    }


    public MutableLiveData<String> getValorTotal() {
        if (valorTotal == null) {
            valorTotal = new MutableLiveData<String>();
        }
        return valorTotal;
    }

    public MutableLiveData<String> getNumListas() {
        if (numListas == null) {
            numListas = new MutableLiveData<String>();
        }
        return numListas;
    }

    public MutableLiveData<List<ListaModel>> getListaModel() {
        if (listaModel == null) {
            listaModel = new MutableLiveData<List<ListaModel>>();
        }
        return listaModel;
    }

    public void atualizaLista() {
        this.getListaModel().postValue(listaRepository.getAll());
    }

    public void atualizaTotal() {
        this.getValorTotal().postValue("R$ " + precision.format(listaRepository.pegaValorTotal()));
        this.getNumListas().postValue(String.valueOf(getListaModel().getValue().size()));
    }

    public List<ListaModel> getAll() {
        return this.getListaModel().getValue();
    }

    public int getId() {
        return lista.getId();
    }

    public void setId(int id) {
        this.lista.setId(id);
    }

    public String getNome() {
        return lista.getNome();
    }

    public void setNome(String nome) {
        this.lista.setNome(nome);
    }


    //Rever utilidade.
    public void deleteByID(int id) {
        executor.execute(() -> {
            listaRepository.excluirID(id);
            atualizaTotal();
        });

    }

}
