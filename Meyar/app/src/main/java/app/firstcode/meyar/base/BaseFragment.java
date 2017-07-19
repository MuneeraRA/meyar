package app.firstcode.meyar.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;

import static android.content.Context.WINDOW_SERVICE;

/**
 * Created by Diaa on 6/4/2017.
 */

public class BaseFragment extends Fragment {

    MaterialDialog materialDialog;
    NavigationView navigationView;
    private static String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern p;
    private static Matcher m;

    public void toolbarSetTitle(int title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    public void setCheckedItem(int id) {
        navigationView = (NavigationView) (getActivity()).findViewById(R.id.nav_view);
        navigationView.setCheckedItem(id);
    }

    public static void showDialog(final Context context, final int imageResource, final int message, final int actionName, final OnDialogClickListener clickListener) {
        BaseActivity.showMessage(context, imageResource, message, actionName, clickListener);
//        materialDialog = new MaterialDialog.Builder(getActivity())
//                .customView(R.layout.dialog_custom_view, false)
//                .showListener(new DialogInterface.OnShowListener() {
//                    @Override
//                    public void onShow(DialogInterface dialog) {
//                        final TextView textView = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_message);
//                        TextView dialog_action = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_action);
//                        textView.setText("asdasdasd");
//
//                        dialog_action.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                Toast.makeText(getActivity(), "" + textView.getText().toString(), Toast.LENGTH_SHORT).show();
//                                materialDialog.dismiss();
//                            }
//                        });
//                    }
//                })
//                .show();
    }


    public static void showDialog(final Context context,final int imageResource, final String message, final int actionName, final OnDialogClickListener clickListener) {
        BaseActivity.showMessage(context, imageResource, message, actionName, clickListener);}


    // This method for validate the registration/edit profile form fields before submission

    public static boolean registrationFormValidation(Context context , String name, String mail, String telephon, String password, String passwordConf, String
            bussinseName, String ownerName, String ownerPhone, String negotiatorName, String negotiatorPhone,String nationality, String employesNum, String age, String commercailRecordNum, String city) {

        if (name.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_name, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (mail.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }


        if (!validateMail(mail)){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }


        if (telephon.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_telephone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (telephon.length()<10 || telephon.length()>14  ){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_telephone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (password.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_password, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (passwordConf.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_passwordConf, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (!(passwordConf.equals(password))){
            showDialog(context, R.drawable.ic_report, R.string.msg_password_does_not_match, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (bussinseName.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_bussinseName, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (ownerName.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_ownerName, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (ownerPhone.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_ownerPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (ownerPhone.length()<10 || ownerPhone.length()>14 ){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_ownerPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (negotiatorName.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_negotiatorName, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }
        if (negotiatorPhone.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_negotiatorPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (negotiatorPhone.length()<10 || negotiatorPhone.length()>14){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_negotiatorPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }
        if (nationality.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_nationality, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (employesNum.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_employesNum, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (age.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_age, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (commercailRecordNum.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_commercailRecordNum, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (city.length()==1){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_city, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        return true;}



    // This method for validate the user profile edited form fields before  submission
    // we use polymorphism here as there is no need for password confirmation field

    public static boolean registrationFormValidation(Context context  , String name, String mail, String telephon,String
            bussinseName, String ownerName, String ownerPhone, String negotiatorName, String negotiatorPhone,
                                                     String nationality, String employesNum, String age, String commercailRecordNum, String city) {
        if (name.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_name, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (mail.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}


        if (!validateMail(mail)){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}


        if (telephon.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_telephone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (telephon.length()<10 || telephon.length()>14 ){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_telephone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}


        if (bussinseName.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_bussinseName, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (ownerName.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_ownerName, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (ownerPhone.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_ownerPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (ownerPhone.length()<10 || ownerPhone.length()>14){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_ownerPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (negotiatorName.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_negotiatorName, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (negotiatorPhone.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_negotiatorPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (negotiatorPhone.length()<10 || negotiatorPhone.length()>14 ){
            showDialog(context, R.drawable.ic_report, R.string.msg_wrong_negotiatorPhone, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (nationality.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_nationality, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (employesNum.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_employesNum, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (age.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_age, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (commercailRecordNum.equals("")){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_commercailRecordNum, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        if (city.length()==1){
            showDialog(context, R.drawable.ic_report, R.string.msg_empty_city, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;}

        return true;}


    public static boolean validateMail (String Email ){
        p = Pattern.compile(EMAIL_STRING);
        m = p.matcher(Email);
        return m.matches();}


    public void adjustFontScale(Configuration configuration) {
        if (configuration.fontScale > 1.30) {
//            LogUtil.log(LogUtil.WARN, TAG, "fontScale=" + configuration.fontScale); //Custom Log class, you can use Log.w
//            LogUtil.log(LogUtil.WARN, TAG, "font too big. scale down..."); //Custom Log class, you can use Log.w
            Log.e("TAG: ", "" + "fontScale=" + configuration.fontScale);
            Log.e("TAG: ", "" + "font too big. scale down...");
            configuration.fontScale = (float) 1.30;
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) getActivity().getSystemService(WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getActivity().getResources().updateConfiguration(configuration, metrics);
        }
    }

    public void dissmissDialog() {
        materialDialog.dismiss();
    }
}
