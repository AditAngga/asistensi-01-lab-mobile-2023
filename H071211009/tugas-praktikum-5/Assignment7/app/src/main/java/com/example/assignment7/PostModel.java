package com.example.assignment7;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class PostModel implements Parcelable {

    String caption;
    Uri image;

    //Constructor kosong agar tidak langsung nge-set value. set valuenya di AddFragment (saat tombol upload di klik)
    //khusus caption
    public PostModel() {

    }

    public PostModel(String caption, Uri image) {
        this.caption = caption;
        this.image = image;
    }

    //parcelable
    protected PostModel(Parcel in) {
        caption = in.readString();
        image = in.readParcelable(Uri.class.getClassLoader());
    }

    public static final Creator<PostModel> CREATOR = new Creator<PostModel>() {
        @Override
        public PostModel createFromParcel(Parcel in) {
            return new PostModel(in);
        }

        @Override
        public PostModel[] newArray(int size) {
            return new PostModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(caption);
        parcel.writeParcelable(image, i);
    }

    //getter n setter
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}