package com.haopeng.bitblockchaingit.dto;

import java.util.Date;

public class TransactionAmoutDTO {

    private String txid;

    private String txhash;

    private String blockhash;

    private Date time;

    private long timemiao;

    private String amountbtc;

    private String amountusd;

    public long getTimemiao() {
        return timemiao;
    }

    public void setTimemiao(long timemiao) {
        this.timemiao = timemiao;
    }

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

    public String getBlockhash() {
        return blockhash;
    }

    public void setBlockhash(String blockhash) {
        this.blockhash = blockhash;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getAmountbtc() {
        return amountbtc;
    }

    public void setAmountbtc(String amountbtc) {
        this.amountbtc = amountbtc;
    }

    public String getAmountusd() {
        return amountusd;
    }

    public void setAmountusd(String amountusd) {
        this.amountusd = amountusd;
    }
}
