package com.popov.HomeWork.TheTransactionAnalyser;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

// class Analyser has all calculation logic
public class Analyser {

    // fields for creation correct Analyser
    private final File file;
    private final String transactionMerchant;
    private final Date fromDate;
    private final Date toDate;

    // full constructor
    public Analyser(File file, String transactionMerchant, Date fromDate, Date toDate) {
        this.file = file;
        this.transactionMerchant = transactionMerchant;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    // central method for gathering appropriate transactions
    public void analyse() {
            // get a set of objects from csv file
            Set<TransactionRecord> recordSet = convertToObjects(convertToSet());
            // initialize variable for average value of appropriate transactions
            Double averageTransactionValue = 0.0;
            // initialize variable for total number of appropriate transactions
            int numberOfTransactions = 0;
            // for each record at this Set
            for (TransactionRecord record : recordSet) {
                // if they fit in current date range
                if (record.getTransactionDate().after(fromDate) && record.getTransactionDate().before(toDate)) {
                    // if name of searched merchant is the same as in the csv file AND doesn't have REVERSAL
                    if (record.getTransactionMerchant().equals(this.transactionMerchant) && !hasAReversalTransaction(record, recordSet)) {
                        // we add this transaction in calculation statistics
                        averageTransactionValue += record.getTransactionAmount();
                        numberOfTransactions++;
                        // System.out.println(record);
                    }
                }
            }
            // print result
            System.out.println("Number of Transactions = " + numberOfTransactions);
            System.out.printf("%s %.2f","Average Transaction Value =",  averageTransactionValue / numberOfTransactions);
        }

    // this method check REVERSAL transactions
    private boolean hasAReversalTransaction(TransactionRecord record, Set<TransactionRecord> recordSet) {
        // if it's type is REVERSAL - return true
        if (record.getTransactionType().getType().equals("REVERSAL")) {
            return true;
        }
        for (TransactionRecord transactionRecord : recordSet) {
            // we don't need to compare our record with non-REVERSAL transactions
            if (transactionRecord.getRelatedTransactionID() != null) {
                // but if it REVERSAL search for match and return true
                if (record.getTransactionID().equals(transactionRecord.getRelatedTransactionID())) {
                    return true;
                }
            }
        }
        // in other cases we don't have REVERSAL
        return false;
    }

    // converting to Set
    private Set<String> convertToSet() {
        // create Set
        Set<String> transactionSet = new LinkedHashSet<>();
        // try to read with BufferedReader and FileReader
        try (FileReader fileReader = new FileReader(this.file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            // initialize loop "slider"
            int character = bufferedReader.read();
            while (character != -1) { // to the document end
                // by read() method we lose one character -> we need to append it
                transactionSet.add((char) character + bufferedReader.readLine());
                character = bufferedReader.read();
            }
        } catch (FileNotFoundException fileNotFoundException) {
            System.out.println("File not Found!");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        /* for console confirmation
        for (String str : transactionSet) {
            System.out.println(str);
        }
        */
        return transactionSet;
    }

    // converting to objects
    private Set<TransactionRecord> convertToObjects(Set<String> transactionSet) {
        // initialize future fields
        String transactionID = "";
        String transactionMerchant = "";
        String relatedTransactionID = "";
        Double transactionAmount = null;
        TransactionType transactionType = null;
        Date transactionDate = null;
        // if input file is well formed and is not missing data, than...
        Set<TransactionRecord> transactionRecords = new LinkedHashSet<>();
        for (String str : transactionSet) {
            // split our each String by comma or comma + whitespace
            String[] data = str.split(", |,");
            // in split array each String position is correspond to correct class field
            for (int i = 0; i < data.length; i++) {
                switch (i) {
                    case 0:
                        // for ID
                        transactionID = data[i];
                        break;
                    case 1:
                        // for Date
                        try {
                            transactionDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(data[i]);
                        } catch (ParseException parseException) {
                            System.out.println("Can't transform data into date!");
                        }
                        break;
                    case 2:
                        // for Amount
                        try {
                            transactionAmount = Double.parseDouble(data[i]);
                        } catch (NullPointerException nullPointerException) {
                            System.out.println("Transaction amount is absent");
                        }
                        break;
                    case 3:
                        // for Merchant
                        transactionMerchant = data[i];
                        break;
                    case 4:
                        // for Type
                        transactionType = TransactionType.valueOf(data[i]);
                        break;
                    case 5:
                        // for related ID
                        relatedTransactionID = data[i];
                        break;
                }
            }
            // add new object with switch-formed fields
            transactionRecords.add(new TransactionRecord(transactionID, transactionDate,
                    transactionAmount, transactionMerchant,
                    transactionType, relatedTransactionID));
        }
        /* for console confirmation
        for (TransactionRecord record : transactionRecords) {
            System.out.println(record);
        }
         */
        return transactionRecords;
    }
}
