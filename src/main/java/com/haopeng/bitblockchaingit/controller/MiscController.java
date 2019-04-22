package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dto.ImportStateDTO;
import com.haopeng.bitblockchaingit.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/misc")
@EnableAutoConfiguration
public class MiscController {

    @Autowired
    private MiscService miscService;

    @GetMapping("search")
    public  Object search(@RequestParam String keyword){
          return null;
    }

    @GetMapping("/importFromHeight")
    public  void  importFromHeight(@RequestParam Integer blockHeight, Boolean isClean){
           miscService.importFromHeight(blockHeight,isClean);
    }

    //根据一个块信息向数据中添加块信息和交易信息
    @GetMapping("/importFromHash")
    public  void  importFromHash(@RequestParam String blockhash, Boolean isClean) throws Throwable {
           miscService.importFromHash(blockhash,isClean);
    }

    @GetMapping("/getImportState")
    public ImportStateDTO getImportState(){
        return null;
    }
}
