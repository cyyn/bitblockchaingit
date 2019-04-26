package com.haopeng.bitblockchaingit.dto;

import com.haopeng.bitblockchaingit.po.Transactiondetail;

import java.util.List;

public class TransactionDetailDTO extends Transactiondetail {

    private Integer count;

    private   List<TransactionInBlockDTO> transactiondetails;


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<TransactionInBlockDTO> getTransactiondetails() {
        return transactiondetails;
    }

    public void setTransactiondetails(List<TransactionInBlockDTO> transactiondetails) {
        this.transactiondetails = transactiondetails;
    }
}
