package com.exe.paradox;

public class Member {
    private String name, about, drawable;

    public Member(String name, String about, String drawable) {
        this.name = name;
        this.drawable = drawable;
        this.about = about;
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
