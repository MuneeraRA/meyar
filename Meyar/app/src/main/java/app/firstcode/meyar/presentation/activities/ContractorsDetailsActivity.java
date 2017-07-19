package app.firstcode.meyar.presentation.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.orhanobut.hawk.Hawk;
import com.whinc.widget.ratingbar.RatingBar;

import java.util.ArrayList;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.ContractorSearchModel;
import app.firstcode.meyar.data.service.model.RateResponse;
import de.hdodenhof.circleimageview.CircleImageView;
import me.crosswall.lib.coverflow.CoverFlow;
import me.crosswall.lib.coverflow.core.PageItemClickListener;
import me.crosswall.lib.coverflow.core.PagerContainer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractorsDetailsActivity extends BaseActivity implements View.OnClickListener {

    ContractorSearchModel contractor;
    CircleImageView profile_image;
    MaterialDialog materialDialog;
    TextView ratingCount, add_or_remove_text;
    Button contractorName, contractorEmail, contractorNumber;
    LinearLayout addRate, add_to_favorite;
    ImageView ic_heart, call_button;
    RatingBar ratingBar;

    public boolean isInFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractors_details);

        initToolbar(R.string.contractor_detail);

        Intent i = getIntent();

        String object = i.getStringExtra("contractor");

        contractor = new Gson().fromJson(object, ContractorSearchModel.class);


        profile_image = (CircleImageView) findViewById(R.id.profile_image);
        add_or_remove_text = (TextView) findViewById(R.id.add_or_remove_text);
        ratingCount = (TextView) findViewById(R.id.ratingCount);
        contractorName = (Button) findViewById(R.id.contractorName);
        contractorEmail = (Button) findViewById(R.id.contractorEmail);
        contractorNumber = (Button) findViewById(R.id.contractorNumber);
        ic_heart = (ImageView) findViewById(R.id.ic_heart);
        call_button = (ImageView) findViewById(R.id.call_button);
        addRate = (LinearLayout) findViewById(R.id.addRate);
        add_to_favorite = (LinearLayout) findViewById(R.id.add_to_favorite);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        call_button.setOnClickListener(this);
        add_to_favorite.setOnClickListener(this);
        addRate.setOnClickListener(this);

        isInFavorite = checkIsInFavorite(contractor);
        changeFavoriteStatus();

        contractorName.setPressed(true);
        contractorEmail.setPressed(true);
        contractorNumber.setPressed(true);

        Glide.with(this).load(contractor.getProfilePicture()).placeholder(R.drawable.ic_user_place_holder).error(R.drawable.ic_user_place_holder).into(profile_image);
        ratingCount.setText("(" + contractor.getRateCount() + "تقييم)");
        contractorName.setText(contractor.getName());
        contractorEmail.setText(contractor.getEmail());
        contractorNumber.setText(contractor.getMobile());
        ratingBar.setCount(contractor.getRate());

        PagerContainer container = (PagerContainer) findViewById(R.id.pager_container);
        ViewPager pager = container.getViewPager();
