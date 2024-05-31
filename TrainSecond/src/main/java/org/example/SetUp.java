package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SetUp {
    private Scanner scanner = new Scanner(System.in);

    public void init() {
        DataBase.getInstance().retrive();
        if (DataBase.getInstance().chekData()) {
            features();
        } else {
            setUp();
        }
    }

    private void setUp() {
        System.out.print("Number of Schedules :");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            System.out.println("Schedule " + (i + 1));
            Train train = new Train();
            System.out.print("Train Number :");
            train.setTrainNumber(scanner.nextInt());
            System.out.print("Train Name :");
            scanner.nextLine();
            train.setTrainName(scanner.nextLine());
            System.out.print("Train Departure Time :");
            train.setTrainDepartureTime(scanner.nextLine());
            System.out.print("Train Arrival Time :");
            train.setTrainArrivalTime(scanner.nextLine());
            System.out.print("Train Routes :");
            String routes[] = scanner.nextLine().split(",");
            List<String> strings = new ArrayList<>();
            for (String s : routes) {
                strings.add(s);
            }
            train.setTrainRoutes(strings);
            System.out.print("Train seats :");
            train.setSeats(scanner.nextInt());
            System.out.print("Train Fare :");
            train.setFare(scanner.nextDouble());
            DataBase.getInstance().addTrain(train);
        }
        features();
    }

    private void features() {
        while (true) {
            System.out.println("\n1.Show trains\n2.Search trains\n3.Book Train\n9.Exit");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showTrains();
                    break;
                case 2:
                    searchTrain();
                    break;
                case 3:
                    bookTrains();
                    break;
                case 9:
                    DataBase.getInstance().backUp();
                    return;
            }
        }
    }

    private void bookTrains() {
        System.out.println("##Plan my journey##");
        System.out.print("From Station :");
        scanner.nextLine();
        String from = scanner.nextLine();
        System.out.print("To Staion :");
        String to = scanner.nextLine();
        if (searchAvaliTrain(from, to)) {
            System.out.print("Enter Train Number :");
            int trainNumber = scanner.nextInt();
            System.out.print("Enter Number of Passengers :");
            int n = scanner.nextInt();
            List<Passenger> passengers = new ArrayList<>();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                System.out.println("Passenger "+(i+1));
                Passenger passenger = new Passenger();
                System.out.print("Name :");
                passenger.setName(scanner.nextLine());
                System.out.print("Age :");
                passenger.setAge(scanner.nextInt());
                System.out.print("Gender :");
                scanner.nextLine();
                passenger.setGender(scanner.nextLine());
                passengers.add(passenger);
            }
            System.out.println("Total Fare :"+getTotalFare(trainNumber,passengers.size()));
            while (true) {
                System.out.println("\n1.Pay\n2.Cancel\n3.Reschedule");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        if(bookTickets(trainNumber, passengers,from,to)){
                            System.out.println("Tickets Booked Successfully");
                        }else {
                            System.out.println("Failed to Book / insufficient seats");
                        }
                        return;
                    case 2:
                        return;
                    case 3:
                        bookTrains();
                        return;
                }
            }
        }
    }

    private boolean bookTickets(int trainNumber, List<Passenger> passengers, String from, String to) {
        return DataBase.getInstance().bookTickets(trainNumber, passengers, from, to);
    }

    private double getTotalFare(int trainNumber, int size) {
        return DataBase.getInstance().getTotalFare(trainNumber).getFare()*size;
    }

    private void searchTrain() {
        System.out.println("##Plan my journey##");
        System.out.print("From Station :");
        scanner.nextLine();
        String from = scanner.nextLine();
        System.out.print("To Staion :");
        String to = scanner.nextLine();
        searchAvaliTrain(from, to);
    }

    private boolean searchAvaliTrain(String from, String to) {
        List<Train> trains = DataBase.getInstance().searchTrain(from, to);
        if (trains.size() != 0) {
            for (Train train : trains) {
                showDetails(train);
            }
            return true;
        } else {
            System.out.println("No trains Available");
            return false;
        }
    }

    private void showTrains() {
        List<Train> trains = DataBase.getInstance().getAllTrain();
        for (Train train : trains) {
            showDetails(train);
        }
    }

    private void showDetails(Train train) {
        System.out.println("Train Number :" + train.getTrainNumber());
        System.out.println("Train Name :" + train.getTrainName());
        System.out.println("Train Departure Time :" + train.getTrainDepartureTime());
        System.out.println("Train Arrival Time :" + train.getTrainArrivalTime());
        System.out.println("Train Routes :" + train.getTrainRoutes());
        System.out.println("Train seats :" + train.getSeats());
        System.out.println("Train Fare :" + train.getFare());
        System.out.println("----------------------------------------------------------------");
    }
}
