package app.firstcode.meyar.presentation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;

import java.util.ArrayList;
import java.util.List;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseActivity;
import app.firstcode.meyar.data.service.ApiClient;
import app.firstcode.meyar.data.service.ApiInterface;
import app.firstcode.meyar.data.service.model.ContractorSearchModel;
import app.firstcode.meyar.presentation.adapter.ContractorsAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContractorsActivity extends BaseActivity {

    RecyclerView contractor_list;
    //    ArrayList<String> stringArrayList = new ArrayList<>();
//    ArrayList<String> searchArray = new ArrayList<>();
    ArrayList<ContractorSearchModel> contractorSmallDatas = new ArrayList<>();
    ArrayList<ContractorSearchModel> contractorSmallDatasCopy = new ArrayList<>();
    ContractorsAdapter contractorsAdapter;
    String filteredName = "";
    String cityId;
    String serviceId;
    boolean submitClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractors);
        initToolbarWithSearchView(R.string.contractor);

        contractor_list = (RecyclerView) findViewById(R.id.contractor_list);
        contractor_list.setHasFixedSize(true);
        contractor_list.setLayoutManager(new LinearLayoutManager(this));

        contractorsAdapter = new ContractorsAdapter(this, contractorSmallDatas);
        contractor_list.setAdapter(contractorsAdapter);


        Intent i = getIntent();

        cityId = i.getStringExtra("cityId");
        serviceId = i.getStringExtra("serviceId");

        Log.e("CityId: ", cityId + "");
        Log.e("ServiceId: ", serviceId + "");

        getContractors(cityId, serviceId, filteredName);

        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                searchView.setQuery(filteredName, false);

            }

            @Override
            public void onSearchViewClosed() {
//                Toast.makeText(ContractorsActivity.this, "Here", Toast.LENGTH_SHORT).show();
                if (!submitClicked) {
                    getContractors(cityId, serviceId, "");
                } else {
                    submitClicked = false;
                }
//                searchView.setQuery("asdasd asd asd asd", false);
            }
        });

        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                submitClicked = true;
                getContractors(cityId, serviceId, query);
                searchView.setQuery(filteredName, false);
                if (searchView.isSearchOpen()) {
                    searchView.closeSearch();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (!submitClicked) {
                    getContractors(cityId, serviceId, newText);
                } else {
//                    submitClicked = false;
                }
                return false;
            }
        });

    }

    private void getContractors(String cityId, String serviceId, final String name) {
        filteredName = name;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<List<ContractorSearchModel>> call = apiService.searchContractor(serviceId, cityId, name);
        call.enqueue(new Callback<List<ContractorSearchModel>>() {
            @Override
            public void onResponse(Call<List<ContractorSearchModel>> call, Response<List<ContractorSearchModel>> response) {
                contractorSmallDatas.clear();
                Log.e("DDDDD", response.body().size() + "    " + name);
                if (response.body().size() != 0) {
                    contractorSmallDatas.addAll(response.body());
                } else {
                }
                contractorsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ContractorSearchModel>> call, Throwable t) {
                Toast.makeText(ContractorsActivity.this, "" + getString(R.string.msg_checking_network), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);

        return true;
    }

    @Override
    public void onBackPressed() {
        if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }
}
