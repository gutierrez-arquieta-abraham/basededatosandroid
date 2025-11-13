package com.example.basededatos;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText etcodigo,etdescripcion, etprecio;
    Button btna,btnb,btnco,btnca;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        etcodigo = findViewById(R.id.ecodigo);
        etdescripcion = findViewById(R.id.edescripcion);
        etprecio = findViewById(R.id.eprecio);

        btna = findViewById(R.id.altas);
        btnb = findViewById(R.id.bajas);
        btnco = findViewById(R.id.consultas);
        btnca = findViewById(R.id.cambios);

        btna.setOnClickListener(this);
        btnb.setOnClickListener(this);
        btnca.setOnClickListener(this);
        btnco.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String cadenita = ((Button)v).getText().toString();
        if (cadenita.equals("Altas")) {
            etcodigo.setEnabled(true);
            etdescripcion.setEnabled(true);
            etprecio.setEnabled(true);
            btnb.setEnabled(false);
            btnco.setEnabled(false);
            btnca.setEnabled(false);
            btna.setText("Agregar");
        } else if (cadenita.equals("Agregar")) {
            Base admin = new Base(this, "administracion",null,1);
            SQLiteDatabase basesita = admin.getWritableDatabase();
            int llavesita = Integer.parseInt(etcodigo.getText().toString());
            String titulo = etdescripcion.getText().toString();
            int precio = Integer.parseInt(etprecio.getText().toString());
            ContentValues registro = new ContentValues();
            registro.put("clave", llavesita);
            registro.put("descripcion", titulo);
            registro.put("precio", precio);
            basesita.insert("basesita",null,registro);
            basesita.close();

            etcodigo.setText("");
            etdescripcion.setText("");
            etprecio.setText("");

            etcodigo.setEnabled(false);
            etdescripcion.setEnabled(false);
            etprecio.setEnabled(false);
            btnb.setEnabled(true);
            btnco.setEnabled(true);
            btnca.setEnabled(true);
            btna.setText("Altas");
            Toast.makeText(this, "Registro Agregado", Toast.LENGTH_SHORT).show();
        }

    }
}