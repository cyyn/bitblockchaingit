package com.haopeng.bitblockchaingit.api;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import org.springframework.stereotype.Service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Base64;
import java.util.HashMap;

@Service
public class BitcoinJsonRpcClient {

    private JsonRpcHttpClient jsonRpcHttpClient;

    public BitcoinJsonRpcClient() throws MalformedURLException {
        HashMap<String, String> headers = new HashMap<>();
        String authStrOrig = String.format("%s:%s","hp","123456");
        String authStr = Base64.getEncoder().encodeToString(authStrOrig.getBytes());
        String authStrResult = String.format("Basic %s",authStr);
        headers.put("Authorization",authStrResult);
        jsonRpcHttpClient = new JsonRpcHttpClient(new URL("http://localhost:18332"),headers);
    }

    public String getBlockHashByHeight(Integer blockHeight) throws Throwable {
        String blockhash = jsonRpcHttpClient.invoke("getblockhash", new Integer[]{blockHeight}, String.class);
        return blockhash;
    }

    public Double getBalance(String address) throws Throwable {
        JSONArray listunspent = jsonRpcHttpClient.invoke("listunspent", new Object[]{6, 9999999, new String[]{address}},
                JSONArray.class);
        JSONObject jsonObject = listunspent.getJSONObject(0);
        Double aDouble = jsonObject.getDouble("amount");
        return aDouble;
    }

    public JSONObject getRawTransaxtion(String txid) throws Throwable {
        JSONObject getrawtransaction = jsonRpcHttpClient.invoke("getrawtransaction", new Object[]{txid, true}, JSONObject.class);
        return getrawtransaction;
    }

    public String getBestBlockhash() throws Throwable {
        String getbestblockhash = jsonRpcHttpClient.invoke("getbestblockhash", new Object[]{}, String.class);
        return getbestblockhash;
    }

}
