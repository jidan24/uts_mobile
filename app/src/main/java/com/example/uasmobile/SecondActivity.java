package com.example.uasmobile;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        // Mendapatkan data dari Intent
        Intent intent = getIntent();
        String nik = intent.getStringExtra("NIK");
        String namaLengkap = intent.getStringExtra("NAMA");
        String tanggalLahir = intent.getStringExtra("TANGGAL_LAHIR");
        String tempatLahir = intent.getStringExtra("TEMPAT_LAHIR");
        String alamat = intent.getStringExtra("ALAMAT");
        String usia = intent.getStringExtra("USIA");
        String jenisKelamin = intent.getStringExtra("JENIS_KELAMIN");
        String kewarganegaraan = intent.getStringExtra("KEWARGANEGARAAN");
        String kompetensi = intent.getStringExtra("KOMPETENSI");
        String email = intent.getStringExtra("EMAIL");

        // Menampilkan data di TextView
        TextView textViewNik = findViewById(R.id.textViewNik);
        textViewNik.setText(nik);

        TextView textViewNama = findViewById(R.id.textViewNama);
        textViewNama.setText(namaLengkap);

        TextView textViewTglLahir = findViewById(R.id.textViewTglLahir);
        textViewTglLahir.setText(tanggalLahir);

        TextView textViewTempatLahir = findViewById(R.id.textViewTempatLahir);
        textViewTempatLahir.setText(tempatLahir);

        TextView textViewAlamat = findViewById(R.id.textViewAlamat);
        textViewAlamat.setText(alamat);

        TextView textViewUsia = findViewById(R.id.textViewUsia);
        textViewUsia.setText(usia);

        TextView textViewJenisKelamin = findViewById(R.id.textViewJenisKelamin);
        textViewJenisKelamin.setText(jenisKelamin);

        TextView textViewKewarganegaraan = findViewById(R.id.textViewKewarganegaraan);
        textViewKewarganegaraan.setText(kewarganegaraan);

        TextView textViewKompetensi = findViewById(R.id.textViewKompetensi);
        textViewKompetensi.setText(kompetensi);

        TextView textViewEmail = findViewById(R.id.textViewEmail);
        textViewEmail.setText(email);

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());

    }
}
