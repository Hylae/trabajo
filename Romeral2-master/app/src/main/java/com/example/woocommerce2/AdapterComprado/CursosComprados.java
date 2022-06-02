package com.example.woocommerce2.AdapterComprado;

import android.os.Parcel;
import android.os.Parcelable;

public class CursosComprados implements Parcelable {

    private int id;
    private String titulo;

    public CursosComprados(){}

    protected CursosComprados(Parcel in){
        id = in.readInt();
        titulo = in.readString();
    }

    public static final Creator<CursosComprados> CREATOR = new Creator<CursosComprados>() {
        @Override
        public CursosComprados createFromParcel(Parcel in) {
            return new CursosComprados(in);
        }

        @Override
        public CursosComprados[] newArray(int size) {
            return new CursosComprados[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static Creator<CursosComprados> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(titulo);
    }
}
