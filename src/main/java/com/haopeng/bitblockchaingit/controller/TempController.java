package com.haopeng.bitblockchaingit.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haopeng.bitblockchaingit.api.BitcoinApi;
import com.haopeng.bitblockchaingit.api.BitcoinJsonRpcClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempController {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @GetMapping("/test")
    public void test() throws Throwable {
        //查询区块链信息
        JSONObject chainInfo = bitcoinApi.getChainInfo();

        //通过这个交易的hash查询交易信息
        //String txhash = "25c9afda23222e8c327fa0bfb6458ea77d538725eb1d34b497a7eec31cc51b79";
        //JSONObject transaction = bitcoinApi.getTransaction(txhash);

        //通过区块的hash查询区块信息
        // String blockhash = "00000000000000ef7a27f22cfa593ea9227e309fe65ed3fe29fbbff5d003961e";
        //JSONObject block = bitcoinApi.getBlock(blockhash);
        ////返回值中有上个块的hash值
        //JSONObject noTxBlock = bitcoinApi.getNoTxBlock(blockhash);

        //查询这个块中10条交易记录
        //String blockhash2 = "00000000000000ef7a27f22cfa593ea9227e309fe65ed3fe29fbbff5d003961e";
        //JSONArray blockHeaders = bitcoinApi.getBlockHeaders(10, blockhash2);

         //查询有关TX mempool的各种信息。仅支持JSON作为输出格式。
        //JSONObject mempoolInfo = bitcoinApi.getMempoolInfo();

        //查询TX mempool中的事务。仅支持JSON作为输出格式
        // SONObject mempoolContents = bitcoinApi.getMempoolContents();

        // String blockHashByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(1489445);

        //String adderss="2MxVBAE3b4EacXnukRM8npCgySW5HrEQWAo";
        //Double balance = bitcoinJsonRpcClient.getBalance(adderss);

        String txid="94b2fc97b1a35fb4d02688ef16f8bae43264cdf405476288836e49392f30c677";
        JSONObject rawTransaxtion = bitcoinJsonRpcClient.getRawTransaxtion(txid);


    }

}
