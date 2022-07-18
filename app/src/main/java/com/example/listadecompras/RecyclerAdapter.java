package com.example.listadecompras;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.meuViewHolder> {



    @NonNull
    @Override
    public meuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_layout, viewGroup, false);


        return new meuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull meuViewHolder meuViewHolder, int i) {

    }

    @Override
    public int getItemCount() {

        return 5;
    }

    public class meuViewHolder extends RecyclerView.ViewHolder{

        public meuViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
