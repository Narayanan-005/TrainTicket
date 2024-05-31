package org.example;

import java.util.List;

public class Train {
    private int trainNumber;
    private String trainName;
    private String trainDepartureTime;
    private String trainArrivalTime;
    private List<String> trainRoutes;
    private int seats;
    private double fare;

    public int getTrainNumber() {
        return trainNumber;
    }

    public void setTrainNumber(int trainNumber) {
        this.trainNumber = trainNumber;
    }

    public String getTrainName() {
        return trainName;
    }

    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }

    public String getTrainDepartureTime() {
        return trainDepartureTime;
    }

    public void setTrainDepartureTime(String trainDepartureTime) {
        this.trainDepartureTime = trainDepartureTime;
    }

    public String getTrainArrivalTime() {
        return trainArrivalTime;
    }

    public void setTrainArrivalTime(String trainArrivalTime) {
        this.trainArrivalTime = trainArrivalTime;
    }

    public List<String> getTrainRoutes() {
        return trainRoutes;
    }

    public void setTrainRoutes(List<String> trainRoutes) {
        this.trainRoutes = trainRoutes;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}