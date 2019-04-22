package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dao.TransactiondetailMapper;
import com.haopeng.bitblockchaingit.dto.AddressInfo;
import com.haopeng.bitblockchaingit.dto.TransactionInBlockDTO;
import com.haopeng.bitblockchaingit.po.Transactiondetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private TransactiondetailMapper transactiondetailMapper;

    @GetMapping("/getAddressInfo")
    public AddressInfo getAddressInfo(@RequestParam String address){
        return null;
    }

    @GetMapping("/getAddressTransactions")
    public List<Transactiondetail> getAddressTransactions(@RequestParam String address, @RequestParam(required = false,defaultValue = "1") Integer pageNum){
        List<Transactiondetail> transactiondetails=transactiondetailMapper.selectByAddress(address);
        return transactiondetails;
    }
}
