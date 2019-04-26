package com.haopeng.bitblockchaingit.dto;

import com.haopeng.bitblockchaingit.po.Transactiondetail;

import java.util.List;

public class TransactionDetailDTO extends Transactiondetail {

    List<TransactionInBlockDTO> transactiondetails;



    public List<TransactionInBlockDTO> getTransactiondetails() {
        return transactiondetails;
    }

    public void setTransactiondetails(List<TransactionInBlockDTO> transactiondetails) {
        this.transactiondetails = transactiondetails;
    }
}
