
package app.firstcode.meyar.data.service.model.register;

import java.util.List;

public class data {


    private int ID;

    private int CategoryID;

    private String Name;

    private String Email;

    private String Password;

    private String Mobile;

    private Object Image;

    private Object Image2;

    private Object Image3;

    private Object ProfilePicture;

    private String City;

    private String CommercialName;

    private String OwnerName;

    private String OwnerMobile;

    private String AuthorizedPerson;

    private String AuthorizedPersonMobile;

    private String Nationality;

    private int NoOfEmployees;

    private String BusinessStart;

    private String CommercialRegistrationNo;

    private Object AuthorizationLetterPhoto;

    private int Status;

    private Object MobileID;

    private Object AuthKey;

    private Object Cities;

    private Object AuthKeyExpirationDate;

    private int Rate;

    private int RateCount;

    private Object Category;

    private String CreatedAt;

    private Object UpdatedAt;

    private List<Object> ContractorRates = null;

    private List<Object> PasswordResetCodes = null;

    private List<Object> RateLogs = null;

    public data() {
    }

    public data(Integer ID, Integer categoryID, String name, String email, String password, String mobile, Object image, Object image2, Object image3, Object profilePicture, String city, String commercialName, String ownerName, String ownerMobile, String authorizedPerson, String authorizedPersonMobile, String nationality, Integer noOfEmployees, String businessStart, String commercialRegistrationNo, Object authorizationLetterPhoto, Integer status, Object mobileID, Object authKey, Object cities, Object authKeyExpirationDate, Integer rate, Integer rateCount, Object category, String createdAt, Object updatedAt, List<Object> contractorRates, List<Object> passwordResetCodes, List<Object> rateLogs) {
        this.ID = ID;
        CategoryID = categoryID;
        Name = name;
        Email = email;
        Password = password;
        Mobile = mobile;
        Image = image;
        Image2 = image2;
        Image3 = image3;
        ProfilePicture = profilePicture;
        City = city;
        CommercialName = commercialName;
        OwnerName = ownerName;
        OwnerMobile = ownerMobile;
        AuthorizedPerson = authorizedPerson;
        AuthorizedPersonMobile = authorizedPersonMobile;
        Nationality = nationality;
        NoOfEmployees = noOfEmployees;
        BusinessStart = businessStart;
        CommercialRegistrationNo = commercialRegistrationNo;
        AuthorizationLetterPhoto = authorizationLetterPhoto;
        Status = status;
        MobileID = mobileID;
        AuthKey = authKey;
        Cities = cities;
        AuthKeyExpirationDate = authKeyExpirationDate;
        Rate = rate;
        RateCount = rateCount;
        Category = category;
        CreatedAt = createdAt;
        UpdatedAt = updatedAt;
        ContractorRates = contractorRates;
        PasswordResetCodes = passwordResetCodes;
        RateLogs = rateLogs;
    }

    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public Integer getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(Integer categoryID) {
        CategoryID = categoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public Object getImage() {
        return Image;
    }

    public void setImage(Object image) {
        Image = image;
    }

    public Object getImage2() {
        return Image2;
    }

    public void setImage2(Object image2) {
        Image2 = image2;
    }

    public Object getImage3() {
        return Image3;
    }

    public void setImage3(Object image3) {
        Image3 = image3;
    }

    public Object getProfilePicture() {
        return ProfilePicture;
    }

    public void setProfilePicture(Object profilePicture) {
        ProfilePicture = profilePicture;
    }

    public Object getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCommercialName() {
        return CommercialName;
    }

    public void setCommercialName(String commercialName) {
        CommercialName = commercialName;
    }

    public String getOwnerName() {
        return OwnerName;
    }

    public void setOwnerName(String ownerName) {
        OwnerName = ownerName;
    }

    public String getOwnerMobile() {
        return OwnerMobile;
    }

    public void setOwnerMobile(String ownerMobile) {
        OwnerMobile = ownerMobile;
    }

    public String getAuthorizedPerson() {
        return AuthorizedPerson;
    }

    public void setAuthorizedPerson(String authorizedPerson) {
        AuthorizedPerson = authorizedPerson;
    }

    public String getAuthorizedPersonMobile() {
        return AuthorizedPersonMobile;
    }

    public void setAuthorizedPersonMobile(String authorizedPersonMobile) {
        AuthorizedPersonMobile = authorizedPersonMobile;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public Integer getNoOfEmployees() {
        return NoOfEmployees;
    }

    public void setNoOfEmployees(Integer noOfEmployees) {
        NoOfEmployees = noOfEmployees;
    }

    public String getBusinessStart() {
        return BusinessStart;
    }

    public void setBusinessStart(String businessStart) {
        BusinessStart = businessStart;
    }

    public String getCommercialRegistrationNo() {
        return CommercialRegistrationNo;
    }

    public void setCommercialRegistrationNo(String commercialRegistrationNo) {
        CommercialRegistrationNo = commercialRegistrationNo;
    }

    public Object getAuthorizationLetterPhoto() {
        return AuthorizationLetterPhoto;
    }

    public void setAuthorizationLetterPhoto(Object authorizationLetterPhoto) {
        AuthorizationLetterPhoto = authorizationLetterPhoto;
    }

    public Integer getStatus() {
        return Status;
    }

    public void setStatus(Integer status) {
        Status = status;
    }

    public Object getMobileID() {
        return MobileID;
    }

    public void setMobileID(Object mobileID) {
        MobileID = mobileID;
    }

    public Object getAuthKey() {
        return AuthKey;
    }

    public void setAuthKey(Object authKey) {
        AuthKey = authKey;
    }

    public Object getCities() {
        return Cities;
    }

    public void setCities(Object cities) {
        Cities = cities;
    }

    public Object getAuthKeyExpirationDate() {
        return AuthKeyExpirationDate;
    }

    public void setAuthKeyExpirationDate(Object authKeyExpirationDate) {
        AuthKeyExpirationDate = authKeyExpirationDate;
    }

    public Integer getRate() {
        return Rate;
    }

    public void setRate(Integer rate) {
        Rate = rate;
    }

    public Integer getRateCount() {
        return RateCount;
    }

    public void setRateCount(Integer rateCount) {
        RateCount = rateCount;
    }

    public Object getCategory() {
        return Category;
    }

    public void setCategory(Object category) {
        Category = category;
    }

    public String getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(String createdAt) {
        CreatedAt = createdAt;
    }

    public Object getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        UpdatedAt = updatedAt;
    }

    public List<Object> getContractorRates() {
        return ContractorRates;
    }

    public void setContractorRates(List<Object> contractorRates) {
        ContractorRates = contractorRates;
    }

    public List<Object> getPasswordResetCodes() {
        return PasswordResetCodes;
    }

    public void setPasswordResetCodes(List<Object> passwordResetCodes) {
        PasswordResetCodes = passwordResetCodes;
    }

    public List<Object> getRateLogs() {
        return RateLogs;
    }

    public void setRateLogs(List<Object> rateLogs) {
        RateLogs = rateLogs;
    }
}

