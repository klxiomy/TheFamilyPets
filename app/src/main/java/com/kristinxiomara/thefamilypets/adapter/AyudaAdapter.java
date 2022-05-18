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
import com.kristinxiomara.thefamilypets.data.AyudaModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AyudaAdapter extends RecyclerView.Adapter<AyudaAdapter.ViewHolder>{
    Context context;
    List<AyudaModel> ayudaModelList;

    public AyudaAdapter(Context context, List<AyudaModel> ayudaModelList) {
        this.context = context;
        this.ayudaModelList = ayudaModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.desing_recycler_ayuda,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AyudaModel ayudaModel=ayudaModelList.get(position);
        holder.textdesc.setText("Descripcion: " + ayudaModel.getDescripcion());
        holder.textcontac.setText("Contacto: " + ayudaModel.getEmail());
        holder.tipo.setText("Tipo: " + ayudaModel.getTipo());
        holder.textUbica.setText("Ubicacion:"+ayudaModel.getUbicacion());
        String imageUri=null;
        imageUri=ayudaModel.getImage();
        Picasso.get().load(imageUri).into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return ayudaModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textdesc,textcontac,tipo, textUbica;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.imgAyuda);
            textdesc=itemView.findViewById(R.id.txtDescripAyuda);
            textcontac=itemView.findViewById(R.id.txtContactoAyuda);
            tipo=itemView.findViewById(R.id.txtTipoAyuda);
            textUbica=itemView.findViewById(R.id.txtUbicacionAyuda);
        }
    }
}
