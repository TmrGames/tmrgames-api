package com.tmr.tomoapi.service;

import com.alibaba.fastjson2.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tmr.tomoapi.entity.SysBlockchain;
import com.tmr.tomoapi.entity.SysPrice;
import com.tmr.tomoapi.exception.ServiceException;
import com.tmr.tomoapi.mapper.MarketMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class MarketService implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(MarketService.class);

    @Autowired
    private MarketMapper marketMapper;

    @Override
    public void run(ApplicationArguments applicationArguments) {

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                CloseableHttpClient httpclient = HttpClients.createDefault();
                try {
                    HttpGet httpget = new HttpGet("https://api.binance.com/api/v3/ticker/price?symbol=VICUSDT");
                    CloseableHttpResponse response = httpclient.execute(httpget);
                    HttpEntity entity = response.getEntity();
                    if (entity == null) {
                        throw new ServiceException("get market price return null");
                    }
                    JSONObject ret = JSONObject.parse(EntityUtils.toString(entity));
                    String symbol = ret.getString("symbol");
                    if (symbol == null) {
                        throw new ServiceException("symbol is null");
                    }
                    String price = ret.getString("price");
                    if (price == null) {
                        throw new ServiceException("price is null");
                    }
                    logger.info("Market symbol: {} price: {}", symbol, price);

                    LambdaQueryWrapper<SysPrice> queryWrapper = new LambdaQueryWrapper<SysPrice>();
                    queryWrapper.eq(SysPrice::getSymbol, "VICUSDT");
                    SysPrice sysPrice = new SysPrice();
                    sysPrice.setPrice(price);
                    if(marketMapper.selectCount(queryWrapper) > 0){
                        marketMapper.update(sysPrice, queryWrapper);
                    }
                    else{
                        sysPrice.setSymbol("VICUSDT");
                        marketMapper.insert(sysPrice);
                    }
                }
                catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        httpclient.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, 0, 60000);
    }
}
