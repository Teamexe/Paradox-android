package com.exe.paradox.model;

/**
 * Created by shasha on 7/4/18.
 */

public class FeaturedProject {
    String title, desc, link, drawablePath;

    public FeaturedProject(String title, String desc, String link, String drawablePath) {
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

    public String getDrawablePath() {
        return drawablePath;
    }
}
