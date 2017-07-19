package app.firstcode.meyar.presentation.fragments;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.Category;
import app.firstcode.meyar.presentation.activities.ServiceTypeActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePageFragment extends BaseFragment {

    private LinearLayout typeOne;
    private LinearLayout typeTwo;
    private LinearLayout typeThree;
    private LinearLayout typeFore;
    private Boolean isDataReady = false;

    ArrayList<Category> categories = new ArrayList<>();

    /**
     * Find the Views in the layout<br />
     * <br />
     * Auto-created on 2017-07-07 14:31:20 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    private void findViews(View rootView) {
        typeOne = (LinearLayout) rootView.findViewById(R.id.type_one);
        typeTwo = (LinearLayout) rootView.findViewById(R.id.type_two);
        typeThree = (LinearLayout) rootView.findViewById(R.id.type_three);
        typeFore = (LinearLayout) rootView.findViewById(R.id.type_fore);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home_page, null, false);
        toolbarSetTitle(R.string.nav_home);
        setCheckedItem(R.id.nav_home);
        findViews(v);

        typeOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataReady)
                    startServiceActivity(categories.get(0));
                else
                    Toast.makeText(getActivity(), getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });
        typeTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataReady)
                    startServiceActivity(categories.get(1));
                else
                    Toast.makeText(getActivity(), getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });
        typeThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataReady)
                    startServiceActivity(categories.get(2));
                else
                    Toast.makeText(getActivity(), getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });
        typeFore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDataReady)
                    startServiceActivity(categories.get(3));
                else
                    Toast.makeText(getActivity(), getString(R.string.please_try_again), Toast.LENGTH_SHORT).show();
            }
        });

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<Category>> call = apiService.getCategories();
        call.enqueue(new Callback<List<Category>>() {
            @Override
            public void onResponse(Call<List<Category>> call, Response<List<Category>> response) {
                isDataReady = true;
                categories.clear();
                categories.addAll(response.body());
                Log.e("Response Success: ", response.body().get(0).getName() + "");
                Log.e("Response Success: ", response.body().size() + "");
            }

            @Override
            public void onFailure(Call<List<Category>> call, Throwable t) {
                isDataReady = false;
                Toast.makeText(getActivity(), "" + getString(R.string.msg_checking_network), Toast.LENGTH_SHORT).show();
            }
        });

        return v;
    }

    private void startServiceActivity(Category category) {
        Intent intent = new Intent(getActivity(), ServiceTypeActivity.class);
        String object = ConvertObjectToString(category);
        intent.putExtra("object", object);
        getActivity().startActivity(intent);
    }

    private String ConvertObjectToString(Category category) {
        return new Gson().toJson(category, Category.class);
    }
}
