package com.haopeng.bitblockchaingit.service.impl;


import com.haopeng.bitblockchaingit.dao.TransactionMapper;
import com.haopeng.bitblockchaingit.dao.TransactiondetailMapper;
import com.haopeng.bitblockchaingit.dto.TransactionDetailDTO;
import com.haopeng.bitblockchaingit.dto.TransactionHash;
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
        transactionDetailDTO.setCount(transactionInBlockDTOS.size());
        transactionDetailDTO.setTransactiondetails(transactionInBlockDTOS);
        return transactionDetailDTO;
    }

    @Override
    public TransactionHash seletransactiondetailhash(String txhash) {
        //通过交易hash查询
        TransactionHash transactionHash=transactionMapper.seletransactiondetailhash(txhash);
        //通过交易id把交易地址信息拿到
        List<Transactiondetail> transactiondetails = transactiondetailMapper.seleTransactionTxid(transactionHash.getTxid());
        double input=0;
        double output=0;
        //把数据进行遍历
        for (Transactiondetail transactiondetail : transactiondetails) {
            if (transactiondetail.getType()==1){
                input+=transactiondetail.getAmount();
            }else {
                output+=transactiondetail.getAmount();
            }
        }
        transactionHash.setTotalInput(input);
        transactionHash.setTotalOutput(output);
        if (input>output){
          transactionHash.setFees(input-output);
        }else {
          transactionHash.setFees(0.0);
        }
        transactionHash.setTransactiondetails(transactiondetails);
        return transactionHash;
    }


}
