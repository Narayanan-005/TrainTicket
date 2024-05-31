package com.narayanan.train.database;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.narayanan.train.model.Train;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataBase {

    private static DataBase dataBase;
    private List<Train> trains = new ArrayList<>();

    private DataBase() {

    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public boolean addTrain(Train train) {
        for (Train train1 : trains) {
            if (train1.getTrainNumber() == train.getTrainNumber()) {
                return false;
            }
        }
        trains.add(train);
        return true;
    }

    public boolean isDataExist() {
        System.out.println(trains.size());
        if (trains.size() == 0) {
            return false;
        }
        return true;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public List<Train> searchTrain(String from, String to) {
        List<Train> searchResult = new ArrayList<>();
        for (Train train : trains) {
            List<String> routes = train.getTrainRoutes();
            boolean isFromExist = false;
            for (String s : routes) {
                if (isFromExist && s.equalsIgnoreCase(to)) {
                    searchResult.add(train);
                    break;
                }
                if (s.equalsIgnoreCase(from)) {
                    isFromExist = true;
                }
            }
        }
        return searchResult;
    }

    public double getTotalFare(int trainNumber, int n) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train.getFare() * n;
            }
        }
        return 0.0;
    }


    public void retrive() {
        this.trains = retriveTrain();
    }

    private List<Train> retriveTrain() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Train/trains.json");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("can't create file");
            }
        }
        List<Train> trains1 = null;
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String temp = null;
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }
            Type type = new TypeToken<List<Train>>() {
            }.getType();
            trains1 = gson.fromJson(builder.toString(), type);
        } catch (IOException e) {
            System.out.println("can't read");
        }
        if (trains1 == null) {
            return new ArrayList<>();
        }
        return trains1;
    }

    public void backUp() {
        backupTrains();
    }

    private void backupTrains() {
        File file = new File("/home/prabakar/Documents/Task/console_application/Train/trains.json");
        Gson gson = new Gson();
        String s = gson.toJson(trains);
        try {
            FileWriter writer = new FileWriter(file);
            writer.write(s);
        } catch (IOException e) {
            System.out.println("can't write");
        }

    }


}
