package com.kristinxiomara.thefamilypets.adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kristinxiomara.thefamilypets.R;

import java.util.HashMap;
import java.util.Map;

public class Registro_Masc_Perdida extends AppCompatActivity {

    Button publicarperd;

    TextInputEditText nombperd, colorperd, razaperd, diasperd, recompensa, contactoperd, descperd;

    ImageView selectperd;

    private FirebaseFirestore db;

    private StorageReference reference;

    public Uri uriPerdida;

    RadioGroup tipo;

    public String tipoMP;

    private static final int Gallery_Intent = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_masc_perdida);
        reference = FirebaseStorage.getInstance().getReference();
        db = FirebaseFirestore.getInstance();

        nombperd = (TextInputEditText) findViewById(R.id.edt_nombperd);
        colorperd = (TextInputEditText) findViewById(R.id.edt_colorperd);
        razaperd = (TextInputEditText) findViewById(R.id.edt_razaperd);
        diasperd = (TextInputEditText) findViewById(R.id.edt_diasperd);
        recompensa = (TextInputEditText) findViewById(R.id.edt_recompensa);
        contactoperd = (TextInputEditText) findViewById(R.id.edt_contactoperd);
        descperd = (TextInputEditText) findViewById(R.id.edt_descperd);
        tipo = (RadioGroup) findViewById(R.id.rg_tipoperd);
        selectperd = (ImageView) findViewById(R.id.img_selectperd);

        selectperd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent selecImg = new Intent(Intent.ACTION_PICK);
                selecImg.setType("image/*");
                startActivityForResult(selecImg, Gallery_Intent);
            }
        });

        tipo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {

                switch (i){
                    case R.id.rb_catperd:
                        tipoMP ="Gato";
                        break;
                    case R.id.rb_dogperd:
                        tipoMP ="Perro";
                        break;
                }
            }
        });

        publicarperd = findViewById(R.id.btn_publicperd);

        publicarperd.setBackgroundColor(getColor(R.color.blueback));
        publicarperd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicarperd.setBackgroundColor(getColor(R.color.oscuro));

                String nombreM = nombperd.getText().toString().trim();
                String colorM = colorperd.getText().toString().trim();
                String razaM = razaperd.getText().toString().trim();
                String diasP = diasperd.getText().toString().trim();
                String descripcionM = descperd.getText().toString().trim();
                String numeroContacto = contactoperd.getText().toString();
                String recompensaM = recompensa.getText().toString();
                String estado = "Perdido";

                StorageReference filePath = reference.child("Fotos").child(uriPerdida.getLastPathSegment());

                filePath.putFile(uriPerdida).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        Toast.makeText(Registro_Masc_Perdida.this, "Se subio exitosamente", Toast.LENGTH_SHORT).show();
                        Task<Uri> downloadURL =taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(Task<Uri> task) {
                                String foto = task.getResult().toString();

                                if (nombreM.isEmpty() && colorM.isEmpty() && razaM.isEmpty() && diasP.isEmpty() && descripcionM.isEmpty()){
                                    Toast.makeText(Registro_Masc_Perdida.this, "Rellene todos los campos", Toast.LENGTH_SHORT).show();
                                }else {
                                    pubMas(nombreM, colorM, razaM, diasP, descripcionM, tipoMP, foto, numeroContacto, estado, recompensaM);
                                }

                                Intent intent = new Intent(Registro_Masc_Perdida.this,MascotaPerdida.class);
                                startActivity(intent);
                            }
                        });

                    }
                });
            }
        });


    }

    private void pubMas(String nombreM, String colorM, String razaM, String diasP, String descripcionM, String tipoM, String foto, String numeroContacto, String estado, String recompensaM) {
        Map<String, Object> mascotaPerdida = new HashMap<>();
        mascotaPerdida.put("nombre", nombreM);
        mascotaPerdida.put("color", colorM);
        mascotaPerdida.put("raza", razaM);
        mascotaPerdida.put("diasP", diasP);
        mascotaPerdida.put("tipo", tipoM);
        mascotaPerdida.put("descripcion", descripcionM);
        mascotaPerdida.put("foto", foto);
        mascotaPerdida.put("contacto", "57"+numeroContacto);
        mascotaPerdida.put("recompensa", recompensaM);
        mascotaPerdida.put("estado", estado);

        db.collection("MascotasPerdidas").add(mascotaPerdida).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(Registro_Masc_Perdida.this, "Publicado Exitosamente", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Gallery_Intent && resultCode == RESULT_OK){
            uriPerdida = data.getData();
            selectperd.setImageURI(uriPerdida);
        }
    }
}