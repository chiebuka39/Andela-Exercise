package com.harrricdev.edwin.andelaexercise.Main;

import com.harrricdev.edwin.andelaexercise.Models.Item;

import java.util.List;

/**
 * Created by edwin on 3/6/17.
 */

public interface MainContract {

    public interface MainView{
        void setBasePresenter(MainPresenter mainPresenter);
        void showDevelopers(List<Item> devs);
    }

    public interface  MainPresenter{

        void loadDevelopers();

    }
}
