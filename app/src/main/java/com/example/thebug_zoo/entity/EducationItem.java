package com.example.thebug_zoo.entity;

public class EducationItem {
    private final int imageResourse;
    private final String title;
    private final String desc;
    private boolean isShrink = true;

    public EducationItem(int imageResourse, String title, String desc) {
        this.imageResourse = imageResourse;
        this.title = title;
        this.desc = desc;
    }

    public int getImageResourse() {
        return imageResourse;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public boolean isShrink() {
        return isShrink;
    }

    public void setShrink(boolean shrink) {
        isShrink = shrink;
    }
}
