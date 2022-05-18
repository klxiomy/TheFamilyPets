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
import com.kristinxiomara.thefamilypets.data.DenunciaModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DenunciaAdapter extends RecyclerView.Adapter<DenunciaAdapter.ViewHolder>{
    Context context;
    List<DenunciaModel> modelListDenuncia;

    public DenunciaAdapter(Context context, List<DenunciaModel> modelListDenuncia) {
        this.context = context;
        this.modelListDenuncia = modelListDenuncia;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recycler_denuncia,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DenunciaModel denunciaModel= modelListDenuncia.get(position);
        holder.titulo.setText("Titulo:  "+denunciaModel.getTitulo());
        holder.descripcion.setText("Descripcion:"+denunciaModel.getDescricion());

        String imageUri=null;
        imageUri=denunciaModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return modelListDenuncia.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView titulo,descripcion;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgRecyclerDenuncia);
            descripcion=itemView.findViewById(R.id.descripcionRecyclerDenuncia);
            titulo=itemView.findViewById(R.id.titulorecyclerDenuncia);

        }
    }
}
