package app.firstcode.meyar.data.service.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diaa on 7/10/2017.
 */

public class ContractorSearchModel {

    @SerializedName("ID")
    private int ID;
    @SerializedName("Name")
    private String Name;
    @SerializedName("CategoryID")
    private int CategoryID;
    @SerializedName("Category")
    private String Category;
    @SerializedName("Rate")
    private int Rate;
    @SerializedName("RateCount")
    private int RateCount;
    @SerializedName("ProfilePicture")
    private String ProfilePicture;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Mobile")
    private String Mobile;
    @SerializedName("Cities")
    private String Cities;
    @SerializedName("Image")
    private String Image;
    @SerializedName("Image2")
    private String Image2;
    @SerializedName("Image3")
    private String Image3;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public int getRate() {
        return Rate;
    }

    public void setRate(int Rate) {
        this.Rate = Rate;
    }

    public int getRateCount() {
        return RateCount;
    }

    public void setRateCount(int RateCount) {
        this.RateCount = RateCount;
    }

    public String getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(String ProfilePicture) {
        this.ProfilePicture = ProfilePicture;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getCities() {
        return Cities;
    }

    public void setCities(String Cities) {
        this.Cities = Cities;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public String getImage2() {
        return Image2;
    }

    public void setImage2(String Image2) {
        this.Image2 = Image2;
    }

    public String getImage3() {
        return Image3;
    }

    public void setImage3(String Image3) {
        this.Image3 = Image3;
    }
}
