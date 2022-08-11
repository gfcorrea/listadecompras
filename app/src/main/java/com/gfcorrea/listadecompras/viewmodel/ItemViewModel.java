package com.gfcorrea.listadecompras.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.gfcorrea.listadecompras.model.ItemListaModel;
import com.gfcorrea.listadecompras.repository.ItemRepository;

import java.text.DecimalFormat;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class ItemViewModel extends ViewModel {
    //Utilizado para passar a lista ao adapter e atualizar a tela pelo observer.
    private MutableLiveData<List<ItemListaModel>> itemListaModel;

    //Utilizado para atualizar as totalizações na tela inicial.
    private MutableLiveData<String> valorTotal;
    private MutableLiveData<String> numItens;

    ItemRepository itemRepository = new ItemRepository();
    private DecimalFormat precision = new DecimalFormat("0.00");
    private ExecutorService executor = Executors.newSingleThreadExecutor();

    //ID da lista selecionada na primeira tela.
    private int id_lista;

    public MutableLiveData<List<ItemListaModel>> getItemListaModel(){
        if (itemListaModel == null) {
            itemListaModel = new MutableLiveData<List<ItemListaModel>>();
        }
        return itemListaModel;
    }

    public MutableLiveData<String> getTotalGeral() {
        if (valorTotal == null) {
            valorTotal = new MutableLiveData<String>();
        }
        return valorTotal;
    }

    public MutableLiveData<String> getNumItens() {
        if (numItens == null) {
            numItens = new MutableLiveData<String>();
        }
        return numItens;
    }

    public void atualizaLista(){
        this.getItemListaModel().postValue(itemRepository.itensDaListaID( this.getId_lista() ));
     }

    public void atualizaTotal(){
        getTotalGeral().postValue( "R$ " + precision.format(itemRepository.pegaTotalLista(this.getId_lista())) );
        getNumItens().postValue(String.valueOf(getItemListaModel().getValue().size()));
    }

    public int getId_lista() {
        return id_lista;
    }

    public void setId_lista(int id_lista) {
        this.id_lista = id_lista;
    }

    public List<ItemListaModel> getLista() {
        return getItemListaModel().getValue();
    }

    public void setLista(List<ItemListaModel> lista) {
        this.getItemListaModel().postValue(lista);
    }

    public void deleteByID(long id){
        executor.execute(()->{
            itemRepository.apagarID(id);
            atualizaTotal();
        });
    }
}
