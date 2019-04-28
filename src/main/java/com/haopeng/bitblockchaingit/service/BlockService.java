package com.haopeng.bitblockchaingit.service;

import com.haopeng.bitblockchaingit.dto.BlockDetailDTO;
import com.haopeng.bitblockchaingit.dto.BlockListDTO;
import com.haopeng.bitblockchaingit.po.Transaction;

import java.util.List;

public interface BlockService {
    List<BlockListDTO> getRecentBlocks() throws Throwable;

    List<BlockListDTO> getRecentBlocksByNameType(String name, String type);

    BlockDetailDTO getBlockDetailByHash(String blockhash);

    BlockDetailDTO getBlockDetailByHeight(Integer blockheight);

    List<Transaction> getByheight(String height);
}
