package com.exe.paradox;

public class Event {
    String name, desc;
    int path;

    public Event(String name, String desc, int path) {
        this.name = name;
        this.desc = desc;
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public int getPath() {
        return path;
    }
}
