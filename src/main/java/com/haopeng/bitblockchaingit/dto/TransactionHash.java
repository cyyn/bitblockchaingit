package com.haopeng.bitblockchaingit.dto;

import com.haopeng.bitblockchaingit.po.Transaction;
import com.haopeng.bitblockchaingit.po.Transactiondetail;

import java.util.List;

public class TransactionHash extends Transaction {


    private List<Transactiondetail> transactiondetails;

    public List<Transactiondetail> getTransactiondetails() {
        return transactiondetails;
    }

    public void setTransactiondetails(List<Transactiondetail> transactiondetails) {
        this.transactiondetails = transactiondetails;
    }
}
