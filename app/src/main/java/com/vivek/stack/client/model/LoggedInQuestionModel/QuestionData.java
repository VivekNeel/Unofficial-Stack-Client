
package com.vivek.stack.client.model.LoggedInQuestionModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class QuestionData {

    @SerializedName("items")
    @Expose
    private List<Item> items = new ArrayList<Item>();
    @SerializedName("has_more")
    @Expose
    private boolean hasMore;
    @SerializedName("quota_max")
    @Expose
    private int quotaMax;
    @SerializedName("quota_remaining")
    @Expose
    private int quotaRemaining;

    /**
     * 
     * @return
     *     The items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * 
     * @param items
     *     The items
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * 
     * @return
     *     The hasMore
     */
    public boolean isHasMore() {
        return hasMore;
    }

    /**
     * 
     * @param hasMore
     *     The has_more
     */
    public void setHasMore(boolean hasMore) {
        this.hasMore = hasMore;
    }

    /**
     * 
     * @return
     *     The quotaMax
     */
    public int getQuotaMax() {
        return quotaMax;
    }

    /**
     * 
     * @param quotaMax
     *     The quota_max
     */
    public void setQuotaMax(int quotaMax) {
        this.quotaMax = quotaMax;
    }

    /**
     * 
     * @return
     *     The quotaRemaining
     */
    public int getQuotaRemaining() {
        return quotaRemaining;
    }

    /**
     * 
     * @param quotaRemaining
     *     The quota_remaining
     */
    public void setQuotaRemaining(int quotaRemaining) {
        this.quotaRemaining = quotaRemaining;
    }

}
