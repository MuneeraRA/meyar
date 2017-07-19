package app.firstcode.meyar.presentation.fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.nguyenhoanglam.imagepicker.activity.ImagePicker;
import com.nguyenhoanglam.imagepicker.activity.ImagePickerActivity;
import com.nguyenhoanglam.imagepicker.model.Image;

import java.io.File;
import java.util.ArrayList;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.register.data;
import app.firstcode.meyar.data.service.model.register.RegisterResponse;
import app.firstcode.meyar.data.service.model.register.RegisterRequest;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.app.Activity.RESULT_OK;

public class NewAccountFragment extends BaseFragment implements View.OnClickListener {

    private CircleImageView profile_image;
    private ImageView profileImagePlaceholder;
    private EditText contractorNameEdit;
    private EditText contractorEmailEdit;
    private EditText contractorNumberEdit;
    private EditText contractorPasswordEdit;
    private EditText contractorReEnterPasswordEdit;
    private EditText contractorBusinessName;
    private EditText ownerNameEdit;
    private EditText ownerNumberEdit;
    private EditText commissionerNameEdit;
    private EditText commissionerPhoneNumberEdit;
    private EditText commissionerNationalityEdit;
    private EditText commissionerEmployeesNumberEdit;
    private EditText companyAgeText;
    private EditText commercialRecordNumberEdit;
    private TextView textView;
    private CheckBox riyadhCheckBox;
    private CheckBox jeddahCheckBox;
    private CheckBox dammamCheckBox;
    private Button uploadLatter;
    private ImageView firstImage;
    private ImageView secondImage;
    private ImageView thirdImage;
    private Button save;

    File ProfilepPicture ,letterpic,imgeOne,imgeTwo,imgeThree ;

    private boolean validationFlag ;
    private String  name, mail, telephon,  password,  passwordConf, bussinseName,  ownerName,  ownerPhone,  negotiatorName,  negotiatorPhone,nationality,  employesNum, age,commercailRecordNum, city=",";

    private Retrofit retrofit;
    ApiInterface client;

    public final int REQUEST_PROFILE_PICTURE = 2000;
    public final int REQUEST_PREVIOUS_WORK = 3000;
    public final int REQUEST_UPOAD_LATTER = 4000;

