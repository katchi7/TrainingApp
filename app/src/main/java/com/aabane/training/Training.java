package com.aabane.training;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.ByteArrayOutputStream;


public class Training {
    private int id = 0;
    private String name;
    private byte[] image;

    public Training(String name,Bitmap image){
        super();
        this.id = id;
        this.image = getBitmapAsByteArray(image) ;
        this.name = name;
    }
    public Training(int id, String name,byte[] image){
        super();
        this.id = id;
        this.image = image;
        this.name = name;
    }

    protected Training(Parcel in) {
        id = in.readInt();
        name = in.readString();
        image = in.createByteArray();
    }


    public byte[] getImage() {
        return image;
    }
    public Bitmap getBitmapImage() {
        return BitmapFactory.decodeByteArray(image,0,image.length) ;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setImage(Bitmap image) {
        this.image = getBitmapAsByteArray(image) ;
    }

    public void setName(String name) {
        this.name = name;
    }
    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }

}
