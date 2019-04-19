package com.haopeng.bitblockchaingit.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.haopeng.bitblockchaingit.api.BitcoinApi;
import com.haopeng.bitblockchaingit.api.BitcoinJsonRpcClient;
import com.haopeng.bitblockchaingit.dao.BlockMapper;
import com.haopeng.bitblockchaingit.dao.TransactionMapper;
import com.haopeng.bitblockchaingit.dao.TransactiondetailMapper;
import com.haopeng.bitblockchaingit.enumeration.TransactionDetailType;
import com.haopeng.bitblockchaingit.po.Block;
import com.haopeng.bitblockchaingit.po.Transaction;
import com.haopeng.bitblockchaingit.po.Transactiondetail;
import com.haopeng.bitblockchaingit.service.MiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MiscServiceImpl implements MiscService {

    @Autowired
    private BitcoinApi bitcoinApi;

    @Autowired
    private BlockMapper blockMapper;

    @Autowired
    private TransactionMapper transactionMapper;

    @Autowired
    private BitcoinJsonRpcClient bitcoinJsonRpcClient;

    @Autowired
    private TransactiondetailMapper transactiondetailMapper;

    //异步函数
    @Async
    @Override
    public void importFromHeight(Integer blockHeight, Boolean isClean) {
    }

    @Async
    @Override
    public void importFromHash(String blockHash, Boolean isClean) {
        if(isClean){
            blockMapper.truncate();
            transactionMapper.truncate();
            transactiondetailMapper.truncate();
        }
        String temphash = blockHash;
        while (temphash != null && !temphash.isEmpty()){
            JSONObject blockOrigin = bitcoinApi.getBlock(temphash);
            Block block = new Block();
            block.setBlockhash(blockOrigin.getString("hash"));
            block.setBlockchainId(2);
            block.setHeight(blockOrigin.getInteger("height"));
            Long time = blockOrigin.getLong("time");
            Date date = new Date(time * 1000);
            block.setTime(date);
            JSONArray tx = blockOrigin.getJSONArray("tx");
            for(int i=0;i<tx.size();i++){
                importTx(tx.getJSONObject(i),temphash,date);
            }
            block.setTxSize(tx.size());
            block.setSizeOnDisk(blockOrigin.getLong("size"));
            block.setDifficulty(blockOrigin.getDouble("difficulty"));
            block.setPrevBlockhash(blockOrigin.getString("previousblockhash"));
            block.setNextBlockhash(blockOrigin.getString("nextblockhash"));
            block.setMerkleRoot(blockOrigin.getString("merkleroot"));
            blockMapper.insert(block);
            temphash = blockOrigin.getString("nextblockhash");
        }
    }


    public void importTx(JSONObject tx,String blockhash,Date time){
        Transaction transaction = new Transaction();
        String txid=tx.getString("txid");
        transaction.setTxid(txid);
        transaction.setTxhash(tx.getString("hash"));
        transaction.setBlockhash(blockhash);
        transaction.setSize(tx.getLong("size"));
        transaction.setWeight(tx.getInteger("weight"));
        transaction.setTime(time);
        transactionMapper.insert(transaction);

        //获取一条交易记录中的接收方的信息
        JSONArray vouts=tx.getJSONArray("vout");
        for (int i = 0; i < vouts.size(); i++) {
            importVoutDetail(vouts.getJSONObject(i),txid);
        }
//        //获取一条交易记录中的发送方的信息
//        JSONArray vins=tx.getJSONArray("vin");
//        for (int i = 0; i < vins.size(); i++) {
//            importVoutDetail(vins.getJSONObject(i),txid);
//        }
    }


    public void importVoutDetail(JSONObject vout,String txid){
        Transactiondetail transactiondetail = new Transactiondetail();
        transactiondetail.setTxid(txid);
        //获取接收方钱包的地址
        JSONObject scriptPubKey = vout.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses!=null&&!addresses.isEmpty()){
            String string = addresses.getString(0);
            transactiondetail.setAddress(string);
        }
        Double amount = vout.getDouble("value");
        transactiondetail.setAmount(amount);
        transactiondetail.setType((byte) TransactionDetailType.Receive.ordinal());

        transactiondetailMapper.insert(transactiondetail);

    }


    public void importVinDetail(JSONObject vin, String txidOrigin) throws Throwable {

        //获取交易记录中的交易ID
        String txid = vin.getString("txid");
        Integer vout = vin.getInteger("vout");

        //通过这个交易交易id查询出这个交易id的信息
        JSONObject rawTransaxtion = bitcoinJsonRpcClient.getRawTransaxtion(txid);
        JSONArray vout1 = rawTransaxtion.getJSONArray("vout");
        JSONObject jsonObject = vout1.getJSONObject(vout);

        Transactiondetail transactiondetail = new Transactiondetail();
        transactiondetail.setTxid(txidOrigin);
        transactiondetail.setType((byte) TransactionDetailType.Send.ordinal());
        Double amount = jsonObject.getDouble("value");
        transactiondetail.setAmount(amount);
        //获取存入方的交易地址
        JSONObject scriptPubKey = jsonObject.getJSONObject("scriptPubKey");
        JSONArray addresses = scriptPubKey.getJSONArray("addresses");
        if (addresses != null){
            String address = addresses.getString(0);
            transactiondetail.setAddress(address);
        }
        transactiondetailMapper.insert(transactiondetail);

    }
}
