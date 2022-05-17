package com.kristinxiomara.thefamilypets.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.kristinxiomara.thefamilypets.R;
import com.kristinxiomara.thefamilypets.adapter.AyudaMascota;
import com.kristinxiomara.thefamilypets.adapter.DenunciaMascota;
import com.kristinxiomara.thefamilypets.adapter.MascotaEncontrada;
import com.kristinxiomara.thefamilypets.adapter.MascotaPerdida;

public class Menu extends AppCompatActivity {

    ConstraintLayout card_mascper, card_mascenc, card_ayuda, card_denuncias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        card_mascper = findViewById(R.id.card_mascper);
        card_mascenc = findViewById(R.id.card_mascenc);
        card_ayuda = findViewById(R.id.card_ayuda);
        card_denuncias = findViewById(R.id.card_denuncias);

        card_mascper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MascotaPerdida.class);
                startActivity(intent);
            }
        });

        card_mascenc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, MascotaEncontrada.class);
                startActivity(intent);
            }
        });

        card_ayuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, AyudaMascota.class);
                startActivity(intent);
            }
        });

        card_denuncias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Menu.this, DenunciaMascota.class);
                startActivity(intent);
            }
        });

    }
}