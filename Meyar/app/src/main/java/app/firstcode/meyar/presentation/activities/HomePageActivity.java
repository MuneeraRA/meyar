package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.transition.Visibility;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.orhanobut.hawk.Hawk;

import java.io.IOException;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.presentation.fragments.FavoriteContractorsFragment;
import app.firstcode.meyar.presentation.fragments.HomePageFragment;
import app.firstcode.meyar.presentation.fragments.NewAccountFragment;
import app.firstcode.meyar.presentation.fragments.ProfileFragment;
import app.firstcode.meyar.presentation.fragments.SupportFragment;

public class HomePageActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;

    MenuItem home;
    MenuItem profile;
    MenuItem fav;
    MenuItem support;
    MenuItem regisiter;
    MenuItem login;
    MenuItem logout;
    boolean isLogin = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (!Hawk.contains("clientId")) {
            new MyThreed().execute();
        } else {
            Log.e("Client Id: ", Hawk.get("clientId", "Doesn't Have ID :("));
        }

//        Intent i = getIntent();
//        if (i != null) {
//            if (i.hasExtra("isLogin")) {
//                isLogin = i.getBooleanExtra("isLogin", false);
//            }
//        }

//        if (Hawk.contains("user")) {
//            isLogin = true;
//        } else {
//            isLogin = false;
//        }
        isLogin = Hawk.contains("user");

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        if (isLogin) {
            getSupportActionBar().setTitle(R.string.nav_profile);
            replaceFragments(new ProfileFragment(), ProfileFragment.class.getSimpleName());
        } else {
            getSupportActionBar().setTitle(R.string.nav_home);
            replaceFragments(new HomePageFragment(), HomePageFragment.class.getSimpleName());
        }


        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        home = navigationView.getMenu().findItem(R.id.nav_home);
        profile = navigationView.getMenu().findItem(R.id.nav_profile);
        fav = navigationView.getMenu().findItem(R.id.nav_favorite_contractors);
        support = navigationView.getMenu().findItem(R.id.nav_support);
        regisiter = navigationView.getMenu().findItem(R.id.nav_register_as_contractor);
        login = navigationView.getMenu().findItem(R.id.nav_login);
        logout = navigationView.getMenu().findItem(R.id.nav_logout);

        isUserLogin(isLogin);
    }

    private void isUserLogin(boolean isLogin) {
        if (isLogin) {
            home.setVisible(false);
            profile.setVisible(true);
            fav.setVisible(false);
            support.setVisible(true);
            regisiter.setVisible(false);
            login.setVisible(false);
            logout.setVisible(true);
        } else {
            home.setVisible(true);
            profile.setVisible(false);
            fav.setVisible(true);
            support.setVisible(true);
            regisiter.setVisible(true);
            login.setVisible(true);
            logout.setVisible(false);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (isLogin) {
                ProfileFragment myFragment = (ProfileFragment) getSupportFragmentManager().findFragmentByTag(ProfileFragment.class.getSimpleName());
                if (myFragment != null && myFragment.isVisible()) {
                    finish();
                } else {
                    super.onBackPressed();
                }
            } else {
                HomePageFragment myFragment = (HomePageFragment) getSupportFragmentManager().findFragmentByTag(HomePageFragment.class.getSimpleName());
                if (myFragment != null && myFragment.isVisible()) {
                    finish();
                } else {
                    super.onBackPressed();
                }
            }
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            replaceFragments(new HomePageFragment(), HomePageFragment.class.getSimpleName());
        } else if (id == R.id.nav_favorite_contractors) {
            replaceFragments(new FavoriteContractorsFragment(), FavoriteContractorsFragment.class.getSimpleName());
        } else if (id == R.id.nav_support) {
            replaceFragments(new SupportFragment(), SupportFragment.class.getSimpleName());
        } else if (id == R.id.nav_profile) {
            replaceFragments(new ProfileFragment(), ProfileFragment.class.getSimpleName());
        } else if (id == R.id.nav_register_as_contractor) {
            replaceFragments(new NewAccountFragment(), NewAccountFragment.class.getSimpleName());
        } else if (id == R.id.nav_login) {
            startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(getApplicationContext(), HomePageActivity.class);
//            intent.putExtra("isLogin", false);
            Hawk.delete("user");
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public class MyThreed extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... params) {
            try {
                AdvertisingIdClient.Info idClient = AdvertisingIdClient.getAdvertisingIdInfo(HomePageActivity.this);

                Log.e("Id: ", "" + idClient.getId());
                Hawk.put("clientId", idClient.getId());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesNotAvailableException e) {
                e.printStackTrace();
            } catch (GooglePlayServicesRepairableException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
