package com.narayanan.train.transaction;

import com.narayanan.train.features.ShowView;
import com.narayanan.train.model.Passenger;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TransactionView {
    private TransactionModel transactionModel;
    private Scanner scanner = new Scanner(System.in);

    public TransactionView() {
        this.transactionModel = new TransactionModel(this);
    }

    public void bookTrain() {
        if (new ShowView().searchTrain()) {
            System.out.print("Enter train number");
            int trainNumber = scanner.nextInt();
            System.out.print("Enter number of passenger");
            int n = scanner.nextInt();
            List<Passenger> passengers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                System.out.println("Enter Passenger " + (i + 1) + " details");
                Passenger passenger = new Passenger();
                System.out.print("Name :");
                passenger.setName(scanner.nextLine());
                System.out.print("Age :");
                passenger.setAge(scanner.nextInt());
                System.out.println("Gender :");
                passenger.setGender(scanner.next());
                passengers.add(passenger);
            }
            double totalFare = transactionModel.getTotalFare(trainNumber, n);
            System.out.print("Total Fare :"+totalFare);
            while (true){
                System.out.println("\n1.Pay\n2.Cancel\n3.Reschedule");
                int choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        transactionModel.bookTickets(trainNumber,passengers);
                        break;
                    case 2:
                        return;
                    case 3:
                        bookTrain();
                        break;
                }
            }
        }
    }
}
