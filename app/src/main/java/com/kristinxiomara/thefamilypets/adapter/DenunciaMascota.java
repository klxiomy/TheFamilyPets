package com.kristinxiomara.thefamilypets.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.kristinxiomara.thefamilypets.R;
import com.kristinxiomara.thefamilypets.data.DenunciaModel;

import java.util.ArrayList;
import java.util.List;

public class DenunciaMascota extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    DenunciaAdapter denunciaAdapter;
    List<DenunciaModel> modelListDenuncia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_denuncia_mascota);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Denuncia");
        mStorage = FirebaseStorage.getInstance();
        recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        modelListDenuncia   = new ArrayList<DenunciaModel>();
        denunciaAdapter= new DenunciaAdapter(DenunciaMascota.this,modelListDenuncia);
        recyclerView.setAdapter(denunciaAdapter);
        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                DenunciaModel denunciaModel= snapshot.getValue(DenunciaModel.class);
                modelListDenuncia.add(denunciaModel);
                denunciaAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }
}