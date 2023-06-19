package com.example.ziyanapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Update extends AppCompatActivity implements View.OnClickListener {

    private EditText edtnamabarang, edtmerkbarang, edtjenisbarang, edtstok, edtharga;
    private Button btnUpdate;
    private Button btnDelete;
    public static final String EXTRA_PRODUCT = "extra_product";
    public final int ALERT_DIALOG_CLOSE = 10;
    private product product;
    private String productId;
    DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        edtnamabarang = findViewById(R.id.edt_edit_namabarang);
        edtmerkbarang = findViewById(R.id.edt_edit_merk);
        edtjenisbarang = findViewById(R.id.edt_edit_jenisbarang);
        edtstok = findViewById(R.id.edt_edit_stok);
        edtharga = findViewById(R.id.edt_edit_harga);
        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);
        btnDelete = findViewById(R.id.action_delete);
        btnDelete.setOnClickListener(this);
        product = getIntent().getParcelableExtra(EXTRA_PRODUCT);
        if (product != null) {
            productId = product.getId();
        } else {
            product = new product();
        }
        if (product != null) {
            edtnamabarang.setText(product.getNamabarang());
            edtmerkbarang.setText(product.getMerkbarang());
            edtjenisbarang.setText(product.getJenisbarang());
            edtstok.setText(product.getStok());
            edtharga.setText(product.getHarga());
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_update) {
            updateProduct();
        }
        if (view.getId() == R.id.action_delete) {
            deleteProduct();
        }
    }
    private void updateProduct() {
        String namabarang = edtnamabarang.getText().toString().trim();
        String merkbarang = edtmerkbarang.getText().toString().trim();
        String jenisbarang = edtjenisbarang.getText().toString().trim();
        String stokbarang = edtstok.getText().toString().trim();
        String hargabarang = edtharga.getText().toString().trim();
        boolean isEmptyFields = false;
        if (TextUtils.isEmpty(namabarang)) {
            isEmptyFields = true;
            edtnamabarang.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(merkbarang)) {
            isEmptyFields = true;
            edtmerkbarang.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(jenisbarang)) {
            isEmptyFields = true;
            edtjenisbarang.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(stokbarang)) {
            isEmptyFields = true;
            edtstok.setError("Field ini tidak boleh kosong");
        }
        if (TextUtils.isEmpty(hargabarang)) {
            isEmptyFields = true;
            edtharga.setError("Field ini tidak boleh kosong");
        }
        if (! isEmptyFields) {
            Toast.makeText(Update.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
            product.setNamabarang(namabarang);
            product.setMerkbarang(merkbarang);
            product.setJenisbarang(jenisbarang);
            product.setStok(stokbarang);
            product.setHarga(hargabarang);
            DatabaseReference dbproduct = mDatabase.child("product");
            //update data
            dbproduct.child(productId).setValue(product);
            finish();
        }
    }
    private void deleteProduct(){
        DatabaseReference dbproduct = mDatabase.child("product").child(productId);
        dbproduct.removeValue();
        Toast.makeText(Update.this, "Deleted Successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
    @Override
    public void onBackPressed() {

        showAlertDialog(ALERT_DIALOG_CLOSE);
    }
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        dialogTitle = "Batal";
        dialogMessage = "Apakah anda ingin membatalkan perubahan pada form?";
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