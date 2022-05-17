package com.kristinxiomara.thefamilypets.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.kristinxiomara.thefamilypets.R;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Registro_Masc_Encontrada extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    ImageButton imageButton;
    RadioGroup radioGroup;
    RadioButton radioDog, radioCat;
    TextView editRaza, txtLog, txtLat, txtDirect;
    Button btnContinuar, btnUbi;
    Location location;

    private static final int Gallery_Code=1;
    Uri imageUrl=null;
    ProgressDialog progressDialog;

    public String direccion, lag, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_masc_encontrada);

        imageButton = findViewById(R.id.img_subirencont);
        btnContinuar = findViewById(R.id.btn_publicencont);
        editRaza = findViewById(R.id.edt_descencont);
        radioGroup = findViewById(R.id.rg_tipoencont);
        radioDog = findViewById(R.id.rb_dogencont);
        radioCat = findViewById(R.id.rb_catencont);
        btnUbi = findViewById(R.id.btn_ubiencont);
        txtLat = findViewById(R.id.txtLat);
        txtLog = findViewById(R.id.txtLog);
        txtDirect = findViewById(R.id.txtDirect);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1000);
        }else {
            btnUbi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    UbicacionStart();
                }
            });

        }

        btnContinuar = findViewById(R.id.btn_publicencont);

        mDatabase =FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference().child("Mascota encontrada");
        mStorage=FirebaseStorage.getInstance();
        progressDialog=new ProgressDialog(this);

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_Code);
            }
        });
    }


    private void UbicacionStart() {
        LocationManager mlocManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Localizacion Local = new Localizacion();
        Local.setMainActivity(Registro_Masc_Encontrada.this);
        final boolean gpsEnabled = mlocManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!gpsEnabled) {
            Intent settingsIntent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(settingsIntent);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,}, 1000);
            return;
        }
        mlocManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, (LocationListener) Local);
        mlocManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) Local);

        txtLat.setText("Localizacion generada");
        txtLog.setText("");
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                UbicacionStart();
                return;
            }
        }
    }

    public void setLocation(Location loc) {
        //Obtener la direccion de la calle a partir de la latitud y la longitud
        if (loc.getLatitude() != 0.0 && loc.getLongitude() != 0.0) {
            try {
                Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                List<Address> list = geocoder.getFromLocation(
                        loc.getLatitude(), loc.getLongitude(), 1);
                if (!list.isEmpty()) {
                    Address DirCalle = list.get(0);
                    txtDirect.setText(""+DirCalle.getAddressLine(0));
                    direccion=txtDirect.getText().toString();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class Localizacion implements LocationListener {
        Registro_Masc_Encontrada registro_masc_encontrada;

        public Registro_Masc_Encontrada getMainActivity() {
            return registro_masc_encontrada;
        }

        public void setMainActivity(Registro_Masc_Encontrada mainActivity) {
            this.registro_masc_encontrada = mainActivity;
        }

        @Override
        public void onLocationChanged(Location loc) {
            // Este metodo se ejecuta cada vez que el GPS recibe nuevas coordenadas
            // debido a la deteccion de un cambio de ubicacion

            loc.getLatitude();
            loc.getLongitude();

            String Text = "" + loc.getLatitude();
            String Text2 = "" + loc.getLongitude();
            txtLat.setText(Text);
            txtLog.setText(Text2);
            lon=txtLog.getText().toString();
            lag=txtLat.getText().toString();
            this.registro_masc_encontrada.setLocation(loc);
        }

        @Override
        public void onProviderDisabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es desactivado
            txtLat.setText("GPS Desactivado");
        }

        @Override
        public void onProviderEnabled(String provider) {
            // Este metodo se ejecuta cuando el GPS es activado
            txtLat.setText("GPS Activado");
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            switch (status) {
                case LocationProvider.AVAILABLE:
                    Log.d("debug", "LocationProvider.AVAILABLE");
                    break;
                case LocationProvider.OUT_OF_SERVICE:
                    Log.d("debug", "LocationProvider.OUT_OF_SERVICE");
                    break;
                case LocationProvider.TEMPORARILY_UNAVAILABLE:
                    Log.d("debug", "LocationProvider.TEMPORARILY_UNAVAILABLE");
                    break;
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_Code && resultCode == RESULT_OK) {
            imageUrl = data.getData();
            imageButton.setImageURI(imageUrl);
        }


        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String fn=editNombre.getText().toString().trim();
                String ln = editRaza.getText().toString().trim();

                String tipo1 = "";
                String Perro = radioDog.getText().toString();
                String Gato = radioCat.getText().toString();
                if (radioDog.isChecked()) {
                    tipo1 = "Perro";
                } else if (radioCat.isChecked()) {
                    tipo1 = "Gato";
                }

                if (!(ln.isEmpty() && imageUrl != null)) {
                    progressDialog.setTitle("cargando..");
                    progressDialog.show();

                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    String finalTipo = tipo1;
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {
                                    String t = task.getResult().toString();
                                    String tt = finalTipo;
                                    DatabaseReference newPost = mRef.push();
                                    //newPost.child("FirstName").setValue(fn);
                                    newPost.child("LastName").setValue(ln);
                                    newPost.child("image").setValue(task.getResult().toString());
                                    newPost.child("tipo").setValue(tt);
                                    newPost.child("firstName").setValue(direccion);
                                    newPost.child("lag").setValue(lag);
                                    newPost.child("lon").setValue(lon);
                                    progressDialog.dismiss();

                                    Intent intent = new Intent(Registro_Masc_Encontrada.this, MascotaEncontrada.class);
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