    private ArrayList<Image> images = new ArrayList<>();
    private ArrayList<Image> imageProfile = new ArrayList<>();
    private ArrayList<Image> imageLetter = new ArrayList<>();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-06-19 21:16:00 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View v) {

        profile_image = (CircleImageView) v.findViewById(R.id.profile_image);
        profileImagePlaceholder = (ImageView) v.findViewById(R.id.profile_image_placeholder);
        contractorNameEdit = (EditText) v.findViewById(R.id.contractor_name_edit);
        contractorEmailEdit = (EditText) v.findViewById(R.id.contractor_email_edit);
        contractorNumberEdit = (EditText) v.findViewById(R.id.contractor_number_edit);
        contractorPasswordEdit = (EditText) v.findViewById(R.id.contractor_password_edit);
        contractorReEnterPasswordEdit = (EditText) v.findViewById(R.id.contractor_re_enter_password_edit);
        contractorBusinessName = (EditText) v.findViewById(R.id.contractor_business_name);
        ownerNameEdit = (EditText) v.findViewById(R.id.owner_name_edit);
        ownerNumberEdit = (EditText) v.findViewById(R.id.owner_number_edit);
        commissionerNameEdit = (EditText) v.findViewById(R.id.commissioner_name_edit);
        commissionerPhoneNumberEdit = (EditText) v.findViewById(R.id.commissioner_phone_number_edit);
        commissionerNationalityEdit = (EditText) v.findViewById(R.id.commissioner_nationality_edit);
        commissionerEmployeesNumberEdit = (EditText) v.findViewById(R.id.commissioner_employees_number_edit);
        companyAgeText = (EditText) v.findViewById(R.id.company_age_text);
        commercialRecordNumberEdit = (EditText) v.findViewById(R.id.commercial_record_number_edit);
        textView = (TextView) v.findViewById(R.id.textView);
        riyadhCheckBox = (CheckBox) v.findViewById(R.id.riyadh_check_box);
        jeddahCheckBox = (CheckBox) v.findViewById(R.id.jeddah_check_box);
        dammamCheckBox = (CheckBox) v.findViewById(R.id.dammam_check_box);
        uploadLatter = (Button) v.findViewById(R.id.upload_latter);
        firstImage = (ImageView) v.findViewById(R.id.first_image);
        secondImage = (ImageView) v.findViewById(R.id.second_image);
        thirdImage = (ImageView) v.findViewById(R.id.third_image);
        save = (Button) v.findViewById(R.id.save);

        ProfilepPicture =null;
        letterpic=null;
        imgeOne=null;
        imgeTwo=null;
        imgeThree = null;

        riyadhCheckBox.setOnClickListener(this);
        jeddahCheckBox.setOnClickListener(this);
        dammamCheckBox.setOnClickListener(this);
        uploadLatter.setOnClickListener(this);
        save.setOnClickListener(this);
        validationFlag=false;
    }





    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_new_account, null, false);
        findViews(v);
        toolbarSetTitle(R.string.nav_register_as_contractor);
        setCheckedItem(R.id.nav_register_as_contractor);

        profile_image.setOnClickListener(this);
        firstImage.setOnClickListener(this);
        secondImage.setOnClickListener(this);
        thirdImage.setOnClickListener(this);



        Retrofit retrofit =ApiClient.getClient();
        client =  retrofit.create(ApiInterface.class);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.profile_image:
                profileImageStartChoose();
                break;
            case R.id.first_image:
                previousWorkImageStartChoose();
                break;
            case R.id.second_image:
                previousWorkImageStartChoose();
                break;
            case R.id.third_image:
                previousWorkImageStartChoose();
                break;
            case R.id.upload_latter:
                letterStartChoose();
                break;
            case R.id.save:
                fetchFormfileds(v);
                validationFlag= BaseFragment.registrationFormValidation(getActivity(),name,mail,telephon,password,passwordConf,bussinseName,ownerName,ownerPhone,negotiatorName,negotiatorPhone,nationality,employesNum,age,commercailRecordNum,city);
                if (validationFlag)
                {regesterConstractore();
                }
                break;
            case R.id.riyadh_check_box:
                city+="1,";
                break;
            case R.id.dammam_check_box:
                city+="2,";
                break;
            case R.id.jeddah_check_box:
                city+="3,";
                break;

        }
    }

    private void fetchFormfileds (View view){
        name = contractorNameEdit.getText().toString().trim();
        mail=contractorEmailEdit.getText().toString().trim();
        telephon=contractorNumberEdit.getText().toString().trim();
        password=contractorPasswordEdit.getText().toString().trim();
        passwordConf=contractorReEnterPasswordEdit.getText().toString().trim();
        bussinseName=contractorBusinessName.getText().toString().trim();
        ownerName=ownerNameEdit.getText().toString().trim();
        ownerPhone=ownerNumberEdit.getText().toString().trim();
        negotiatorName=commissionerNameEdit.getText().toString().trim();
        negotiatorPhone=commissionerPhoneNumberEdit.getText().toString().trim();
        nationality=commissionerNationalityEdit.getText().toString().trim();
        employesNum=commissionerEmployeesNumberEdit.getText().toString().trim();
        age=companyAgeText.getText().toString().trim();
        commercailRecordNum=commercialRecordNumberEdit.getText().toString().trim();

    }

    private void  regesterConstractore (){
        RegisterRequest data = new RegisterRequest();
        data.setName(name);
        data.setPassword(password);
        data.setEmail(mail);
        data.setMobile(telephon);
        data.setCity(city);
        data.setCommercialName(bussinseName);
        data.setOwnerName(ownerName);
        data.setOwnerMobile(ownerPhone);
        data.setAuthorizedPerson(negotiatorName);
        data.setOwnerName(negotiatorPhone);
        data.setNationality(nationality);
        data.setNoOfEmployees(employesNum);
        data.setBusinessStart(age);
        data.setCommercialRegistrationNo(commercailRecordNum);
        RequestBody requestProfileFile , requestLetterFile ,imageFile ,image2File,image3File;
        MultipartBody.Part profilePicBody ,letterPicBody ,imagePicBody,image2PicBody,image3PicBody;

        if (ProfilepPicture!=null){
            // creates RequestBody instance from file
            requestProfileFile = RequestBody.create(MediaType.parse("multipart/form-data"), ProfilepPicture);
            // MultipartBody.Part is used to send also the actual filename
            profilePicBody = MultipartBody.Part.createFormData("ProfilePicture", ProfilepPicture.getName(), requestProfileFile);}

        else profilePicBody=null;

        if (letterpic!=null) {
            requestLetterFile = RequestBody.create(MediaType.parse("multipart/form-data"), letterpic);
            // MultipartBody.Part is used to send also the actual filename
            letterPicBody = MultipartBody.Part.createFormData("AuthorizationLetterPhoto", letterpic.getName(), requestLetterFile);
        } else letterPicBody =null;

        if (imgeOne!=null){
            imageFile = RequestBody.create(MediaType.parse("multipart/form-data"),imgeOne );
            // MultipartBody.Part is used to send also the actual filename
            imagePicBody = MultipartBody.Part.createFormData("Image", imgeOne.getName(), imageFile);}
        else imagePicBody=null;

        if (imgeTwo!=null){
            image2File = RequestBody.create(MediaType.parse("multipart/form-data"), imgeTwo);
            // MultipartBody.Part is used to send also the actual filename
            image2PicBody = MultipartBody.Part.createFormData("Image2", imgeTwo.getName(), image2File);}
        else image2PicBody=null;

        if (imgeThree!=null){
            image3File = RequestBody.create(MediaType.parse("multipart/form-data"), imgeThree);
            // MultipartBody.Part is used to send also the actual filename
            image3PicBody = MultipartBody.Part.createFormData("Image3", imgeThree.getName(), image3File);}
        else  image3PicBody=null;



        Call<RegisterResponse> call = client.register(profilePicBody,letterPicBody,imagePicBody,image2PicBody,image3PicBody,data);

        call.enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.code()==200 && response.body().getStatus().equals("success") ){
                    BaseFragment.showDialog(getActivity(),R.drawable.ic_check, R.string.successfully_register_message, R.string.txt_back_home, new OnDialogClickListener() {
                        @Override
                        public void onClick() {

                            // Create new fragment and transaction
                            Fragment newFragment = new HomePageFragment();
                            FragmentTransaction transaction = getFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack if needed
                            transaction.replace(R.id.container, newFragment);
                            transaction.addToBackStack(null);

// Commit the transaction
                            transaction.commit();

                        }});


                }



                else {

                    Toast.makeText(getActivity(), response.body().getMessage() , Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
                BaseFragment.showDialog(getActivity(),R.drawable.ic_check, R.string.msg_checking_network, R.string.done, new OnDialogClickListener() {
                    @Override
                    public void onClick() {

                    }});
            }
        });




    }


    // Recomended builder
    public void profileImageStartChoose() {
        ImagePicker.create(this)
                .folderMode(true) // set folder mode (false by default)
                .single() // single mode
                .limit(1) // max images can be selected (999 by default)
//                .showCamera(true) // show camera or not (true by default)
                .start(REQUEST_PROFILE_PICTURE); // start image picker activity with request code
    }

    public void letterStartChoose() {
        ImagePicker.create(this)
                .folderMode(true) // set folder mode (false by default)
                .single() // single mode
                .limit(1) // max images can be selected (999 by default)
//                .showCamera(true) // show camera or not (true by default)
                .start(REQUEST_UPOAD_LATTER); // start image picker activity with request code
    }

    public void previousWorkImageStartChoose() {
        ImagePicker.create(this)
                .folderMode(true) // set folder mode (false by default)
                .single() // single mode
                .multi()
                .limit(3) // max images can be selected (999 by default)
//                .showCamera(true) // show camera or not (true by default)
                .start(REQUEST_PREVIOUS_WORK); // start image picker activity with request code
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
//            requestCode == REQUEST_PROFILE_PICTURE
            Log.e("Deal: ", "Deal");
            switch (requestCode) {
                case REQUEST_PROFILE_PICTURE:
                    imageProfile = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);

                    for (int i = 0, l = images.size(); i < l; i++) {
                        Log.e("Image Path: ", "" + imageProfile.get(i).getPath());
                        Glide.with(getActivity()).load(imageProfile.get(i).getPath()).into(profile_image);
                        profileImagePlaceholder.setVisibility(View.GONE);
                    }

                    String imagepath =imageProfile.get(0).getPath();
                    Toast.makeText(getActivity(), imagepath, Toast.LENGTH_LONG).show();

                    ProfilepPicture= new File(imagepath);
                    // this should be compressed


                    break;
                case REQUEST_UPOAD_LATTER:
                    imageLetter = data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);

                    for (int i = 0, l = imageLetter.size(); i < l; i++) {
                        Log.e("Image Path: ", "" + imageLetter.get(i).getPath());
                        uploadLatter.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                        profileImagePlaceholder.setVisibility(View.GONE);
                    }

                    String imageletterpath =imageLetter.get(0).getPath();
                    letterpic= new File(imageletterpath);

                    break;
                case REQUEST_PREVIOUS_WORK:

                    images= data.getParcelableArrayListExtra(ImagePickerActivity.INTENT_EXTRA_SELECTED_IMAGES);

                    Glide.with(getActivity()).load(R.drawable.ic_upload).override(30, 30).into(firstImage);
                    Glide.with(getActivity()).load(R.drawable.ic_upload).override(30, 30).into(secondImage);
                    Glide.with(getActivity()).load(R.drawable.ic_upload).override(30, 30).into(thirdImage);

//                    firstImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.gray_tint_color));
//                    secondImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.gray_tint_color));
//                    thirdImage.setColorFilter(ContextCompat.getColor(getActivity(), R.color.gray_tint_color));
                    for (int i = 0, l = images.size(); i < l; i++) {
                        Log.e("Image Path: ", "" + images.get(i).getPath());
                        if (i == 0) {
                            Glide.with(getActivity()).load(images.get(i).getPath()).into(firstImage);
                        }
                        if (i == 1) {
                            Glide.with(getActivity()).load(images.get(i).getPath()).into(secondImage);
                        }
                        if (i == 2) {
                            Glide.with(getActivity()).load(images.get(i).getPath()).into(thirdImage);
                        }
                    }

                    String image1path =images.get(0).getPath();
                    imgeOne= new File(image1path);
                    String image2path =images.get(1).getPath();
                    imgeTwo= new File(image1path);
                    String image3path =images.get(2).getPath();
                    imgeThree= new File(image1path);

                    Toast.makeText(getActivity(), image1path + "****" + image2path + "****"+ image3path, Toast.LENGTH_LONG).show();


                    break;
            }


        }
    }
}
