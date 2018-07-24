package com.hoppers.navigationdrawer.model

data class Transactions(val accountNumber: String?, val transactions: List<Transaction>)

data class Transaction(val transactionId: String?, val date: String?, val cbTransactionTypeCode: String?, val description: String?, val amount: String?)