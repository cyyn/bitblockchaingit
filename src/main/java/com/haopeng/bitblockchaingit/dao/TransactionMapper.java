package com.haopeng.bitblockchaingit.dao;

import com.haopeng.bitblockchaingit.dto.TransactionAmoutDTO;
import com.haopeng.bitblockchaingit.dto.TransactionHash;
import com.haopeng.bitblockchaingit.dto.TransactionInBlockDTO;
import com.haopeng.bitblockchaingit.po.Transaction;

import java.util.List;

public interface TransactionMapper {
    int deleteByPrimaryKey(String txid);

    int insert(Transaction record);

    int insertSelective(Transaction record);

    TransactionInBlockDTO selectByPrimaryKey(String txid);

    int updateByPrimaryKeySelective(Transaction record);

    int updateByPrimaryKey(Transaction record);

    void truncate();

    List<TransactionInBlockDTO> seleByBlockhash(String blockhash);

    List<TransactionAmoutDTO> seleblockhash(String blockhash);

    TransactionHash seletransactiondetailhash(String txhash);
}