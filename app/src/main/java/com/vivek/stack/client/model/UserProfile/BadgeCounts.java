
package com.vivek.stack.client.model.UserProfile;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class BadgeCounts {

    @SerializedName("bronze")
    @Expose
    private int bronze;
    @SerializedName("silver")
    @Expose
    private int silver;
    @SerializedName("gold")
    @Expose
    private int gold;

    /**
     * 
     * @return
     *     The bronze
     */
    public int getBronze() {
        return bronze;
    }

    /**
     * 
     * @param bronze
     *     The bronze
     */
    public void setBronze(int bronze) {
        this.bronze = bronze;
    }

    /**
     * 
     * @return
     *     The silver
     */
    public int getSilver() {
        return silver;
    }

    /**
     * 
     * @param silver
     *     The silver
     */
    public void setSilver(int silver) {
        this.silver = silver;
    }

    /**
     * 
     * @return
     *     The gold
     */
    public int getGold() {
        return gold;
    }

    /**
     * 
     * @param gold
     *     The gold
     */
    public void setGold(int gold) {
        this.gold = gold;
    }

}
