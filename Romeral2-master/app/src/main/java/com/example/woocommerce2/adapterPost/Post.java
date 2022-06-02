package com.example.woocommerce2.adapterPost;

import android.os.Parcel;
import android.os.Parcelable;

public class Post implements Parcelable {

    private int id;
    private String precio, precioIva, title, content, feature_image;

    public Post(){}

    protected Post(Parcel in){
        id = in.readInt();
        precio = in.readString();
        precioIva = in.readString();
        title = in.readString();
        content = in.readString();
        feature_image = in.readString();
    }

    public static final Creator<Post> CREATOR = new Creator<Post>() {
        @Override
        public Post createFromParcel(Parcel in) {
            return new Post(in);
        }

        @Override
        public Post[] newArray(int size) {
            return new Post[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getPrecioIva() {
        return precioIva;
    }

    public void setPrecioIva(String precioIva) {
        this.precioIva = precioIva;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFeature_image() {
        return feature_image;
    }

    public void setFeature_image(String feature_image) {
        this.feature_image = feature_image;
    }

    public static Creator<Post> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(precio);
        parcel.writeString(precioIva);
        parcel.writeString(title);
        parcel.writeString(content);
        parcel.writeString(feature_image);
    }


}
