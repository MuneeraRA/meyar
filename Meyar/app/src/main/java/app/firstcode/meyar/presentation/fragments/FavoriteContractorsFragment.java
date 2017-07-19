package app.firstcode.meyar.presentation.fragments;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Collection;

import app.firstcode.meyar.R;
import app.firstcode.meyar.base.BaseFragment;
import app.firstcode.meyar.data.service.model.ContractorSearchModel;
import app.firstcode.meyar.presentation.adapter.ContractorsAdapter;

public class FavoriteContractorsFragment extends BaseFragment {

    RecyclerView recyclerView;
    ContractorsAdapter contractorsAdapter;
    ArrayList<ContractorSearchModel> searchModels = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorite_contractors, null, false);
        toolbarSetTitle(R.string.nav_favorite_contractors);
        setCheckedItem(R.id.nav_favorite_contractors);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        contractorsAdapter = new ContractorsAdapter(getActivity(), searchModels);
        recyclerView.setAdapter(contractorsAdapter);
//
//        if (Hawk.contains("favoriteList")) {
//            searchModels.clear();
//            searchModels.addAll((ArrayList<ContractorSearchModel>) Hawk.get("favoriteList"));
//            contractorsAdapter.notifyDataSetChanged();
//        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Hawk.contains("favoriteList")) {
            searchModels.clear();
            searchModels.addAll((ArrayList<ContractorSearchModel>) Hawk.get("favoriteList"));
            contractorsAdapter.notifyDataSetChanged();
        }
    }
}
