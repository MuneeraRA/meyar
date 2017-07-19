package app.firstcode.meyar.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.orhanobut.hawk.Hawk;

import app.firstcode.meyar.OnDialogClickListener;
import app.firstcode.meyar.R;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by Diaa on 6/4/2017.
 */

public class BaseActivity extends AppCompatActivity {

    Toolbar toolbar;
    public MaterialSearchView searchView;
    public static MaterialDialog materialDialog;

    public void replaceFragments(Fragment fragment, String tag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment, tag).addToBackStack(tag).commit();
    }

    public String getAdvertisingId() {
        if (Hawk.contains("clientId")) {
            return Hawk.get("clientId");
        }
        return "";
    }

    public void initToolbar(int title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public void initToolbarWithSearchView(int title) {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchView.setLayoutDirection(View.LAYOUT_DIRECTION_LOCALE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    public static void showMessage(final Context context, final int imageResource, final int message, final int actionName, final OnDialogClickListener clickListener) {
        materialDialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_custom_view, false)
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        ImageView imageView = (ImageView) materialDialog.getCustomView().findViewById(R.id.imageView);
                        imageView.setImageResource(imageResource);

                        final TextView dialogMessage = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_message);
                        TextView dialog_action = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_action);

                        dialogMessage.setText(context.getString(message));
                        dialog_action.setText(context.getString(actionName));

                        dialog_action.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clickListener.onClick();
                                materialDialog.dismiss();
                            }
                        });
                    }
                }).canceledOnTouchOutside(false)
                .show();
    }

    public static void showMessage(final Context context, final int imageResource, final String message, final int actionName, final OnDialogClickListener clickListener) {
        materialDialog = new MaterialDialog.Builder(context)
                .customView(R.layout.dialog_custom_view, false)
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        ImageView imageView = (ImageView) materialDialog.getCustomView().findViewById(R.id.imageView);
                        imageView.setImageResource(imageResource);

                        final TextView dialogMessage = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_message);
                        TextView dialog_action = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_action);

                        dialogMessage.setText(message);
                        dialog_action.setText(context.getString(actionName));

                        dialog_action.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                clickListener.onClick();
                                materialDialog.dismiss();
                            }
                        });
                    }
                }).canceledOnTouchOutside(false)
                .show();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public void adjustFontScale(Configuration configuration) {
        if (configuration.fontScale > 1.30) {
//            LogUtil.log(LogUtil.WARN, TAG, "fontScale=" + configuration.fontScale); //Custom Log class, you can use Log.w
//            LogUtil.log(LogUtil.WARN, TAG, "font too big. scale down..."); //Custom Log class, you can use Log.w
            Log.e("TAG: ", "" + "fontScale=" + configuration.fontScale);
            Log.e("TAG: ", "" + "font too big. scale down...");
            configuration.fontScale = (float) 1.30;
            DisplayMetrics metrics = getResources().getDisplayMetrics();
            WindowManager wm = (WindowManager) getSystemService(WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(metrics);
            metrics.scaledDensity = configuration.fontScale * metrics.density;
            getBaseContext().getResources().updateConfiguration(configuration, metrics);
        }
    }


    public static boolean forgetPasswordFormValidation(Context context , String mail){

        if (mail.equals("")){
            showMessage(context, R.drawable.ic_report, R.string.msg_empty_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

       /* if (!BaseFragment.validateMail(mail)) {
            showMessage(context, R.drawable.ic_report, R.string.msg_wrong_mail,R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {
                }
            });
            return false;
        }*/


        return true;
    }

    public static boolean loginFormValidation(Context context , String mail,String password){

        if (mail.equals("")){
            showMessage(context, R.drawable.ic_report, R.string.msg_empty_mail, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }

        if (password.equals("")){
            showMessage(context, R.drawable.ic_report, R.string.msg_empty_password, R.string.done, new OnDialogClickListener() {
                @Override
                public void onClick() {}});
            return false ;
        }


        return true;
    }


}
