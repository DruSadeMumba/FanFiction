
package com.drusade.fanfictionbookclub.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cite {

    @SerializedName("domain")
    @Expose
    private String domain;
    @SerializedName("span")
    @Expose
    private String span;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Cite() {
    }

    /**
     * 
     * @param domain
     * @param span
     */
    public Cite(String domain, String span) {
        super();
        this.domain = domain;
        this.span = span;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getSpan() {
        return span;
    }

    public void setSpan(String span) {
        this.span = span;
    }

}
