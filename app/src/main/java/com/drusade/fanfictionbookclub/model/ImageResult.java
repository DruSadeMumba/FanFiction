
package com.drusade.fanfictionbookclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class ImageResult {

    @SerializedName("image")
    @Expose
    private Image image;
    @SerializedName("link")
    @Expose
    private Link link;

    /**
     * No args constructor for use in serialization
     * 
     */
    public ImageResult() {
    }

    /**
     * 
     * @param image
     * @param link
     */
    public ImageResult(Image image, Link link) {
        super();
        this.image = image;
        this.link = link;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

}
