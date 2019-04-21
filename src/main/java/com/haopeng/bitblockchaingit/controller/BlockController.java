package com.haopeng.bitblockchaingit.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haopeng.bitblockchaingit.api.BitcoinApi;
import com.haopeng.bitblockchaingit.api.BitcoinJsonRpcClient;
import com.haopeng.bitblockchaingit.dao.BlockMapper;
import com.haopeng.bitblockchaingit.dto.BlockDetailDTO;
import com.haopeng.bitblockchaingit.dto.BlockListDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/block")
public class BlockController {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private BlockMapper blockMapper;

    @Value("${blockchain.recentCount}")
    private Integer recentCount;

    @GetMapping("/getRecentBlocks")
    public List<BlockListDTO> getRecentBlocks() throws Throwable {

        String bestBlockhash = bitcoinJsonRpcClient.getBestBlockhash();
        String tempBlockhash =bestBlockhash;
        List<BlockListDTO> blockListDTOS = new LinkedList<>();

        for (int i=0;i<5;i++) {
            JSONObject block = bitcoinApi.getNoTxBlock(tempBlockhash);
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setHeight(block.getInteger("height"));
            Long time = block.getLong("time");
            Date date = new Date(time*1000);
            blockListDTO.setTime(date);
            blockListDTO.setTxSize(block.getJSONArray("tx").size());
            blockListDTO.setSizeOnDisk(block.getLong("size"));
            blockListDTOS.add(blockListDTO);
            tempBlockhash =block.getString("previousblockhash");
        }

        JSONObject chainInfo = bitcoinApi.getChainInfo();
        Integer height = chainInfo.getInteger("blocks");
        height-=5;
        String blockHashByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(height);
        JSONArray blockHeaders = bitcoinApi.getBlockHeaders(5, blockHashByHeight);
        LinkedList<BlockListDTO> blocklistdtos = new LinkedList<>();
        for (int i=4;i>-1;i--){
            JSONObject jsonObject = blockHeaders.getJSONObject(i);
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setHeight(jsonObject.getInteger("height"));
            Long time = jsonObject.getLong("time");
            Date date = new Date(time * 1000);
            blockListDTO.setTime(date);
            blockListDTO.setTxSize(jsonObject.getInteger("nTx"));
            blockListDTOS.add(blockListDTO);
        }



        return  blockListDTOS;
    }

    @GetMapping("/getRecentBlocksByNameType")
    public List<BlockListDTO> getRecentBlocksByNameType(@RequestParam String name,@RequestParam String type){
        return  null;
    }

    @GetMapping("/getBlockDetailByHash")
    public BlockDetailDTO getBlockDetailByHash(@RequestParam String blockhash){
        return  null;
    }

    @GetMapping("/getBlockDetailByHeight")
    public  BlockDetailDTO getBlockDetailByHeight(@RequestParam Integer blockheight){
        return null;
    }
}
