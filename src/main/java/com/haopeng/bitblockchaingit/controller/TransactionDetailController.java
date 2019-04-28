package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dto.TransactionDetailDTO;
import com.haopeng.bitblockchaingit.service.TransactinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactiondetail")
@CrossOrigin
@EnableAutoConfiguration
public class TransactionDetailController {

   @Autowired
   private TransactinService transactinService;

    @GetMapping("/seletrandetaailaddress")
    public TransactionDetailDTO getAddressTransactions(@RequestParam String address){
        TransactionDetailDTO seletrandetaailaddress = transactinService.seletrandetaailaddress(address);
        return seletrandetaailaddress;
    }

}
