package com.example.dc.navigation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dc.navigation.adapters.IconListAdapter;
import com.example.dc.navigation.db.Database;
import com.example.dc.navigation.models.Icon;

import java.util.ArrayList;


public class FragmentMenu2 extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<Icon> listContact = new ArrayList<>();
    private Database database;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu2, container, false);
        database = Database.getInstance(getContext());
        database.cleanTable();

        Icon a = new Icon("Title 1", "Desc", "https://findicons.com/files/icons/360/emoticons/128/satisfied.png");
        Icon b = new Icon("Title 2", "Desc", "https://cdn.icon-icons.com/icons2/722/PNG/256/Smiley-16_256_icon-icons.com_62518.png");
        Icon c = new Icon("Title 3", "Desc", "https://findicons.com/files/icons/360/emoticons/128/ok.png");
        database.insertIcon(a);
        database.insertIcon(b);
        database.insertIcon(c);

        Toast.makeText(getContext(), listContact.size()+ "size", Toast.LENGTH_SHORT);
        listContact = database.listAllIcon();

        IconListAdapter adapter = new IconListAdapter(getContext(), listContact);
        recyclerView =  view.findViewById(R.id.recycler_row_db);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Information");
    }
}
