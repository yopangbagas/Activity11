package com.example.activity11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EditBarang extends AppCompatActivity {
    TextView tv_Key;
    EditText ed_Nama;
    Button btnEdit;
    DatabaseReference databaseReference;
    String kode, nama, key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_barang);

        tv_Key = findViewById(R.id.tv_key);
        ed_Nama = findViewById(R.id.edNama);
        btnEdit = findViewById(R.id.btEdit);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        Bundle bundle = this.getIntent().getExtras();
        kode = bundle.getString("Kunci1");
        nama = bundle.getString("Kunci2");
        key = bundle.getString("Kunci3");

        tv_Key.setText(key);
        ed_Nama.setText(nama);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editBarang(new Barang(kode, ed_Nama.getText().toString()));
            }
        });
    }

    public void editBarang(Barang barang) {
        databaseReference.child("Barang")
                .child(key)
                .setValue(barang)
                .addOnSuccessListener(this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(EditBarang.this, "Data Berhasil diupdate", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
    }
}