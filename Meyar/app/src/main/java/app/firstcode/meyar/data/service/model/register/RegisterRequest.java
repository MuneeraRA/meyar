package app.firstcode.meyar.data.service.model.register;

import com.google.gson.annotations.SerializedName;

/**
 * Created by macbookpro on 7/11/2017 AD.
 */

public class RegisterRequest {

    @SerializedName("Name")
    private String Name;
    @SerializedName("Email")
    private String Email;
    @SerializedName("Password")
    private String Password;
    @SerializedName("Confirm")
    private String Confirm;
    @SerializedName("Mobile")
    private String Mobile;
    @SerializedName("City")
    private String City;
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
    private String NoOfEmployees;
    @SerializedName("BusinessStart")
    private String BusinessStart;
    @SerializedName("CommercialRegistrationNo")
    private String CommercialRegistrationNo;



    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }

    public String getConfirm() {
        return Confirm;
    }

    public void setConfirm(String Confirm) {
        this.Confirm = Confirm;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String Mobile) {
        this.Mobile = Mobile;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String City) {
        this.City = City;
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

    public String getNoOfEmployees() {
        return NoOfEmployees;
    }

    public void setNoOfEmployees(String NoOfEmployees) {
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
}