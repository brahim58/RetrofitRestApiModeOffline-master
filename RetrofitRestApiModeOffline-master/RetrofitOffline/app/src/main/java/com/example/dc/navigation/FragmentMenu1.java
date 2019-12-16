package com.example.dc.navigation;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dc.navigation.adapters.IconListAdapter;
import com.example.dc.navigation.models.Icon;
import com.example.dc.navigation.models.IconList;
import com.example.dc.navigation.network.GetDataService;
import com.example.dc.navigation.network.RetrofitInstance;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FragmentMenu1 extends Fragment {

    private RecyclerView recyclerView;
    private IconListAdapter adapter;

    private ProgressDialog mDialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_menu1, container, false);

        mDialog = new ProgressDialog(getContext());
        mDialog.setMessage("Loading Data...");
        mDialog.setCancelable(false);
        mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDialog.setIndeterminate(true);
        mDialog.show();

        GetDataService service = RetrofitInstance.getRetrofitInstance(getContext()).create(GetDataService.class);
        Call<IconList> call = service.getInforData();
        Log.wtf("URL Called", call.request().url() + "");

        call.enqueue(new Callback<IconList>() {
            @Override
            public void onResponse(Call<IconList> call, Response<IconList> response) {
                Toast.makeText(getContext(), response.body().getIconeList().size() + "", Toast.LENGTH_LONG).show();
                generateEventsList(view, response.body().getIconeList());
                mDialog.dismiss();
            }

            @Override
            public void onFailure(Call<IconList> call, Throwable t) {
                Log.wtf("Error URL Called", t.getMessage() + "Something went wrong...Please try later!");
                mDialog.dismiss();
            }
        });
        return view;
    }


    private void generateEventsList(View view, ArrayList<Icon> empDataList) {
        recyclerView =  view.findViewById(R.id.recycler_row);

        adapter = new IconListAdapter(getContext(), empDataList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Information");
    }
}
