package com.exe.paradox;

public class Member {
    private String name, about;
    private int drawable;

    public Member(String name, String about, int drawable) {
        this.name = name;
        this.drawable = drawable;
        this.about = about;
    }

    public String getName() {
        return name;
    }

    public int getDrawable() {
        return drawable;
    }

    public String getAbout() {
        return about;
    }
}
