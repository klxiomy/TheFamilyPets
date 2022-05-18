package com.kristinxiomara.thefamilypets.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kristinxiomara.thefamilypets.R;
import com.kristinxiomara.thefamilypets.data.EncontradaModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EncontradaAdapter extends RecyclerView.Adapter<EncontradaAdapter.ViewHolder> {

    Context context;
    List<EncontradaModel> encontradaModelList;

    public EncontradaAdapter(Context context, List<EncontradaModel> encontradaModelList) {
        this.context = context;
        this.encontradaModelList = encontradaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recycler_masc_encontrada, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EncontradaModel encontradaModel = encontradaModelList.get(position);
        holder.txtUbicacion.setText("Ubicacion: " + encontradaModel.getFirstName());
        holder.txtDescrip.setText("Descripcion: " + encontradaModel.getLastName());
        holder.txtGenero.setText("Tipo: " + encontradaModel.getTipo());

        String imageUri=null;
        imageUri=encontradaModel.getImage();
        Picasso.get().load(imageUri).into(holder.imgEncontrada);
    }

    @Override
    public int getItemCount() {
        return encontradaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEncontrada;
        TextView txtUbicacion,txtGenero, txtDescrip;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgEncontrada=itemView.findViewById(R.id.imgEncontrada);
            txtUbicacion=itemView.findViewById(R.id.txtUbicacion);
            txtGenero=itemView.findViewById(R.id.txtGenero);
            txtDescrip=itemView.findViewById(R.id.txtDescrip);
        }
    }
}
