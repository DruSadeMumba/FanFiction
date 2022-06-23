
package com.drusade.fanfictionbookclub.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Result {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("additional_links")
    @Expose
    private List<AdditionalLink> additionalLinks = null;
    @SerializedName("cite")
    @Expose
    private Cite cite;
    private String pushId;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Result() {
    }

    /**
     * 
     * @param additionalLinks
     * @param link
     * @param description
     * @param cite
     * @param title
     */
    public Result(String title, String link, String description, List<AdditionalLink> additionalLinks, Cite cite) {
        super();
        this.title = title;
        this.link = link;
        this.description = description;
        this.additionalLinks = additionalLinks;
        this.cite = cite;
        this.pushId = pushId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<AdditionalLink> getAdditionalLinks() {
        return additionalLinks;
    }

    public void setAdditionalLinks(List<AdditionalLink> additionalLinks) {
        this.additionalLinks = additionalLinks;
    }

    public Cite getCite() {
        return cite;
    }

    public void setCite(Cite cite) {
        this.cite = cite;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }
}
