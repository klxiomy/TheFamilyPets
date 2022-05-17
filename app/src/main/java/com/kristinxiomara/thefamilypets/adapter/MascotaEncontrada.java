package com.kristinxiomara.thefamilypets.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.kristinxiomara.thefamilypets.R;
import com.kristinxiomara.thefamilypets.data.EncontradaModel;

import java.util.ArrayList;
import java.util.List;

public class MascotaEncontrada extends AppCompatActivity {

    FloatingActionsMenu floatMenu;
    FloatingActionButton btnFormulario, btnInicio;

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    RecyclerView recyclerView;
    EncontradaAdapter encontradaAdapter;
    List<EncontradaModel> encontradaMdlList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mascota_encontrada);

        mDatabase =FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Mascota encontrada");
        mStorage=FirebaseStorage.getInstance();

        recyclerView=findViewById(R.id.recyclerview_id);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        encontradaMdlList=new ArrayList<EncontradaModel>();
        encontradaAdapter=new EncontradaAdapter(MascotaEncontrada.this,encontradaMdlList);

        recyclerView.setAdapter(encontradaAdapter);

        mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                EncontradaModel encontradaModel=snapshot.getValue(EncontradaModel.class);
                encontradaMdlList.add(encontradaModel);
                encontradaAdapter.notifyDataSetChanged();
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

        referenciar();

    }

    private void referenciar() {
        btnFormulario = findViewById(R.id.btnFormulario);
        btnFormulario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MascotaEncontrada.this, Registro_Masc_Encontrada.class);
                startActivity(intent);
            }
        });

        btnInicio = findViewById(R.id.btnInicio);
        btnInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MascotaEncontrada.this, Menu.class);
                startActivity(intent2);
            }
        });
    }
}