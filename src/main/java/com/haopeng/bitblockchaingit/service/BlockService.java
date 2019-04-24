package com.haopeng.bitblockchaingit.service;

import com.haopeng.bitblockchaingit.dto.BlockDetailDTO;
import com.haopeng.bitblockchaingit.dto.BlockListDTO;

import java.util.List;

public interface BlockService {
    List<BlockListDTO> getRecentBlocks() throws Throwable;

    List<BlockListDTO> getRecentBlocksByNameType(String name, String type);

    BlockDetailDTO getBlockDetailByHash(String blockhash);

    BlockDetailDTO getBlockDetailByHeight(Integer blockheight);
}
