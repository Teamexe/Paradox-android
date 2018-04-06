package com.exe.paradox.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Member implements Parcelable{
    private String name, about, drawable;

    public Member(String name, String about, String drawable) {
        this.name = name;
        this.drawable = drawable;
        this.about = about;
    }

    protected Member(Parcel in) {
        name = in.readString();
        about = in.readString();
        drawable = in.readString();
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(about);
        dest.writeString(drawable);
    }

    public String getName() {
        return name;
    }

    public String getDrawable() {
        return drawable;
    }

    public String getAbout() {
        return about;
    }
}
