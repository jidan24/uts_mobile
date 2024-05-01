package com.example.uasmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNIK;
    private EditText editTextNamaLengkap;
    private EditText etTanggalLahir;
    private EditText editTextTempatLahir;
    private EditText editTextAlamat;
    private EditText textViewUsia;
    private Spinner spinnerJenisKelamin;
    private RadioGroup radioGroupKewarganegaraan;
    private CheckBox checkBoxDevelopment, checkBoxAI, checkBoxDesign, checkBoxWriting, checkBoxFinance;
    private EditText editTextEmail;
    private Button buttonReset;
    private Button buttonSubmit;
    private Calendar myCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNIK = findViewById(R.id.editTextText4);
        editTextNamaLengkap = findViewById(R.id.editTextText);
        etTanggalLahir = findViewById(R.id.TanggalLahir);
        editTextTempatLahir = findViewById(R.id.editTextText7);
        editTextAlamat = findViewById(R.id.editTextAddress);
        textViewUsia = findViewById(R.id.editTextText5);
        spinnerJenisKelamin = findViewById(R.id.spinnerGender);
        radioGroupKewarganegaraan = findViewById(R.id.radioGroupNationality);
        checkBoxDevelopment = findViewById(R.id.checkBoxDevelopmentIT);
        checkBoxAI = findViewById(R.id.checkBoxAIService);
        checkBoxDesign = findViewById(R.id.checkBoxDesainCreative);
        checkBoxWriting = findViewById(R.id.checkBoxWriting);
        checkBoxFinance = findViewById(R.id.checkBoxFinanceAccounting);
        editTextEmail = findViewById(R.id.editTextText1);
        buttonReset = findViewById(R.id.buttonReset);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        // Initialize Calendar instance
        myCalendar = Calendar.getInstance();

        // Set up OnClickListener for Date EditText
        etTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        // Set OnClickListener for buttonReset
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetForm();
            }
        });

        // Set OnClickListener for buttonSubmit
        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis()); // Set max date to current date
        datePickerDialog.show();
    }

    // DatePickerDialog.OnDateSetListener
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            calculateAge();
        }
    };

    private void calculateAge() {
        // Get current date
        Calendar today = Calendar.getInstance();

        // Calculate age based on selected date of birth
        int age = today.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < myCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        // Set the calculated age to the EditText
        textViewUsia.setText(String.valueOf(age));
    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        etTanggalLahir.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void resetForm() {
        editTextNIK.setText("");
        editTextNamaLengkap.setText("");
        etTanggalLahir.setText("");
        editTextTempatLahir.setText("");
        editTextAlamat.setText("");
        textViewUsia.setText("");
        spinnerJenisKelamin.setSelection(0);
        radioGroupKewarganegaraan.clearCheck();
        checkBoxDevelopment.setChecked(false);
        checkBoxAI.setChecked(false);
        checkBoxDesign.setChecked(false);
        checkBoxWriting.setChecked(false);
        checkBoxFinance.setChecked(false);
        editTextEmail.setText("");
    }

    private void submitForm() {
        // Mendapatkan data dari form
        String nik = editTextNIK.getText().toString();
        String nama = editTextNamaLengkap.getText().toString();
        String tglLahir = etTanggalLahir.getText().toString();
        String tempatLahir = editTextTempatLahir.getText().toString();
        String alamat = editTextAlamat.getText().toString();
        String usia = textViewUsia.getText().toString();
        String jenisKelamin = spinnerJenisKelamin.getSelectedItem().toString();
        String kewarganegaraan = "";

        int selectedId = radioGroupKewarganegaraan.getCheckedRadioButtonId();
        if (selectedId != -1) {
            RadioButton radioButton = findViewById(selectedId);
            kewarganegaraan = radioButton.getText().toString();
        }

        StringBuilder kompetensi = new StringBuilder();
        if (checkBoxDevelopment.isChecked()) {
            kompetensi.append("Development & IT, ");
        }
        if (checkBoxAI.isChecked()) {
            kompetensi.append("AI Services, ");
        }
        if (checkBoxDesign.isChecked()) {
            kompetensi.append("Design Creative, ");
        }
        if (checkBoxWriting.isChecked()) {
            kompetensi.append("Writing, ");
        }
        if (checkBoxFinance.isChecked()) {
            kompetensi.append("Finance & Accounting");
        }

        String email = editTextEmail.getText().toString();

        // Validate form
        if (nik.isEmpty() || nama.isEmpty() || tglLahir.isEmpty() || tempatLahir.isEmpty() ||
                alamat.isEmpty() || usia.isEmpty() || jenisKelamin.isEmpty() || kewarganegaraan.isEmpty() ||
                kompetensi.toString().isEmpty() || email.isEmpty()) {
            Toast.makeText(MainActivity.this, "Harap lengkapi semua kolom", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validate email format
        if (!email.endsWith("@gmail.com") && !email.endsWith("@mail.com")) {
            Toast.makeText(MainActivity.this, "Format email harus berakhir dengan @gmail.com atau @mail.com", Toast.LENGTH_SHORT).show();
            return;
        }

        // Membuat intent untuk perpindahan data ke SecondActivity
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        // Menambahkan data ke intent
        intent.putExtra("NIK", nik);
        intent.putExtra("NAMA", nama);
        intent.putExtra("TANGGAL_LAHIR", tglLahir);
        intent.putExtra("TEMPAT_LAHIR", tempatLahir);
        intent.putExtra("ALAMAT", alamat);
        intent.putExtra("USIA", usia);
        intent.putExtra("JENIS_KELAMIN", jenisKelamin);
        intent.putExtra("KEWARGANEGARAAN", kewarganegaraan);
        intent.putExtra("KOMPETENSI", kompetensi.toString());
        intent.putExtra("EMAIL", email);

        // Memulai aktivitas SecondActivity dengan intent yang telah dibuat
        startActivity(intent);
    }
}

