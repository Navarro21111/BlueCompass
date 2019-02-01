package com.example.a21736256.bluecompass.javabean;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.a21736256.bluecompass.R;

import java.util.ArrayList;

public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.MiViewHolder> implements View.OnClickListener {

    private ArrayList<PlayaItem> listaPlaya;

    public AdaptadorMenu(ArrayList<PlayaItem> listaPlaya) {
        this.listaPlaya = listaPlaya;
    }
    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i){
        View v=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_playas, viewGroup, false);
        MiViewHolder vh= new MiViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MiViewHolder miViewHolder, int i) {
        miViewHolder.bindPlaya(listaPlaya.get(i));
    }

    @Override
    public int getItemCount() {
        return listaPlaya.size();
    }

    @Override
    public void onClick(View v) {

    }


    public class MiViewHolder extends RecyclerView.ViewHolder{
        TextView tvNombrePlaya;
        TextView tvProvincia;


        public MiViewHolder(@NonNull View itemView){
            super(itemView);
            tvNombrePlaya=itemView.findViewById(R.id.tvNombreMenu);
            tvProvincia=itemView.findViewById(R.id.tvProvincia);

        }

        public void bindPlaya(PlayaItem playa){
            tvNombrePlaya.setText(playa.getNombre());
            tvProvincia.setText(playa.getProvincia());
        }
    }
}