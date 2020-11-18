Code consist of 5 classes:
1) MainApp class - it contains MainThread class only for launch;
2) MainThread class - it contains user input logic and start the Analyser;
3) Analyser class - central class for all calculation logic;
4) TransactionRecord class - objects of this class store transaction data;
5) TransactionType enum - enumeration for simplicity (it's a field of TransactionRecord).

This analyser correctly work only with appropriate structure of csv file:
- each transaction with its own data is a one row(line);
- all transaction data separate by comma or comma & whitespace;
- each transaction has maximum 6 fields of data;
- document is well-formed: there is no missing data.