package app.firstcode.meyar.data.service;

import java.util.List;

import app.firstcode.meyar.data.service.model.Category;
import app.firstcode.meyar.data.service.model.Contractor;
import app.firstcode.meyar.data.service.model.ContractorSearchModel;
import app.firstcode.meyar.data.service.model.RateResponse;
import app.firstcode.meyar.data.service.model.contact.ContactResponce;
import app.firstcode.meyar.data.service.model.contact.Meaage;
import app.firstcode.meyar.data.service.model.register.RegisterRequest;
import app.firstcode.meyar.data.service.model.register.RegisterResponse;
import app.firstcode.meyar.data.service.model.restPassword.CheckCodeRequest;
import app.firstcode.meyar.data.service.model.restPassword.CheckCodeResponse;
import app.firstcode.meyar.data.service.model.restPassword.RestPasswordRequest;
import app.firstcode.meyar.data.service.model.restPassword.RestPasswordResponse;
import app.firstcode.meyar.data.service.model.restPassword.SetPasswordRequest;
import app.firstcode.meyar.data.service.model.restPassword.SetPasswordResponse;
import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by Diaa on 6/3/2017.
 */

public interface ApiInterface {

    @GET(ApiEndpoints.CATEGORIES)
    Call<List<Category>> getCategories();

    @POST(ApiEndpoints.LOGIN)
    @FormUrlEncoded
    Call<Contractor> login(@Field("Email") String email, @Field("Password") String password);

    @GET(ApiEndpoints.SEARCH)
    Call<List<ContractorSearchModel>> searchContractor(@Query("category") String categoryId, @Query("city") String cityId, @Query("term") String name);

    @POST(ApiEndpoints.RATE)
    @FormUrlEncoded
    Call<RateResponse> rate(@Field("ContractorID") String contractorID, @Field("MobileID") String mobileID, @Field("Rate") String rate);

    @POST(ApiEndpoints.CONTACT)
    Call<ContactResponce>contactUs(@Body Meaage meaage);

    @Multipart
    @POST(ApiEndpoints.REGISTER)
    Call <RegisterResponse> register(@Part MultipartBody.Part profilePic, @Part MultipartBody.Part letterPic, @Part MultipartBody.Part pic1, @Part MultipartBody.Part pic2, @Part MultipartBody.Part pic3, @Part("data") RegisterRequest data);


    @POST(ApiEndpoints.REQUESTRESTPASSWORD)
    Call<RestPasswordResponse> requestPassword(@Body RestPasswordRequest data);

    @POST(ApiEndpoints.REQUESTCODE)
    Call<CheckCodeResponse> checkCode(@Body CheckCodeRequest data);

    @POST (ApiEndpoints.SETPASSWORD)
    Call<SetPasswordResponse> setPassword (@Body SetPasswordRequest data);



}
