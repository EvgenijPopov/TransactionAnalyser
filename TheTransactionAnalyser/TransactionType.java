package com.popov.HomeWork.TheTransactionAnalyser;

// for simplicity we use transaction type as Enum
public enum TransactionType {
    PAYMENT("PAYMENT"), REVERSAL("REVERSAL");

    private String type;

    TransactionType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
