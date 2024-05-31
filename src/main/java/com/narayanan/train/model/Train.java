package com.narayanan.train.model;

import java.util.List;

public class Train {
    private int trainNumber;
    private String trainName;
    private String departureTime;
    private String arrivalTime;
    private List<String> trainRoutes;
    private int totalSeats;
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

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(String arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public List<String> getTrainRoutes() {
        return trainRoutes;
    }

    public void setTrainRoutes(List<String> trainRoutes) {
        this.trainRoutes = trainRoutes;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    @Override
    public String toString() {
        return "Train{" +
                "trainNumber=" + trainNumber +
                ", trainName='" + trainName + '\'' +
                ", departureTime='" + departureTime + '\'' +
                ", arrivalTime='" + arrivalTime + '\'' +
                ", trainRoutes=" + trainRoutes +
                ", totalSeats=" + totalSeats +
                ", fare=" + fare +
                '}';
    }
}
