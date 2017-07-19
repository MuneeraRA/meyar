package app.firstcode.meyar.base;

import android.app.Application;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.WindowManager;

import com.orhanobut.hawk.Hawk;

import app.firstcode.meyar.R;
import app.firstcode.meyar.utilities.LocaleUtils;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

/**
 * Created by Diaa on 6/4/2017.
 */

public class ApplicationController extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        LocaleUtils.setLocale(getApplicationContext(), "ar");

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DroidKufi-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
        Hawk.init(getApplicationContext()).build();

//        adjustFontScale(getResources().getConfiguration());
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
}
