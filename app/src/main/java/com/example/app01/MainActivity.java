package com.example.app01;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.app01.Configuracion.SQLiteConexion;
import com.example.app01.Configuracion.Transacciones;

public class MainActivity extends AppCompatActivity {

    EditText nombres, apellidos, edad, correo;

    ImageView foto;

    Button btnagregar, btnfoto;

    static final int REQUEST_CAMARA_PERMISO = 230;
    static final int REQUEST_IMAGE_CAPTURE = 191;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        nombres = findViewById(R.id.nombres);
        apellidos = findViewById(R.id.apellidos);
        edad = findViewById(R.id.edad);
        correo = findViewById(R.id.correo);
        foto = findViewById(R.id.foto);
        btnagregar = findViewById(R.id.button);
        btnfoto = findViewById(R.id.btnfoto);

        btnagregar.setOnClickListener(v -> AddPerson());

        btnfoto.setOnClickListener(v -> TomarFoto());
    }

    private void TomarFoto() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.CAMERA},
                    REQUEST_CAMARA_PERMISO);
        }else{
            AbrirCamara();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == REQUEST_CAMARA_PERMISO){
            if( grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                AbrirCamara();
            }else{
                Toast.makeText(getApplicationContext(), "Permiso Denegado",
                        Toast.LENGTH_LONG).show();
            }
        }
    }

    private void AbrirCamara() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK){
            Bundle extras = data.getExtras();
            Bitmap img = (Bitmap) extras.get("data");
            foto.setImageBitmap(img);
        }
    }

    private void AddPerson() {
        try{
            SQLiteConexion conexion = new SQLiteConexion(this, Transacciones.dbname, null, Transacciones.dbversion);
            SQLiteDatabase db = conexion.getWritableDatabase();

            ContentValues valores = new ContentValues();
            valores.put(Transacciones.nombres, nombres.getText().toString());
            valores.put(Transacciones.apellidos, apellidos.getText().toString());
            valores.put(Transacciones.edad, edad.getText().toString());
            valores.put(Transacciones.correo, correo.getText().toString());
            valores.put(Transacciones.foto, "");

            long resultado = db.insert(Transacciones.tbpersons, Transacciones.id, valores);

            Toast.makeText(getApplicationContext(), "Registro Ingresado: " + resultado,
                    Toast.LENGTH_LONG).show();

            db.close();

            LimpiarPantalla();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Ha ocurrido un error: " + e.getMessage(),
                    Toast.LENGTH_LONG).show();
        }
    }

    private void LimpiarPantalla(){
        nombres.setText("");
        apellidos.setText("");
        edad.setText("");
        correo.setText("");
        nombres.requestFocus();
    }
}