package com.narayanan.train.transaction;

import com.narayanan.train.database.DataBase;
import com.narayanan.train.model.Passenger;

import java.util.List;

public class TransactionModel {
    private TransactionView transactionView;

    TransactionModel(TransactionView transactionView) {
        this.transactionView = transactionView;
    }

    public double getTotalFare(int trainNumber, int n) {
        return DataBase.getInstance().getTotalFare(trainNumber, n);
    }

    void bookTickets(int trainNumber, List<Passenger> passengers)
}
