package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.data.service.model.Category;

public class ServiceTypeActivity extends BaseActivity implements View.OnClickListener {

    Spinner city_Spinner, service_Spinner;
    Button search;
    private int check = 0;
    Category category;
    List<String> cities;
    List<String> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_type);
        StatusBarUtil.setTranslucent(this, 50);
        initToolbar(R.string.title_activity_service_type);
        CollapsingToolbarLayout toolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        toolbarLayout.setTitle("");

        Intent i = getIntent();

        String obj = i.getStringExtra("object");

        category = new Gson().fromJson(obj, Category.class);

        city_Spinner = (Spinner) findViewById(R.id.city_spinner);
        service_Spinner = (Spinner) findViewById(R.id.service_type__spinner);
        search = (Button) findViewById(R.id.search_button);
        search.setOnClickListener(this);

        cities = new ArrayList<String>();
        cities.add(getString(R.string.txt_riyadh));
        cities.add(getString(R.string.txt_jeddah));
        cities.add(getString(R.string.txt_dammam));

        ArrayAdapter<String> cityAdapter = new ArrayAdapter<String>(this, R.layout.item_row_spinner, cities);
        cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city_Spinner.setAdapter(cityAdapter);

        services = new ArrayList<String>();

        for (int j = 0; j < category.getCategories().size(); j++) {
            services.add(category.getCategories().get(j).getName());
        }

        ArrayAdapter<String> serviceType = new ArrayAdapter<String>(this, R.layout.item_row_spinner, services);
        serviceType.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        service_Spinner.setAdapter(serviceType);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.search_button:
                Intent intent = new Intent(ServiceTypeActivity.this, ContractorsActivity.class);
                int cityPosition = city_Spinner.getSelectedItemPosition();
                intent.putExtra("cityId", (cityPosition + 1) + "");
                int servicePosition = service_Spinner.getSelectedItemPosition();
                intent.putExtra("serviceId", category.getCategories().get(servicePosition).getID()+"");
                startActivity(intent);
                break;
        }
    }
}
