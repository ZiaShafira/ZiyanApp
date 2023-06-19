package com.example.ziyanapp;

import android.os.Parcel;
import android.os.Parcelable;

public class product implements Parcelable {
    private String id;
    private String namabarang;
    private String merkbarang;
    private String jenisbarang;
    private String stok;
    private String harga;
    public product() {}
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getNamabarang() {
        return namabarang;
    }
    public void setNamabarang(String namabarang) {
        this.namabarang = namabarang;
    }
    public String getMerkbarang() {return merkbarang;}
    public void setMerkbarang(String merkbarang) {
        this.merkbarang = merkbarang;
    }
    public String getJenisbarang() {return jenisbarang;}
    public void setJenisbarang(String jenisbarang) {
        this.jenisbarang = jenisbarang;
    }
    public String getStok() {
        return stok;
    }
    public void setStok(String stok) {
        this.stok = stok;
    }
    public String getHarga() {
        return harga;
    }
    public void setHarga(String harga) {
        this.harga = harga;
    }
    @Override
    public int describeContents() {
        return 0;
    }
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.namabarang);
        dest.writeString(this.merkbarang);
        dest.writeString(this.jenisbarang);
        dest.writeString(this.stok);
        dest.writeString(this.harga);
    }
    protected product(Parcel in) {
        this.id = in.readString();
        this.namabarang = in.readString();
        this.merkbarang = in.readString();
        this.jenisbarang = in.readString();
        this.stok = in.readString();
        this.harga = in.readString();
    }
    public static final Parcelable.Creator<product> CREATOR = new Parcelable.Creator<product>() {
        @Override
        public product createFromParcel(Parcel source) {
            return new product(source);
        }
        @Override
        public product[] newArray(int size) {return new product[size];}


    };
}