//        pager.setAdapter(new MyPagerAdapter(contractor.getImage(),contractor.getImage2(),contractor.getImage3()));
        String[] images = new String[3];
        images[0] = contractor.getImage();
        images[1] = contractor.getImage2();
        images[2] = contractor.getImage3();
        pager.setAdapter(new MyPagerAdapter(images, this));
        pager.setClipChildren(false);
        //
        pager.setOffscreenPageLimit(15);

        container.setPageItemClickListener(new PageItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
//                Toast.makeText(ContractorsDetailsActivity.this, "position:" + position, Toast.LENGTH_SHORT).show();
            }
        });

        boolean showTransformer = getIntent().getBooleanExtra("showTransformer", true);


        if (showTransformer) {

            new CoverFlow.Builder()
                    .with(pager)
                    .scale(0.3f)
                    .pagerMargin(-30)
                    .spaceSize(0f)
                    .build();

        } else {
            pager.setPageMargin(30);
        }


    }

    private void changeFavoriteStatus() {
        if (isInFavorite) {
            add_or_remove_text.setText(getString(R.string.remove_favorite));
            ic_heart.setImageResource(R.drawable.ic_favorite);
            ic_heart.setColorFilter(ContextCompat.getColor(this, R.color.red));
        } else {
            add_or_remove_text.setText(getString(R.string.add_to_favorite));
            ic_heart.setImageResource(R.drawable.ic_favorite);
            ic_heart.setColorFilter(ContextCompat.getColor(this, R.color.gray_tint_color));
        }
    }

    private boolean checkIsInFavorite(ContractorSearchModel contractor) {
        if (Hawk.contains("favoriteList")) {
            ArrayList<ContractorSearchModel> contractorSearchModels = Hawk.get("favoriteList");
            for (ContractorSearchModel searchModel : contractorSearchModels) {
                if (searchModel.getEmail().equalsIgnoreCase(contractor.getEmail())) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addRate:
                showRateDialog();
                break;
            case R.id.call_button:
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + contractor.getMobile()));
                startActivity(intent);
                break;
            case R.id.add_to_favorite:
                if (isInFavorite) {
                    ArrayList<ContractorSearchModel> searchModels = Hawk.get("favoriteList");
                    for (int i = 0; i < searchModels.size(); i++) {
                        if (contractor.getEmail().equalsIgnoreCase(searchModels.get(i).getEmail())) {
                            searchModels.remove(searchModels.get(i));
                            i--;
                        }
                    }
                    Hawk.put("favoriteList", searchModels);
                    isInFavorite = false;
                    changeFavoriteStatus();
                } else {
                    if (Hawk.contains("favoriteList")) {
                        ArrayList<ContractorSearchModel> searchModels = Hawk.get("favoriteList");
                        searchModels.add(contractor);
                        Hawk.put("favoriteList", searchModels);
                        isInFavorite = true;
                        changeFavoriteStatus();
                    } else {
                        ArrayList<ContractorSearchModel> searchModels = new ArrayList<>();
                        searchModels.add(contractor);
                        Hawk.put("favoriteList", searchModels);
                        isInFavorite = true;
                        changeFavoriteStatus();
                    }
                }
                break;
        }
    }

    private class MyPagerAdapter extends PagerAdapter {
        String[] images;
        Context context;

        public MyPagerAdapter(String[] images, Context context) {
            this.images = images;
            this.context = context;
        }


//        private final String image;
//        private final String image2;
//        private final String image3;
//
//        public MyPagerAdapter(String image, String image2, String image3) {
//            this.image = image;
//            this.image2 = image2;
//            this.image3 = image3;
//        }

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            View view = LayoutInflater.from(ContractorsDetailsActivity.this).inflate(R.layout.item_cover, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.image_cover);

            Glide.with(context).load(images[position]).error(R.drawable.image_placeholder).placeholder(R.drawable.image_placeholder).into(imageView);
//            imageView.setImageDrawable(getResources().getDrawable(DemoData.covers[position]));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            container.addView(view);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Toast.makeText(ContractorsDetailsActivity.this, "" + position, Toast.LENGTH_SHORT).show();
                }
            });
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return images.length;
//            return DemoData.covers.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return (view == object);
        }
    }

    public void showRateDialog() {
        materialDialog = new MaterialDialog.Builder(this)
                .customView(R.layout.dialog_rate_custom_view, false)
                .showListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialog) {
                        final RatingBar ratingBar = (RatingBar) materialDialog.getCustomView().findViewById(R.id.ratingBar);
                        TextView dialog_action = (TextView) materialDialog.getCustomView().findViewById(R.id.dialog_action);
//
                        dialog_action.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                materialDialog.dismiss();
                                rateContractor(contractor.getID() + "f", getAdvertisingId(), ratingBar.getCount() + "");
                                Log.e("Rate Value: ", "" + ratingBar.getCount() + "AdvertisingId: " + getAdvertisingId());
                            }
                        });
                    }
                })
                .show();
    }

    public void rateContractor(String contractorId, String mobileId, String rateCount) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<RateResponse> call = apiService.rate(contractorId, mobileId, rateCount);

        call.enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {
                Log.e("Status: ", response.body().getMessage());
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                Log.e("Status: ", t.toString());
            }
        });
    }
}
