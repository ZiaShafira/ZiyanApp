package com.example.ziyanapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Create extends AppCompatActivity implements View.OnClickListener {

    private EditText edtnamabarang, edtmerk, edtjenisbarang, edtstok, edtharga;
    private Button btninsert;
    private product product;
    public final int ALERT_DIALOG_CLOSE = 10;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtnamabarang = findViewById(R.id.edt_namabarang);
        edtmerk = findViewById(R.id.edt_merk);
        edtjenisbarang = findViewById(R.id.edt_jenisbarang);
        edtstok = findViewById(R.id.edt_stok);
        edtharga = findViewById(R.id.edt_harga);
        btninsert = findViewById(R.id.btn_insert);
        btninsert.setOnClickListener(this);
        product = new product();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_insert) {
            saveproduct();
        }
    }
    private void saveproduct()
    {
        String namabarang = edtnamabarang.getText().toString().trim();
        String merkbarang = edtmerk.getText().toString().trim();
        String jenisbarang = edtjenisbarang.getText().toString().trim();
        String stok = edtstok.getText().toString().trim();
        String harga = edtharga.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(namabarang)) {
            isEmptyFields = true;
            edtnamabarang.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(merkbarang)) {
            isEmptyFields = true;
            edtmerk.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(jenisbarang)) {
            isEmptyFields = true;
            edtjenisbarang.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(stok)) {
            isEmptyFields = true;
            edtstok.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(harga)) {
            isEmptyFields = true;
            edtharga.setError("Field ini tidak boleh kosong");
        }
        if (! isEmptyFields) {
            Toast.makeText(Create.this, "Successfully Insert Data", Toast.LENGTH_SHORT).show();
            DatabaseReference dbproduct = mDatabase.child("product");
            String id = dbproduct.push().getKey();
            product.setId(id);
            product.setNamabarang(namabarang);
            product.setMerkbarang(merkbarang);
            product.setJenisbarang(jenisbarang);
            product.setStok(stok);
            product.setHarga(harga);
            //insert data
            dbproduct.child(id).setValue(product);
            finish();
        }
    }
    @Override
    public void onBackPressed() {

        showAlertDialog(ALERT_DIALOG_CLOSE);
    }
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        dialogTitle = "Batal";
        dialogMessage = "Apakah anda ingin membatalkan pengisian pada form?";
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(dialogTitle);
        alertDialogBuilder.setMessage(dialogMessage).setCancelable(false).setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (isDialogClose) {
                    finish();
                }
            }
        }).setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
}