package com.haopeng.bitblockchaingit.controller;

import com.haopeng.bitblockchaingit.dto.BlockListDTO;
import com.haopeng.bitblockchaingit.service.BlockService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BlockControllerTest {

    //对block的功能进行单元测试(第一种方式)

    //@Autowired
    //private BlockService blockService;

    @Autowired
    private BlockController blockController;

    @Test
    public void getRecentBlock() throws Throwable {
        //List<BlockListDTO> recentBlocks = blockService.getRecentBlocks();
        List<BlockListDTO> recentBlocks1 = blockController.getRecentBlocks();
        assertEquals(5,recentBlocks1.size());
    }
}
