package com.haopeng.bitblockchaingit.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="bitcoin",url="http://localhost:18332/")
public interface BitcoinApi {

    //查询区块链信息
    @GetMapping("/rest/chaininfo.json")
    JSONObject getChainInfo();

    //通过这个交易的hash查询交易信息
    @GetMapping("/rest/tx/{txhash}.json")
    JSONObject getTransaction(@PathVariable("txhash") String txhash);

    //通过区块的hash查询区块信息
    @GetMapping("/rest/block/{blockhash}.json")
    JSONObject getBlock(@PathVariable("blockhash") String blockhash);

    //返回值中有上个块的hash值
    @GetMapping("/rest/block/notxdetails/{blockhash}.json")
    JSONObject getNoTxBlock(@PathVariable("blockhash") String blockhash);

    //查询这个块中10条交易记录
    @GetMapping("/rest/headers/{count}/{blockhash}.json")
    JSONArray getBlockHeaders(@PathVariable("count") Integer count, @PathVariable("blockhash") String blockhash);

    //查询有关TX mempool的各种信息。仅支持JSON作为输出格式。
    @GetMapping("/rest/mempool/info.json")
    JSONObject getMempoolInfo();

    //查询TX mempool中的事务。仅支持JSON作为输出格式
    @GetMapping("/rest/mempool/contents.json")
    JSONObject getMempoolContents();

}
