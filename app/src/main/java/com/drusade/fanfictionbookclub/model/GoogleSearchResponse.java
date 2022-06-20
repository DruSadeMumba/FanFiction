
package com.drusade.fanfictionbookclub.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class GoogleSearchResponse {

    @SerializedName("results")
    @Expose
    private List<Result> results = null;
    @SerializedName("ts")
    @Expose
    private Double ts;
    /*@SerializedName("image_results")
    @Expose
    private List<Object> imageResults = null;*/
    @SerializedName("total")
    @Expose
    private Integer total;
    /*@SerializedName("answers")
    @Expose
    private List<Object> answers = null;
    @SerializedName("device_type")
    @Expose
    private Object deviceType;*/

    /**
     * No args constructor for use in serialization
     * 
     */
    public GoogleSearchResponse() {
    }

    /**
     * 

     * @param total

     * @param results
     * @param ts
     */
    public GoogleSearchResponse(List<Result> results/*, List<Object> imageResults*/, Integer total/*, List<Object> answers*/, Double ts/*, Object deviceType*/) {
        super();
        this.results = results;
        /*this.imageResults = imageResults;*/
        this.total = total;
        this.ts = ts;
        /*this.answers = answers;
        this.deviceType = deviceType;*/
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    /*public List<Object> getImageResults() {
        return imageResults;
    }

    public void setImageResults(List<Object> imageResults) {
        this.imageResults = imageResults;
    }*/

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    /*public List<Object> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Object> answers) {
        this.answers = answers;
    }*/

    public Double getTs() {
        return ts;
    }

    public void setTs(Double ts) {
        this.ts = ts;
    }

    /*public Object getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Object deviceType) {
        this.deviceType = deviceType;
    }*/

}
