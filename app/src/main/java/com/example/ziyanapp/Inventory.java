package com.example.ziyanapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class Inventory extends AppCompatActivity  {

    private ListView listView;
    private ArrayList<product> productList;
    public final int ALERT_DIALOG_CLOSE = 10;
    DatabaseReference dbproduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);
        listView = findViewById(R.id.lv_list);
        dbproduct = FirebaseDatabase.getInstance().getReference("product");
        productList = new ArrayList<>();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Inventory.this, Update.class);
                intent.putExtra(Update.EXTRA_PRODUCT, productList.get(i));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        dbproduct.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot productSnapshot :
                        dataSnapshot.getChildren()) {
                    product product = productSnapshot.getValue(product.class);
                    productList.add(product);
                }
                ProductAdapter adapter = new ProductAdapter(Inventory.this);
                adapter.setProductList(productList);
                listView.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Inventory.this, "Terjadi kesalahan.", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onBackPressed() {

        showAlertDialog(ALERT_DIALOG_CLOSE);
    }
    private void showAlertDialog(int type) {
        final boolean isDialogClose = type == ALERT_DIALOG_CLOSE;
        String dialogTitle, dialogMessage;
        dialogTitle = "Back";
        dialogMessage = "Apakah anda yakin ingin kembali?";
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