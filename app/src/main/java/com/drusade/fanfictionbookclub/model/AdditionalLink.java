
package com.drusade.fanfictionbookclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class AdditionalLink {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("href")
    @Expose
    private String href;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AdditionalLink() {
    }

    /**
     * 
     * @param text
     * @param href
     */
    public AdditionalLink(String text, String href) {
        super();
        this.text = text;
        this.href = href;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
