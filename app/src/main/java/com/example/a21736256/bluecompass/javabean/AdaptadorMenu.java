package com.example.a21736256.bluecompass.javabean;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.a21736256.bluecompass.R;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorMenu extends RecyclerView.Adapter<AdaptadorMenu.PlayaViewHolder> implements View.OnClickListener {


    private ArrayList<PlayaItem> listaPlayas;
    private View.OnClickListener listener;


    public AdaptadorMenu(ArrayList<PlayaItem> listaPlayas) {
        this.listaPlayas = listaPlayas;
    }

    @NonNull
    @Override
    public PlayaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rv_playas, viewGroup, false);
        PlayaViewHolder vh = new PlayaViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayaViewHolder playaViewHolder, int i) {
        playaViewHolder.bindPlaya(listaPlayas.get(i));

    }

    @Override
    public int getItemCount() {
        return listaPlayas.size();
    }

    @Override
    public void onClick(View v) {
        if (listener!=null){
            listener.onClick(v);
        }
    }

    public class PlayaViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNombreItem;
        private TextView tvZonaItem;
        private ImageView ivPlayaItem;

        public PlayaViewHolder(@NonNull View viewItem) {
            super(viewItem);
            tvNombreItem = viewItem.findViewById(R.id.tvNombreItem);
            tvZonaItem = viewItem.findViewById(R.id.tvZonaItem);
            ivPlayaItem = viewItem.findViewById(R.id.ivPlayaItem);
        }

        public void bindPlaya(PlayaItem p){
            tvNombreItem.setText(p.getNombre());
            tvZonaItem.setText(p.getZona());
            Glide.with(ivPlayaItem.getContext()).applyDefaultRequestOptions(RequestOptions.fitCenterTransform()).load(p.getImagen()).into(ivPlayaItem);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener= listener;
    }
}