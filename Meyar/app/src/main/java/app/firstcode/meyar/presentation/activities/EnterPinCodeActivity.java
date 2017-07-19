package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alimuzaffar.lib.pin.PinEntryEditText;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.restPassword.CheckCodeRequest;
import app.firstcode.meyar.data.service.model.restPassword.CheckCodeResponse;
import app.firstcode.meyar.utilities.LocaleUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EnterPinCodeActivity extends BaseActivity implements View.OnClickListener {

    PinEntryEditText txt_pin_entry;
    Button send_now;
    ApiInterface service ;

    String pinCode ;
    String mobileID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_pin_code);
        initToolbar(R.string.restore_password_title);

        txt_pin_entry = (PinEntryEditText) findViewById(R.id.txt_pin_entry);

        send_now = (Button) findViewById(R.id.send_now);
        send_now.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_now:

                pinCode=txt_pin_entry.getText().toString().trim();
                mobileID=getAdvertisingId();
                if (pinCode.length()==4&&mobileID!=null){

                    service= ApiClient.getClient().create(ApiInterface.class);
                    Call<CheckCodeResponse> call =service.checkCode(new CheckCodeRequest(pinCode,mobileID));

call.enqueue(new Callback<CheckCodeResponse>() {
    @Override
    public void onResponse(Call<CheckCodeResponse> call, Response<CheckCodeResponse> response) {
        if (response.code() == 200 && response.body().getStatus().equals("success")) {

Intent intent = new Intent(EnterPinCodeActivity.this,SubmitNewPasswordActivity.class);
            intent.putExtra("pinCode",pinCode);
            startActivity(intent);
            finish();

        }

        else {

            BaseActivity.showMessage(EnterPinCodeActivity.this, R.drawable.ic_report,R.string.msg_invalied_pin , R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {
                }
            });

        }

        }

    @Override
    public void onFailure(Call<CheckCodeResponse> call, Throwable t) {

        BaseActivity.showMessage(EnterPinCodeActivity.this, R.drawable.ic_report,R.string.msg_checking_network , R.string.done, new OnDialogClickListener() {
            @Override
            public void onClick() {
            }
        });

    }
});


                }

                break;
        }


    }

}
