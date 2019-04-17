package com.haopeng.bitblockchaingit.service.impl;

import com.haopeng.bitblockchaingit.service.MiscService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class MiscServiceImpl implements MiscService {

    //异步函数 排错，错误会返回
    @Async
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {
    }

    @Async
    @Override
    public void importFromHash(String blockHash, Boolean isClean) {
    }
}
