
package com.vivek.stack.client.model.UserProfile;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class ProfileItem {

    @SerializedName("badge_counts")
    @Expose
    private BadgeCounts badgeCounts;
    @SerializedName("account_id")
    @Expose
    private int accountId;
    @SerializedName("is_employee")
    @Expose
    private boolean isEmployee;
    @SerializedName("last_modified_date")
    @Expose
    private int lastModifiedDate;
    @SerializedName("last_access_date")
    @Expose
    private int lastAccessDate;
    @SerializedName("reputation_change_year")
    @Expose
    private int reputationChangeYear;
    @SerializedName("reputation_change_quarter")
    @Expose
    private int reputationChangeQuarter;
    @SerializedName("reputation_change_month")
    @Expose
    private int reputationChangeMonth;
    @SerializedName("reputation_change_week")
    @Expose
    private int reputationChangeWeek;
    @SerializedName("reputation_change_day")
    @Expose
    private int reputationChangeDay;
    @SerializedName("reputation")
    @Expose
    private int reputation;
    @SerializedName("creation_date")
    @Expose
    private int creationDate;
    @SerializedName("user_type")
    @Expose
    private String userType;
    @SerializedName("user_id")
    @Expose
    private int userId;
    @SerializedName("accept_rate")
    @Expose
    private int acceptRate;
    @SerializedName("link")
    @Expose
    private String link;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    @SerializedName("display_name")
    @Expose
    private String displayName;

    /**
     * 
     * @return
     *     The badgeCounts
     */
    public BadgeCounts getBadgeCounts() {
        return badgeCounts;
    }

    /**
     * 
     * @param badgeCounts
     *     The badge_counts
     */
    public void setBadgeCounts(BadgeCounts badgeCounts) {
        this.badgeCounts = badgeCounts;
    }

    /**
     * 
     * @return
     *     The accountId
     */
    public int getAccountId() {
        return accountId;
    }

    /**
     * 
     * @param accountId
     *     The account_id
     */
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    /**
     * 
     * @return
     *     The isEmployee
     */
    public boolean isIsEmployee() {
        return isEmployee;
    }

    /**
     * 
     * @param isEmployee
     *     The is_employee
     */
    public void setIsEmployee(boolean isEmployee) {
        this.isEmployee = isEmployee;
    }

    /**
     * 
     * @return
     *     The lastModifiedDate
     */
    public int getLastModifiedDate() {
        return lastModifiedDate;
    }

    /**
     * 
     * @param lastModifiedDate
     *     The last_modified_date
     */
    public void setLastModifiedDate(int lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    /**
     * 
     * @return
     *     The lastAccessDate
     */
    public int getLastAccessDate() {
        return lastAccessDate;
    }

    /**
     * 
     * @param lastAccessDate
     *     The last_access_date
     */
    public void setLastAccessDate(int lastAccessDate) {
        this.lastAccessDate = lastAccessDate;
    }

    /**
     * 
     * @return
     *     The reputationChangeYear
     */
    public int getReputationChangeYear() {
        return reputationChangeYear;
    }

    /**
     * 
     * @param reputationChangeYear
     *     The reputation_change_year
     */
    public void setReputationChangeYear(int reputationChangeYear) {
        this.reputationChangeYear = reputationChangeYear;
    }

    /**
     * 
     * @return
     *     The reputationChangeQuarter
     */
    public int getReputationChangeQuarter() {
        return reputationChangeQuarter;
    }

    /**
     * 
     * @param reputationChangeQuarter
     *     The reputation_change_quarter
     */
    public void setReputationChangeQuarter(int reputationChangeQuarter) {
        this.reputationChangeQuarter = reputationChangeQuarter;
    }

    /**
     * 
     * @return
     *     The reputationChangeMonth
     */
    public int getReputationChangeMonth() {
        return reputationChangeMonth;
    }

    /**
     * 
     * @param reputationChangeMonth
     *     The reputation_change_month
     */
    public void setReputationChangeMonth(int reputationChangeMonth) {
        this.reputationChangeMonth = reputationChangeMonth;
    }

    /**
     * 
     * @return
     *     The reputationChangeWeek
     */
    public int getReputationChangeWeek() {
        return reputationChangeWeek;
    }

    /**
     * 
     * @param reputationChangeWeek
     *     The reputation_change_week
     */
    public void setReputationChangeWeek(int reputationChangeWeek) {
        this.reputationChangeWeek = reputationChangeWeek;
    }

    /**
     * 
     * @return
     *     The reputationChangeDay
     */
    public int getReputationChangeDay() {
        return reputationChangeDay;
    }

    /**
     * 
     * @param reputationChangeDay
     *     The reputation_change_day
     */
    public void setReputationChangeDay(int reputationChangeDay) {
        this.reputationChangeDay = reputationChangeDay;
    }

    /**
     * 
     * @return
     *     The reputation
     */
    public int getReputation() {
        return reputation;
    }

    /**
     * 
     * @param reputation
     *     The reputation
     */
    public void setReputation(int reputation) {
        this.reputation = reputation;
    }

    /**
     * 
     * @return
     *     The creationDate
     */
    public int getCreationDate() {
        return creationDate;
    }

    /**
     * 
     * @param creationDate
     *     The creation_date
     */
    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * 
     * @return
     *     The userType
     */
    public String getUserType() {
        return userType;
    }

    /**
     * 
     * @param userType
     *     The user_type
     */
    public void setUserType(String userType) {
        this.userType = userType;
    }

    /**
     * 
     * @return
     *     The userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * 
     * @param userId
     *     The user_id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * 
     * @return
     *     The acceptRate
     */
    public int getAcceptRate() {
        return acceptRate;
    }

    /**
     * 
     * @param acceptRate
     *     The accept_rate
     */
    public void setAcceptRate(int acceptRate) {
        this.acceptRate = acceptRate;
    }

    /**
     * 
     * @return
     *     The link
     */
    public String getLink() {
        return link;
    }

    /**
     * 
     * @param link
     *     The link
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * 
     * @return
     *     The profileImage
     */
    public String getProfileImage() {
        return profileImage;
    }

    /**
     * 
     * @param profileImage
     *     The profile_image
     */
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    /**
     * 
     * @return
     *     The displayName
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * 
     * @param displayName
     *     The display_name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

}
