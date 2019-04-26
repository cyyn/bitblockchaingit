package com.haopeng.bitblockchaingit.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BlockScheduler {

    private Logger logger= LoggerFactory.getLogger(this.getClass());

    //定时器可以10分钟执行一次，现在是3秒执行
    @Scheduled(fixedDelay = 3000)
    public void importBlockTransaction(){

        logger.info("start import block transactions");

    }
}
