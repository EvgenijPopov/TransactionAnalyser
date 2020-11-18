package com.popov.HomeWork.TheTransactionAnalyser;

import java.util.Date;

// central class - we create objects of this class to store all transaction data
public class TransactionRecord {

    // appropriate fields
    private final String transactionID;
    private final Date transactionDate;
    private final Double transactionAmount;
    private final String transactionMerchant;
    private final TransactionType transactionType;
    private final String relatedTransactionID;

    // full constructor
    public TransactionRecord(String transactionID, Date transactionDate, Double transactionAmount,
                             String transactionMerchant, TransactionType transactionType,
                             String relatedTransactionID) {
        this.transactionID = transactionID;
        this.transactionDate = transactionDate;
        this.transactionAmount = transactionAmount;
        this.transactionMerchant = transactionMerchant;
        this.transactionType = transactionType;
        this.relatedTransactionID = relatedTransactionID;
    }

    // at this stage we don't need setters only getters
    public String getTransactionID() {
        return transactionID;
    }


    public Date getTransactionDate() {
        return transactionDate;
    }


    public Double getTransactionAmount() {
        return transactionAmount;
    }


    public String getTransactionMerchant() {
        return transactionMerchant;
    }


    public TransactionType getTransactionType() {
        return transactionType;
    }


    public String getRelatedTransactionID() {
        return relatedTransactionID;
    }

    @Override
    public String toString() {
        return "TransactionRecord: {" +
                "transactionID='" + transactionID + '\'' +
                ", transactionDate=" + transactionDate +
                ", transactionAmount=" + transactionAmount +
                ", transactionMerchant='" + transactionMerchant + '\'' +
                ", transactionType=" + transactionType +
                ", relatedTransactionID='" + relatedTransactionID + '\'' +
                '}';
    }
}
