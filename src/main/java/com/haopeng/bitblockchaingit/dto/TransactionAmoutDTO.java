package com.haopeng.bitblockchaingit.dto;

import java.util.Date;

public class TransactionAmoutDTO {

    private String txid;

    private String txhash;

    private String blockhash;

    private Date time;

    private Double amountbtc;

    private Double amountusd;

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

    public Double getAmountbtc() {
        return amountbtc;
    }

    public void setAmountbtc(Double amountbtc) {
        this.amountbtc = amountbtc;
    }

    public Double getAmountusd() {
        return amountusd;
    }

    public void setAmountusd(Double amountusd) {
        this.amountusd = amountusd;
    }
}
