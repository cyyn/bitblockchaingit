package com.haopeng.bitblockchaingit.dto;

import com.haopeng.bitblockchaingit.po.Block;

import java.util.Date;
import java.util.List;

public class BlockDetailDTO extends Block {

    private List<TransactionInBlockDTO> transactions;

    public List<TransactionInBlockDTO> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionInBlockDTO> transactions) {
        this.transactions = transactions;
    }
}
