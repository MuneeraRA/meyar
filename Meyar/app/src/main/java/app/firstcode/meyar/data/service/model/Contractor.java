package app.firstcode.meyar.data.service.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Diaa on 7/14/2017.
 */

public class Contractor {

    @SerializedName("key")
    private String key;
    @SerializedName("user")
    private User user;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static class User {
        @SerializedName("ID")
        private int ID;
        @SerializedName("Name")
        private String Name;
        @SerializedName("Category")
        private String Category;
        @SerializedName("Email")
        private String Email;
        @SerializedName("Mobile")
        private String Mobile;
        @SerializedName("CommercialName")
        private String CommercialName;
        @SerializedName("OwnerName")
        private String OwnerName;
        @SerializedName("OwnerMobile")
        private String OwnerMobile;
        @SerializedName("AuthorizedPerson")
        private String AuthorizedPerson;
        @SerializedName("AuthorizedPersonMobile")
        private String AuthorizedPersonMobile;
        @SerializedName("Nationality")
        private String Nationality;
        @SerializedName("NoOfEmployees")
        private int NoOfEmployees;
        @SerializedName("BusinessStart")
        private String BusinessStart;
        @SerializedName("CommercialRegistrationNo")
        private String CommercialRegistrationNo;
        @SerializedName("AuthorizationLetterPhoto")
        private String AuthorizationLetterPhoto;
        @SerializedName("Rate")
        private int Rate;
        @SerializedName("RateCount")
        private int RateCount;
        @SerializedName("ProfilePicture")
        private String ProfilePicture;
        @SerializedName("Image")
        private String Image;
        @SerializedName("Image2")
        private String Image2;
        @SerializedName("Image3")
        private String Image3;
        @SerializedName("Cities")
        private String Cities;
        @SerializedName("AuthKey")
        private String AuthKey;


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

        public String getCategory() {
            return Category;
        }

        public void setCategory(String Category) {
            this.Category = Category;
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

        public String getCommercialName() {
            return CommercialName;
        }

        public void setCommercialName(String CommercialName) {
            this.CommercialName = CommercialName;
        }

        public String getOwnerName() {
            return OwnerName;
        }

        public void setOwnerName(String OwnerName) {
            this.OwnerName = OwnerName;
        }

        public String getOwnerMobile() {
            return OwnerMobile;
        }

        public void setOwnerMobile(String OwnerMobile) {
            this.OwnerMobile = OwnerMobile;
        }

        public String getAuthorizedPerson() {
            return AuthorizedPerson;
        }

        public void setAuthorizedPerson(String AuthorizedPerson) {
            this.AuthorizedPerson = AuthorizedPerson;
        }

        public String getAuthorizedPersonMobile() {
            return AuthorizedPersonMobile;
        }

        public void setAuthorizedPersonMobile(String AuthorizedPersonMobile) {
            this.AuthorizedPersonMobile = AuthorizedPersonMobile;
        }

        public String getNationality() {
            return Nationality;
        }

        public void setNationality(String Nationality) {
            this.Nationality = Nationality;
        }

        public int getNoOfEmployees() {
            return NoOfEmployees;
        }

        public void setNoOfEmployees(int NoOfEmployees) {
            this.NoOfEmployees = NoOfEmployees;
        }

        public String getBusinessStart() {
            return BusinessStart;
        }

        public void setBusinessStart(String BusinessStart) {
            this.BusinessStart = BusinessStart;
        }

        public String getCommercialRegistrationNo() {
            return CommercialRegistrationNo;
        }

        public void setCommercialRegistrationNo(String CommercialRegistrationNo) {
            this.CommercialRegistrationNo = CommercialRegistrationNo;
        }

        public String getAuthorizationLetterPhoto() {
            return AuthorizationLetterPhoto;
        }

        public void setAuthorizationLetterPhoto(String AuthorizationLetterPhoto) {
            this.AuthorizationLetterPhoto = AuthorizationLetterPhoto;
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

        public String getCities() {
            return Cities;
        }

        public void setCities(String Cities) {
            this.Cities = Cities;
        }

        public String getAuthKey() {
            return AuthKey;
        }

        public void setAuthKey(String AuthKey) {
            this.AuthKey = AuthKey;
        }
    }
}
