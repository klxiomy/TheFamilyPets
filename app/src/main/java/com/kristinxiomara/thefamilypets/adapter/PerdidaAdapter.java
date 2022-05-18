package com.kristinxiomara.thefamilypets.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.kristinxiomara.thefamilypets.R;
import com.kristinxiomara.thefamilypets.data.PerdidaModel;
import com.squareup.picasso.Picasso;

public class PerdidaAdapter extends FirestoreRecyclerAdapter <PerdidaModel, PerdidaAdapter.ViewHolder> {


    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public PerdidaAdapter(@NonNull FirestoreRecyclerOptions<PerdidaModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull PerdidaModel model) {
        holder.nombre.setText("Nombre: " + model.getNombre());
        holder.raza.setText("Raza: " + model.getRaza());
        holder.tipo.setText("Tipo: " + model.getTipo());
        holder.diasP.setText("Dias Perdido: " + model.getDiasP());
        holder.descripcion.setText("Descripcion: " + model.getDescripcion());
        holder.color.setText("Color: "+ model.getColor());

        String imageURL =null;
        imageURL = model.getFoto();
        Picasso.get().load(imageURL).into(holder.imagen);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.design_recycler_mas_perdida, parent, false);
        return new ViewHolder(view);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombre, raza, tipo, diasP, descripcion, color;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre =  itemView.findViewById(R.id.MostrarNombre);
            raza =  itemView.findViewById(R.id.MostrarRaza);
            tipo =  itemView.findViewById(R.id.MostrarTipo);
            diasP =  itemView.findViewById(R.id.DiasPerdidos);
            descripcion =  itemView.findViewById(R.id.MostrarDescripcion);
            color =  itemView.findViewById(R.id.MostrarColor);

            imagen = itemView.findViewById(R.id.MostrarImagen);
        }
    }
}
