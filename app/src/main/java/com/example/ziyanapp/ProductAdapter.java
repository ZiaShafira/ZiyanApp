package com.example.ziyanapp;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;
public class ProductAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<product> productList = new ArrayList<>();
    public void setProductList(ArrayList<product> productList) {
        this.productList = productList;
    }
    public ProductAdapter(Context context) {
        this.context = context;
    }
    @Override
    public int getCount() {
        return productList.size();
    }
    @Override
    public Object getItem(int i) {
        return productList.get(i);
    }
    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View itemView = view;
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);}
        ViewHolder viewHolder = new ViewHolder(itemView);
        product product = (product) getItem(i);
        viewHolder.bind(product);
        return itemView;
    }
    private class ViewHolder {
        private TextView txtNama, txtMerk, txtJenis, txtStok, txtHarga ;
        ViewHolder(View view) {
            txtNama = view.findViewById(R.id.txt_namabarang);
            txtMerk = view.findViewById(R.id.txt_merkbarang);
            txtJenis = view.findViewById(R.id.txt_jenisbarang);
            txtStok = view.findViewById(R.id.txt_stok);
            txtHarga = view.findViewById(R.id.txt_harga);
        }
        void bind(product product) {
            txtNama.setText(product.getNamabarang());
            txtMerk.setText(product.getMerkbarang());
            txtJenis.setText(product.getJenisbarang());
            txtStok.setText(product.getStok());
            txtHarga.setText(product.getHarga());
        }
    }
}
