package com.haopeng.bitblockchaingit.dao;

import com.haopeng.bitblockchaingit.dto.BlockDetailDTO;
import com.haopeng.bitblockchaingit.po.Block;

import java.util.List;

public interface BlockMapper {
    int deleteByPrimaryKey(String blockhash);

    int insert(Block record);

    int insertSelective(Block record);

    BlockDetailDTO selectByPrimaryKey(String blockhash);

    int updateByPrimaryKeySelective(Block record);

    int updateByPrimaryKey(Block record);

    void truncate();

    List<Block> selectRecent();

    BlockDetailDTO seleBlockDetailByHeight(Integer blockheight);

    Block getByheight(Integer height);

    Block selemaxheigheight();
}