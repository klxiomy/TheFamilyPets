package com.kristinxiomara.thefamilypets.adapter;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kristinxiomara.thefamilypets.R;

import javax.annotation.Nullable;

public class Registro_Denuncia_Masc extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageView imageButton, mostrarimg;
    EditText txtTitilo,txtDescripcion;
    Button btnInsertar;
    private  static  final int Gallery_Code=1;
    Uri imageUrl =null;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_denuncia_masc);
        imageButton = findViewById(R.id.img_subirdenun);
        txtTitilo = findViewById(R.id.edt_titledenun);
        txtDescripcion = findViewById(R.id.edt_descdenun);
        btnInsertar = findViewById(R.id.btn_publicdenun);
        mostrarimg = findViewById(R.id.img_mostrarimg);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Denuncia");
        mStorage = FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Gallery_Code && resultCode == RESULT_OK){
            imageUrl=data.getData();
            mostrarimg.setImageURI(imageUrl);
        }


        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String titulo= txtTitilo.getText().toString().trim();
                String descrip= txtDescripcion.getText().toString().trim();

                if (!(titulo.isEmpty() && descrip.isEmpty() && imageUrl !=null)){
                    progressDialog.setTitle("Cargandooo");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task<Uri> downloadUrl=taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t=task.getResult().toString();
                                    DatabaseReference newPost=mRef.push();
                                    newPost.child("titulo").setValue(titulo);
                                    newPost.child("descricion").setValue(descrip);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    progressDialog.dismiss();
                                    Intent intent=new Intent(Registro_Denuncia_Masc.this,DenunciaMascota.class);
                                    startActivity(intent);


                                }
                            });
                        }
                    });
                }
            }
        });
    }
}