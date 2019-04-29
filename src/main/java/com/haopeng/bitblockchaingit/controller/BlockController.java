package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dto.BlockDetailDTO;
import com.haopeng.bitblockchaingit.dto.BlockListDTO;
import com.haopeng.bitblockchaingit.dto.TransactionAmoutDTO;
import com.haopeng.bitblockchaingit.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;;

@RestController
@RequestMapping("/block")
@CrossOrigin
public class BlockController {

    @Autowired
    private BlockService blockService;

    //获取块的五条最新消息
    @GetMapping("/getRecentBlocks")
    public List<BlockListDTO> getRecentBlocks() throws Throwable {
        List<BlockListDTO> blockListDTOS=blockService.getRecentBlocks();
        return  blockListDTOS;
    }

    //根据块的hash查询块的信息
    @GetMapping("/getBlockDetailByHash")
    public BlockDetailDTO getBlockDetailByHash(@RequestParam String blockhash){
        BlockDetailDTO blockDetailDTO=blockService.getBlockDetailByHash(blockhash);
        return  blockDetailDTO;
    }

    //根据块的高度获得块的信息
    @GetMapping("/getBlockDetailByHeight")
    public  BlockDetailDTO getBlockDetailByHeight(@RequestParam Integer blockheight){
        BlockDetailDTO blockDetailDTO=blockService.getBlockDetailByHeight(blockheight);
        return blockDetailDTO;
    }

    //根据块的hash查询该块下的详细交易信息
    @GetMapping("/getblockhash")
    public List<TransactionAmoutDTO> getblockhash(@RequestParam(required = false,defaultValue = "") String blockhash){
        List<TransactionAmoutDTO> byheight = blockService.getByheight(blockhash);
        return  byheight;
    }

}
