package com.example.nav.ui.slideshow;

// GalleryItem.java
public class GalleryItem {
    private int imageResId;
    private String title;

    public GalleryItem(int imageResId, String title) {
        this.imageResId = imageResId;
        this.title = title;
    }

    public int getImageResId() {
        return imageResId;
    }

    public String getTitle() {
        return title;
    }
}

