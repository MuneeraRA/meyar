package app.firstcode.meyar.presentation.fragments;


import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.contact.ContactResponce;
import app.firstcode.meyar.data.service.model.contact.Meaage;
import app.firstcode.meyar.presentation.activities.ServiceTypeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static app.firstcode.meyar.data.service.ApiClient.getClient;

public class SupportFragment extends BaseFragment implements View.OnClickListener {


    private EditText userEmailEditText, userMessageEditText;
    private Button send;

    private String email, message;
    private boolean validationFlag;

    Retrofit retrofit;
    ApiInterface service;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_support, null, false);
        toolbarSetTitle(R.string.nav_support);
        setCheckedItem(R.id.nav_support);

        userEmailEditText = (EditText) v.findViewById(R.id.user_email_edit);
        userMessageEditText = (EditText) v.findViewById(R.id.user_message_edit);
        send = (Button) v.findViewById(R.id.send_message_button);
        send.setOnClickListener(this);
        validationFlag=false;

        retrofit = ApiClient.getClient();
        service = retrofit.create(ApiInterface.class);

        return v;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_message_button:
                email = userEmailEditText.getText().toString().trim();
                message = userMessageEditText.getText().toString().trim();
                validationFlag = supportFormValidation(getActivity(), email, message);

                if (validationFlag) {
                    Call<ContactResponce> call = service.contactUs(new Meaage(email, message));
                    call.enqueue(new Callback<ContactResponce>() {
                        @Override
                        public void onResponse(Call<ContactResponce> call, Response<ContactResponce> response) {

                            if (response.code() == 200 && response.body().getStatus().equals("success")) {
                                BaseFragment.showDialog(getActivity(), R.drawable.ic_check, R.string.contact_us_message, R.string.txt_back_home, new OnDialogClickListener() {
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

                                    }
                                });

                            } else {
                                BaseFragment.showDialog(getActivity(), R.drawable.ic_report, response.body().getMessage(), R.string.done, new OnDialogClickListener() {
                                    @Override
                                    public void onClick() {
                                        return;
                                    }
                                });


                            }
                        }

                        @Override
                        public void onFailure(Call<ContactResponce> call, Throwable t) {

                            BaseFragment.showDialog(getActivity(), R.drawable.ic_report, R.string.msg_checking_network, R.string.done, new OnDialogClickListener() {
                                @Override
                                public void onClick() {
return;
                                }
                            });

                        }
                    });

                }


                break;


        }
    }


    public static boolean supportFormValidation(Context context , String mail , String message) {

        if (mail.equals("")){
            BaseFragment.showDialog(context, R.drawable.ic_report, R.string.msg_empty_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (!BaseFragment.validateMail(mail)){
            BaseFragment.showDialog(context, R.drawable.ic_report, R.string.msg_wrong_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (message.equals("")){
            BaseFragment.showDialog(context, R.drawable.ic_report, R.string.msg_empty_message, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        return  true;}

}
