package com.haopeng.bitblockchaingit.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haopeng.bitblockchaingit.api.BitcoinApi;
import com.haopeng.bitblockchaingit.api.BitcoinJsonRpcClient;
import com.haopeng.bitblockchaingit.dao.BlockMapper;
import com.haopeng.bitblockchaingit.dao.TransactionMapper;
import com.haopeng.bitblockchaingit.dao.TransactiondetailMapper;
import com.haopeng.bitblockchaingit.dto.*;
import com.haopeng.bitblockchaingit.po.Block;
import com.haopeng.bitblockchaingit.po.Transaction;
import com.haopeng.bitblockchaingit.po.Transactiondetail;
import com.haopeng.bitblockchaingit.service.BlockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlockServiceImpl implements BlockService {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private TransactiondetailMapper transactiondetailMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Value("${blockchain.recentCount}")
    private Integer recentCount;

    //获取块的五条最新消息
    public List<BlockListDTO> getRecentBlocks() throws Throwable {

//        String bestBlockhash = bitcoinJsonRpcClient.getBestBlockhash();
//        String tempBlockhash =bestBlockhash;
//        List<BlockListDTO> blockListDTOS = new LinkedList<>();

        //循环五次依次获取五个最新的块
//        for (int i=0;i<5;i++) {
//            JSONObject block = bitcoinApi.getNoTxBlock(tempBlockhash);
//            BlockListDTO blockListDTO = new BlockListDTO();
//            blockListDTO.setHeight(block.getInteger("height"));
//            Long time = block.getLong("time");
//            Date date = new Date(time*1000);
//            blockListDTO.setTime(date);
//            blockListDTO.setTxSize(block.getJSONArray("tx").size());
//            blockListDTO.setSizeOnDisk(block.getLong("size"));
//            blockListDTOS.add(blockListDTO);
//            tempBlockhash =block.getString("previousblockhash");
//        }

//        JSONObject chainInfo = bitcoinApi.getChainInfo();
//        Integer height = chainInfo.getInteger("blocks");
//        height-=5;
//        String blockHashByHeight = bitcoinJsonRpcClient.getBlockHashByHeight(height);
//       JSONArray blockHeaders = bitcoinApi.getBlockHeaders(5, blockHashByHeight);
//        LinkedList<BlockListDTO> blocklistdtos = new LinkedList<>();
//        for (int i=4;i>-1;i--){
//            JSONObject jsonObject = blockHeaders.getJSONObject(i);
//            BlockListDTO blockListDTO = new BlockListDTO();
//            blockListDTO.setHeight(jsonObject.getInteger("height"));
//            Long time = jsonObject.getLong("time");
//            Date date = new Date(time * 1000);
//            blockListDTO.setTime(date);
//            blockListDTO.setTxSize(jsonObject.getInteger("nTx"));
//            blockListDTOS.add(blockListDTO);
//        }

        //根据数据库查询最新的五条数据
        List<Block> blocks=blockMapper.selectRecent();
        List<BlockListDTO> blockListDTOS=blocks.stream().map(block -> {
            BlockListDTO blockListDTO = new BlockListDTO();
            blockListDTO.setTxSize(block.getTxSize());
            blockListDTO.setHeight(block.getHeight());
            blockListDTO.setTime(block.getTime().getTime());
            blockListDTO.setSizeOnDisk(block.getSizeOnDisk());
            return blockListDTO;
        }).collect(Collectors.toList());

        return  blockListDTOS;
    }

    public List<BlockListDTO> getRecentBlocksByNameType(String name,String type){
        return  null;
    }

    //根据块的hash查询块的信息
    public BlockDetailDTO getBlockDetailByHash(String blockhash){
        //查询出块的信息
        BlockDetailDTO blockDetailDTO = blockMapper.selectByPrimaryKey(blockhash);
        //根据块的hash查询出每笔交易的信息
        List<TransactionInBlockDTO> transactionInBlockDTOS=transactionMapper.seleByBlockhash(blockDetailDTO.getBlockhash());
        //根据每笔交易的id查询出交易的信息
        for (TransactionInBlockDTO transactionInBlockDTO : transactionInBlockDTOS) {
            List<Transactiondetail> txDetailInTxInfos=transactiondetailMapper.seleTransactionTxid(transactionInBlockDTO.getTxid());
            transactionInBlockDTO.setTxDetailInTxInfos(txDetailInTxInfos);
        }
        //把查询出来的交易信息放入其中
        blockDetailDTO.setTransactions(transactionInBlockDTOS);
        return blockDetailDTO;
    }

    public  BlockDetailDTO getBlockDetailByHeight(Integer blockheight){
        //查询出块的信息
        BlockDetailDTO  blockDetailDTO=blockMapper.seleBlockDetailByHeight(blockheight);
        //根据块的hash查询出每笔交易的信息
        List<TransactionInBlockDTO> transactionInBlockDTOS=transactionMapper.seleByBlockhash(blockDetailDTO.getBlockhash());
        //根据每笔交易的id查询出交易的信息
        for (TransactionInBlockDTO transactionInBlockDTO : transactionInBlockDTOS) {
            List<Transactiondetail> txDetailInTxInfos=transactiondetailMapper.seleTransactionTxid(transactionInBlockDTO.getTxid());
            transactionInBlockDTO.setTxDetailInTxInfos(txDetailInTxInfos);
        }
        //把查询出来的交易信息放入其中
        blockDetailDTO.setTransactions(transactionInBlockDTOS);
        return blockDetailDTO;
    }


    //根据块的hash查询该块下的交易信息
    @Override
    public List<TransactionAmoutDTO> getByheight(String blockhash) {
        if (blockhash==null || blockhash.equals("")){
            //查询最大的块的高度（height）
            Block block=blockMapper.selemaxheigheight();
            //根据最大高度查询出块的信息
            Block byheight = blockMapper.getByheight(block.getHeight());
            //把块的hash值赋给blockhash
            blockhash=byheight.getBlockhash();
        }
        //根据块的块hash查询交易信息
        List<TransactionAmoutDTO> transactions = transactionMapper.seleblockhash(blockhash);
        for (TransactionAmoutDTO transaction : transactions) {
            transaction.setTimemiao(transaction.getTime().getTime());
            Double amountbtc=0.0;
           //根据这条交易id查询该条交易信息下所有交易发送者和接收者
            List<Transactiondetail> transactiondetails = transactiondetailMapper.seleTransactionTxid(transaction.getTxid());
           //把该条交易下所有发送者和接受者进行遍历
            for (Transactiondetail transactiondetail : transactiondetails) {
                    //把所有的发送者的比特币加起来
                    if (transactiondetail.getType()==2){
                        amountbtc+=transactiondetail.getAmount();
                    }
            }
            //保留6位小数
            amountbtc = (double) Math.round(amountbtc * 1000000) / 100000;
            transaction.setAmountbtc(amountbtc+"  BTC");
            //把比特币转换为美元
            amountbtc=amountbtc*5000;
            //把转换成美元的钱保留后两位小数
            amountbtc = (double) Math.round(amountbtc * 100) / 100;
            transaction.setAmountusd("$"+amountbtc);
        }
        return transactions;
    }
}
