package com.haopeng.bitblockchaingit.service.impl;


import com.haopeng.bitblockchaingit.dao.TransactionMapper;
import com.haopeng.bitblockchaingit.dao.TransactiondetailMapper;
import com.haopeng.bitblockchaingit.dto.TransactionDetailDTO;
import com.haopeng.bitblockchaingit.dto.TransactionInBlockDTO;
import com.haopeng.bitblockchaingit.po.Transactiondetail;
import com.haopeng.bitblockchaingit.service.TransactinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactinServiceImpl implements TransactinService {

    @Autowired
    private TransactiondetailMapper transactiondetailMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Override
    public TransactionDetailDTO seletrandetaailaddress(String address) {
        TransactionDetailDTO transactionDetailDTO = new TransactionDetailDTO();
        transactionDetailDTO.setAddress(address);
        //根据地址查询出地址的信息
        List<Transactiondetail> transactiondetails = transactiondetailMapper.selectByAddress(address);
        //创建一个TransactionInBlockDTO的集合
        ArrayList<TransactionInBlockDTO> transactionInBlockDTOS = new ArrayList<>();
        for (Transactiondetail transactiondetail : transactiondetails) {
            //根据地址的交易txid查询出交易信息
            TransactionInBlockDTO transactionInBlockDTO = transactionMapper.selectByPrimaryKey(transactiondetail.getTxid());
            List<Transactiondetail> transactiondetails1 = transactiondetailMapper.seleTransactionTxid(transactionInBlockDTO.getTxid());
            transactionInBlockDTO.setTxDetailInTxInfos(transactiondetails1);
            transactionInBlockDTOS.add(transactionInBlockDTO);
        }
        transactionDetailDTO.setTransactiondetails(transactionInBlockDTOS);
        return transactionDetailDTO;
    }
}
