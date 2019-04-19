package com.haopeng.bitblockchaingit.service;

import com.alibaba.fastjson.JSONObject;

import java.util.Date;

public interface MiscService {

    void importFromHeight(Integer blockHeight, Boolean isClean);

    void importFromHash(String blockHash, Boolean isClean);

    void importTx(JSONObject tx, String blockhash, Date time);
}
