package com.narayanan.train.setup;

import com.narayanan.train.TrainMain;
import com.narayanan.train.database.DataBase;
import com.narayanan.train.features.ShowView;
import com.narayanan.train.model.Train;

import javax.xml.crypto.Data;
import java.util.Scanner;

public class SetUpView {
    private SetUpModel setUpModel;
    private Scanner scanner = new Scanner(System.in);

    public SetUpView() {
        setUpModel = new SetUpModel(this);
    }

    public void init() {
        DataBase.getInstance().retrive();
        System.out.println("Welcome to " + TrainMain.APP_NAME);
        if (DataBase.getInstance().isDataExist()) {
            new ShowView().init();
        } else {
            setupTrain();
        }
    }

    private void setupTrain() {
        System.out.print("No of Schedules :");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Schedule " + (i + 1) + " :");
            Train train = new Train();
            System.out.print("Train Number :");
            train.setTrainNumber(scanner.nextInt());
            System.out.print("Train Name :");
            scanner.nextLine();
            train.setTrainName(scanner.nextLine());
            System.out.print("Train Departure Time :");
            train.setDepartureTime(scanner.next());
            System.out.print("Train Arrival Time :");
            train.setArrivalTime(scanner.next());
            System.out.print("Train Routes :");
            scanner.nextLine();
            String routes = scanner.nextLine();
            System.out.print("Total seats :");
            train.setTotalSeats(scanner.nextInt());
            System.out.print("Fare :");
            train.setFare(scanner.nextDouble());
            setUpModel.addTrain(train, routes);
        }
        new ShowView().init();

    }
}
