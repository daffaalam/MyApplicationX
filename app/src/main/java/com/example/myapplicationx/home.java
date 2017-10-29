package com.example.myapplicationx;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class home extends AppCompatActivity {

    int size  = 35;
    RadioButton laki;
    RadioButton perempuan;
    String rb_jk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button submit = (Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isidata();
            }
        });
    }

    public void isidata() {

        TextView tampildata = (TextView) findViewById(R.id.data);

        laki = (RadioButton) findViewById(R.id.lk);
        perempuan = (RadioButton) findViewById(R.id.pr);

        EditText nama = (EditText) findViewById(R.id.nama);
        EditText kelas = (EditText) findViewById(R.id.kelas);
        EditText telpon = (EditText) findViewById(R.id.telpon);

        String snama = nama.getText().toString();
        String skelas = kelas.getText().toString();
        String stelpon = telpon.getText().toString();

        CheckBox baca = (CheckBox) findViewById(R.id.baca);
        CheckBox olga = (CheckBox) findViewById(R.id.olga);
        CheckBox music = (CheckBox) findViewById(R.id.music);

        if (snama.isEmpty()) {
            nama.setError("Nama harus diisi.");
            nama.requestFocus();
        } else if (!snama.isEmpty() && skelas.isEmpty()) {
            kelas.setError("Kelas harus diisi.");
            kelas.requestFocus();
        } else if (!skelas.isEmpty() && stelpon.isEmpty()) {
            telpon.setError("Telepon harus diisi.");
            telpon.requestFocus();
        } else if (!Patterns.PHONE.matcher(stelpon).matches()) {
            telpon.setError("Telepon idak valid.");
            telpon.setText("");
            telpon.requestFocus();
        } else if (!laki.isChecked() && !perempuan.isChecked()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder
                    .setTitle("PERINGATAN!")
                    .setMessage("Pilih salah satu jenis kelamin Anda.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            laki.requestFocus();
                        }
                    });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        } else if (laki.isChecked() && !perempuan.isChecked()) {
            rb_jk = "Laki - Laki";
        } else if (!laki.isChecked() && perempuan.isChecked()) {
            rb_jk = "Perempuan";
        }

        else {
            String datas =
                            "Nama : " + snama
                            + "\nKelas : " + skelas
                            + "\nTelpon : " + stelpon
                            + "\nJenis Kelamin : " + rb_jk
                            + "\nUkuran Sepatu : " + size;

            tampildata.setText(datas);
        }
    }

    public void ukuran(int uk) {
        TextView uks = (TextView) findViewById(R.id.ukspt);
        uks.setText(""+uk);
    }

    public void pengurangan(View view) {
        if (size <= 20) {
            Toast.makeText(this, "ukuran Sepatu Terlalu Kecil", Toast.LENGTH_SHORT).show();
            return;
        }
        size = size - 1;
        ukuran(size);
    }

    public void pertambahan(View view) {
        if (size >= 50) {
            Toast.makeText(this, "ukuran Sepatu Terlalu Besar", Toast.LENGTH_SHORT).show();
            return;
        }
        size = size + 1;
        ukuran(size);
    }
}
