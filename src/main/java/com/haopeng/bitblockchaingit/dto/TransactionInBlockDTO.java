package com.haopeng.bitblockchaingit.dto;

import com.haopeng.bitblockchaingit.po.Transactiondetail;

import java.util.Date;
import java.util.List;

public class TransactionInBlockDTO {

    private String txid;

    private String txhash;

    private Long size;

    private Date time;

    private List<Transactiondetail> txDetailInTxInfos;

    public String getTxid() {
        return txid;
    }

    public void setTxid(String txid) {
        this.txid = txid;
    }

    public String getTxhash() {
        return txhash;
    }

    public void setTxhash(String txhash) {
        this.txhash = txhash;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public List<Transactiondetail> getTxDetailInTxInfos() {
        return txDetailInTxInfos;
    }

    public void setTxDetailInTxInfos(List<Transactiondetail> txDetailInTxInfos) {
        this.txDetailInTxInfos = txDetailInTxInfos;
    }
}
