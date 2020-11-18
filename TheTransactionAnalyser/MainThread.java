package com.popov.HomeWork.TheTransactionAnalyser;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

// class MainThread has some user input logic and start the Analyser
public class MainThread implements Runnable {

    Thread thread;

    public MainThread() {
        // after we create MainThread, new Thread is created with Runnable target as this thread and with name
        thread = new Thread(this, "Main Thread");
        // then we register our thread after it run() method will be invoked
        thread.start();
    }

    @Override
    public void run() {
        // we get some data from user input
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter absolute path of csv file: ");
        String pathToFile = scanner.nextLine();
        File csvTransactionFile = new File(pathToFile);
        // we create 2 variables for date range
        Date fromDate = null;
        Date toDate = null;
        // this is condition for do-while loop
        boolean continueCondition = false;
        do {
            try {
                System.out.println("Enter searching range in appropriate date format: dd/MM/yyyy HH:mm:ss");
                System.out.print("  -> From what date: ");
                String fromDateString = scanner.nextLine();
                System.out.print("  -> Until what date: ");
                String toDateString = scanner.nextLine();
                // our dates store in Data type but for user we propose another format (we parse it behind the scene)
                fromDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(fromDateString);
                toDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(toDateString);
                // if after parse there is no exception condition become true - exit loop
                continueCondition = true;
            } catch (ParseException parseException) {
                System.out.println("Wrong date format! Try Again!");
            }
        } while (!continueCondition); // we entering dates until it's format will be correct
        // check transaction merchant input
        String transactionMerchant;
        do {
            System.out.println("Enter transaction merchant: ");
            String userInput = scanner.nextLine();
            transactionMerchant = userInput.trim();
        } while (transactionMerchant.isEmpty()); // it should not be empty or with whitespaces
        // correct path and merchant will be verified by Analyser
        Analyser analyser = new Analyser(csvTransactionFile, transactionMerchant, fromDate, toDate);
        analyser.analyse();
    }
}
