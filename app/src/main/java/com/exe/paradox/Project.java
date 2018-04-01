package com.exe.paradox;

/**
 * Created by shasha on 1/4/18.
 */

public class Project {
    String title, desc, link;
    int drawablePath;

    public Project(String title, String desc, String link, int drawablePath) {
        this.title = title;
        this.desc = desc;
        this.link = link;
        this.drawablePath = drawablePath;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public int getDrawablePath() {
        return drawablePath;
    }
}
