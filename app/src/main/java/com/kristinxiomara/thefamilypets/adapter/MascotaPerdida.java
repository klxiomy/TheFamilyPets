package com.kristinxiomara.thefamilypets.adapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.kristinxiomara.thefamilypets.R;
import com.kristinxiomara.thefamilypets.data.PerdidaModel;

public class MascotaPerdida extends AppCompatActivity {

    private FirebaseFirestore firestore;

    RecyclerView listarMascotas;
    PerdidaAdapter perdidaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_perdida);
        firestore = FirebaseFirestore.getInstance();

        listarMascotas = findViewById(R.id.RecyclerMascotP);

        listarMascotas.setLayoutManager(new LinearLayoutManager(this));

        Query query = firestore.collection("MascotasPerdidas");

        FirestoreRecyclerOptions<PerdidaModel> perdidaModelFirestoreRecyclerOptions = new FirestoreRecyclerOptions.Builder<PerdidaModel>().setQuery(query, PerdidaModel.class).build();

        perdidaAdapter = new PerdidaAdapter(perdidaModelFirestoreRecyclerOptions);

        perdidaAdapter.notifyDataSetChanged();

        listarMascotas.setAdapter(perdidaAdapter);
    }
    @Override
    protected void onStart() {
        super.onStart();
        perdidaAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        perdidaAdapter.stopListening();
    }
}