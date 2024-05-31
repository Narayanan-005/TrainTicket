package org.example;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private List<Train> trains = new ArrayList<>();
    private List<Ticket> tickets = new ArrayList<>();
    private static DataBase dataBase;
    private long pnrNo = 1;
    private int passengerId = 1;

    private DataBase() {

    }

    public static DataBase getInstance() {
        if (dataBase == null) {
            dataBase = new DataBase();
        }
        return dataBase;
    }

    public boolean chekData() {
        if (trains.size() != 0) {
            return true;
        }
        return false;
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

    public List<Train> getAllTrain() {
        return trains;
    }


    public List<Train> searchTrain(String from, String to) {
        List<Train> trains1 = new ArrayList<>();
        boolean isFromExist = false;
        for (Train train : trains) {
            List<String> strings = train.getTrainRoutes();
            for (String s : strings) {
                if (isFromExist && s.equalsIgnoreCase(to)) {
                    trains1.add(train);
                    break;
                }
                if (s.equalsIgnoreCase(from)) {
                    isFromExist = true;
                }
            }
            isFromExist = false;
        }
        return trains1;
    }

    public Train getTotalFare(int trainNumber) {
        for (Train train : trains) {
            if (train.getTrainNumber() == trainNumber) {
                return train;
            }
        }
        return null;
    }

    public boolean bookTickets(int trainNumber, List<Passenger> passengers, String from, String to) {
        Train train = getTotalFare(trainNumber);
        if (train.getSeats() >= passengers.size()) {
            Ticket ticket = new Ticket();
            ticket.setTrainNumber(train.getTrainNumber());
            ticket.setTrainName(train.getTrainName());
            ticket.setArrivalTime(train.getTrainArrivalTime());
            ticket.setDepartureTime(train.getTrainDepartureTime());
            ticket.setFrom(from);
            ticket.setTo(to);
            ticket.setPassengers(passengers);
            ticket.setTotalFare(train.getFare() * passengers.size());
            ticket.setPnrNumber(pnrNo++);
            updateStatus(passengers, "CNF", passengers.size() - 1);
            tickets.add(ticket);
            train.setSeats(train.getSeats() - passengers.size());
            return true;
        }
        return false;
    }

    private void updateStatus(List<Passenger> passengers, String status, int end) {
        for (int i = 0; i <= end; i++) {
            passengers.get(i).setStatus(status);
            passengers.get(i).setId(passengerId++);
        }
    }


    public void retrive() {
        trains = retriveTrains();
        tickets = retriveTickets();
        if (tickets.size() != 0) {
            Ticket ticket = tickets.get(tickets.size() - 1);
            pnrNo = ticket.getPnrNumber() + 1;
            passengerId = ticket.getPassengers().get(ticket.getPassengers().size() - 1).getId() + 1;
        }
    }

    private List<Train> retriveTrains() {
        File file = new File("/home/prabakar/Documents/Task/console_application/TrainSecond/trainjson/trains.json");
        List<Train> trains1 = null;
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }
            Type type = new TypeToken<List<Train>>() {
            }.getType();
            trains1 = gson.fromJson(builder.toString(), type);
        } catch (IOException e) {
            System.out.println("can't read");
        }
        return (trains1 == null ? new ArrayList<>() : trains1);
    }

    private List<Ticket> retriveTickets() {
        File file = new File("/home/prabakar/Documents/Task/console_application/TrainSecond/trainjson/tickets.json");
        List<Ticket> tickets1 = null;
        try {
            Gson gson = new Gson();
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder builder = new StringBuilder();
            String temp;
            while ((temp = reader.readLine()) != null) {
                builder.append(temp);
            }
            Type type = new TypeToken<List<Ticket>>() {
            }.getType();
            tickets1 = gson.fromJson(builder.toString(), type);
        } catch (IOException e) {
            System.out.println("can't read");
        }
        return (tickets1 == null ? new ArrayList<>() : tickets1);
    }

    public void backUp() {
        backUpTrains();
        backUpTickets();
    }

    private void backUpTrains() {
        File file = new File("/home/prabakar/Documents/Task/console_application/TrainSecond/trainjson/trains.json");
        try {
            FileWriter writer = new FileWriter(file);
            Gson gson = new Gson();
            String obj = gson.toJson(trains);
            writer.write(obj);
            writer.close();
        } catch (IOException e) {
            System.out.println("can't write");
        }
    }

    private void backUpTickets() {
        File file = new File("/home/prabakar/Documents/Task/console_application/TrainSecond/trainjson/tickets.json");
        try {
            FileWriter writer = new FileWriter(file);
            Gson gson = new Gson();
            String obj = gson.toJson(tickets);
            writer.write(obj);
            writer.close();
        } catch (IOException e) {
            System.out.println("can't write");
        }
    }
}
