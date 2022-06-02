package com.example.woocommerce2.validacionUsuario;

import android.os.Parcel;
import android.os.Parcelable;

public class Persona implements Parcelable {

    private String nombre;
    private boolean dentro;
    private int id;

    public Persona() {}

    public Persona(String nombre, boolean dentro, int id) {
        this.nombre = nombre;
        this.dentro = dentro;
        this.id = id;
    }

    protected Persona(Parcel in){
        nombre = in.readString();
        dentro = in.readByte() != 0;
        id = in.readInt();
    }

    public static final Creator<Persona> CREATOR = new Creator<Persona>() {
        @Override
        public Persona createFromParcel(Parcel in) {
            return new Persona(in);
        }

        @Override
        public Persona[] newArray(int size) {
            return new Persona[size];
        }
    };


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isDentro() {
        return dentro;
    }

    public void setDentro(boolean dentro) {
        this.dentro = dentro;
    }


    public static Creator<Persona> getCREATOR() {
        return CREATOR;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
