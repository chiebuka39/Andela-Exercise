package com.harrricdev.edwin.andelaexercise.Main;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.harrricdev.edwin.andelaexercise.Models.Item;
import com.harrricdev.edwin.andelaexercise.R;
import com.harrricdev.edwin.andelaexercise.Utils.ItemClickSupport;
import com.harrricdev.edwin.andelaexercise.Widgets.DevelopersRecyclerView;
import com.harrricdev.edwin.andelaexercise.Widgets.Divider;
import com.harrricdev.edwin.andelaexercise.profiles.ProfileActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements MainContract.MainView {


    DevelopersRecyclerView developersRecyclerView;
    TextView emptyView;

    List<Item> developers = Collections.emptyList();
    MainContract.MainPresenter mMainPresenter;
    MainAdapter adapter;

    public static MainFragment newInstance(){
        return new MainFragment();
    }

    public MainFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        developersRecyclerView =(DevelopersRecyclerView) view.findViewById(R.id.developersList);
        emptyView = (TextView) view.findViewById(R.id.empty_view);

        adapter = new MainAdapter(getActivity(), developers);

        developersRecyclerView.showIfEmpty(emptyView);


        developersRecyclerView.addItemDecoration(new Divider(getActivity(), LinearLayoutManager.VERTICAL));
        developersRecyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext());


        developersRecyclerView.setLayoutManager(llm);

        developersRecyclerView.setAdapter(adapter);

        mMainPresenter.loadDevelopers();



        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ItemClickSupport.addTo(developersRecyclerView).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent intent = new Intent(getActivity().getApplicationContext(), ProfileActivity.class);
                intent.putExtra("LOGIN", developers.get(position).getLogin());
                startActivity(intent);
                //Toast.makeText(getActivity().getApplicationContext(), developers.get(position).getLogin() , Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void setBasePresenter(MainContract.MainPresenter mainPresenter) {
        mMainPresenter =  checkNotNull(mainPresenter);
    }

    @Override
    public void showDevelopers(List<Item> devs) {
        developers = devs;
        adapter.reloadAdapter(developers);
    }
}
