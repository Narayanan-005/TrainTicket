package com.narayanan.train.setup;

import com.narayanan.train.database.DataBase;
import com.narayanan.train.model.Train;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class SetUpModel {
    private SetUpView setUpView;

    SetUpModel(SetUpView setUpView) {
        this.setUpView = setUpView;
    }

    public boolean addTrain(Train train, String routes) {
        List<String> strings = new ArrayList<>();
        String arr[] = routes.split(",");
        for (String s : arr) {
            strings.add(s);
        }
        train.setTrainRoutes(strings);
        return DataBase.getInstance().addTrain(train);
    }
}
