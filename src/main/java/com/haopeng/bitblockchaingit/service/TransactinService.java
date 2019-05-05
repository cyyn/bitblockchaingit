package com.haopeng.bitblockchaingit.service;


import com.haopeng.bitblockchaingit.dto.TransactionDetailDTO;
import com.haopeng.bitblockchaingit.dto.TransactionHash;

public interface TransactinService {

    TransactionDetailDTO seletrandetaailaddress(String address);

    TransactionHash seletransactiondetailhash(String txhash);
}
