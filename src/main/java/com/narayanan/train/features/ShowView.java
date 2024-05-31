package com.narayanan.train.features;

import com.narayanan.train.database.DataBase;
import com.narayanan.train.model.Train;
import com.narayanan.train.transaction.TransactionView;

import java.util.Scanner;

public class ShowView {
    private ShowModel showModel;
    private Scanner scanner = new Scanner(System.in);

    public ShowView() {
        showModel = new ShowModel(this);
    }

    public void init() {
        while (true) {
            System.out.println("\n1.List Train Routes\n2.Search Trains\n3.Booking\n9.Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    listTrain();
                    break;
                case 2:
                    searchTrain();
                    break;
                case 3:
                    new TransactionView().bookTrain();
                    break;
                case 9:
                    DataBase.getInstance().backUp();
                    return;
            }
        }
    }


    public boolean searchTrain() {
        System.out.println("##Plan my journey##");
        System.out.print("From station :");
        String from = scanner.next();
        System.out.print("To station :");
        String to = scanner.next();
        return showModel.searchTrain(from, to);
    }

    private void listTrain() {
        showModel.getTrains();
    }

    public void showTrain(Train train) {
        System.out.println("-------------------------------------------------------------------------");
        System.out.println("Train Number :" + train.getTrainNumber());
        System.out.println("Train Name :" + train.getTrainName());
        System.out.println("Train Departure Time :" + train.getDepartureTime());
        System.out.println("Train Arrival Time :" + train.getArrivalTime());
        System.out.println("Train Routes :" + train.getTrainRoutes());
        System.out.println("Total seats :" + train.getTotalSeats());
        System.out.println("Fare :" + train.getFare());
        System.out.println();
    }

    public void showError(String trainNotFound) {
        System.out.println("\n" + trainNotFound);
    }
}
