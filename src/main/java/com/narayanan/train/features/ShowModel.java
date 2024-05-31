package com.narayanan.train.features;

import com.narayanan.train.database.DataBase;
import com.narayanan.train.model.Train;

import java.util.List;

public class ShowModel {
    private ShowView showView;

    ShowModel(ShowView showView) {
        this.showView = showView;
    }

    public void getTrains() {
        List<Train> trains = DataBase.getInstance().getTrains();
        for (Train train : trains) {
            showView.showTrain(train);
        }
    }

    public boolean searchTrain(String from, String to) {
        List<Train> trains = DataBase.getInstance().searchTrain(from, to);
        if (trains.size() != 0) {
            for (Train train : trains) {
                showView.showTrain(train);
            }
            return true;
        } else {
            showView.showError("Train not found");
            return false;
        }
    }
}
