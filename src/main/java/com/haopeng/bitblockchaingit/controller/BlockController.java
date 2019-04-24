package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dto.BlockDetailDTO;
import com.haopeng.bitblockchaingit.dto.BlockListDTO;
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

    @GetMapping("/getRecentBlocksByNameType")
    public List<BlockListDTO> getRecentBlocksByNameType(@RequestParam String name,@RequestParam String type){
        List<BlockListDTO> blocksByNameType=blockService.getRecentBlocksByNameType(name,type);
        return  blocksByNameType;
    }

    @GetMapping("/getBlockDetailByHash")
    public BlockDetailDTO getBlockDetailByHash(@RequestParam String blockhash){
        BlockDetailDTO blockDetailDTO=blockService.getBlockDetailByHash(blockhash);
        return  blockDetailDTO;
    }

    @GetMapping("/getBlockDetailByHeight")
    public  BlockDetailDTO getBlockDetailByHeight(@RequestParam Integer blockheight){
        BlockDetailDTO blockDetailDTO=blockService.getBlockDetailByHeight(blockheight);
        return blockDetailDTO;
    }
}
