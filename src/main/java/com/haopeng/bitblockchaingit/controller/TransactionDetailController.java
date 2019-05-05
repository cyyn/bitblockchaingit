package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dto.TransactionDetailDTO;
import com.haopeng.bitblockchaingit.dto.TransactionHash;
import com.haopeng.bitblockchaingit.service.TransactinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/transactiondetail")
@CrossOrigin
@EnableAutoConfiguration
public class TransactionDetailController {

   @Autowired
   private TransactinService transactinService;

    //根据地址查询这个地址的交易信息
    @GetMapping("/seletrandetaailaddress")
    public TransactionDetailDTO getAddressTransactions(@RequestParam String address){
        TransactionDetailDTO seletrandetaailaddress = transactinService.seletrandetaailaddress(address);
        return seletrandetaailaddress;
    }

    //根据交易hash查询交易信息
    @GetMapping("/seletransactiondetailhash")
    public List<TransactionHash> seletransactiondetailhash(@RequestParam String txhash){
        TransactionHash transactionHash=transactinService.seletransactiondetailhash(txhash);
        ArrayList<TransactionHash> transactionHashes = new ArrayList<>();
        transactionHashes.add(transactionHash);
        return transactionHashes;

    }
}
