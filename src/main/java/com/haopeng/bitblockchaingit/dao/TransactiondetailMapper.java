package com.haopeng.bitblockchaingit.dao;

import com.haopeng.bitblockchaingit.po.Transactiondetail;
import com.haopeng.bitblockchaingit.po.TransactiondetailKey;

import java.util.List;

public interface TransactiondetailMapper {
    int deleteByPrimaryKey(TransactiondetailKey key);

    int insert(Transactiondetail record);

    int insertSelective(Transactiondetail record);

    Transactiondetail selectByPrimaryKey(TransactiondetailKey key);

    int updateByPrimaryKeySelective(Transactiondetail record);

    int updateByPrimaryKey(Transactiondetail record);

    void truncate();

    List<Transactiondetail> selectByAddress(String address);
}