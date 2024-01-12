package com.tmr.tomoapi.service;

import com.tmr.tomoapi.TomoApiApplication;
import com.tmr.tomoapi.domain.Input.UserDepositInput;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Component;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Numeric;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

@Component
public class BlockchainService implements ApplicationRunner {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainService.class);

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public void run(ApplicationArguments applicationArguments){

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                try {
                    Web3j web3 = Web3j.build(new HttpService("https://rpc.tomochain.com"));
                    try {
                        BigInteger blockNumber = web3.ethBlockNumber().send().getBlockNumber();
                        logger.info("Query the latest block {}", blockNumber);
                        int currentBlockNo = Integer.parseInt(userAccountService.getLastBlockNo());
                        int endBlockNo = currentBlockNo + 10;
                        if(endBlockNo < blockNumber.intValue()){
                            logger.info("Replay block from {} to {}", currentBlockNo, endBlockNo);
                            web3.replayPastTransactionsFlowable(
                                    DefaultBlockParameter.valueOf(Numeric.toBigInt(Integer.toHexString(currentBlockNo))),
                                    DefaultBlockParameter.valueOf(Numeric.toBigInt(Integer.toHexString(endBlockNo)))).subscribe(tx -> {
                                if(tx.getValue().compareTo(new BigInteger("0")) > 0){
                                    logger.info("From: {} To: {} Tomo: {}",tx.getFrom(),tx.getTo(),tx.getValue());
                                    UserDepositInput userDepositInput = new UserDepositInput();
                                    userDepositInput.setFromAddress(tx.getFrom());
                                    userDepositInput.setToAddress(tx.getTo());
                                    userDepositInput.setBlockNo(tx.getBlockNumber().toString());
                                    userDepositInput.setTxHash(tx.getHash());
                                    userDepositInput.setCrypto("VIC");
                                    userDepositInput.setAmount(tx.getValue().toString());
                                    userAccountService.depositTomo(userDepositInput);
                                }

                                if(!Objects.isNull(tx.getTo()) && tx.getTo().equals("0x0fd0288aaae91eaf935e2ec14b23486f86516c8c")){
                                    String methodId = tx.getInput().substring(0, 10);
                                    if(methodId.equals("0xa9059cbb")){
                                        String toAddress = "0x" + tx.getInput().substring(34, 74);
                                        String amount = new BigInteger(tx.getInput().substring(74, 138), 16).toString();
                                        logger.info("C98 From: {} To: {} Amount: {}",tx.getFrom(), toAddress, amount);
                                        UserDepositInput userDepositInput = new UserDepositInput();
                                        userDepositInput.setFromAddress(tx.getFrom());
                                        userDepositInput.setToAddress(toAddress);
                                        userDepositInput.setBlockNo(tx.getBlockNumber().toString());
                                        userDepositInput.setTxHash(tx.getHash());
                                        userDepositInput.setCrypto("C98");
                                        userDepositInput.setAmount(amount);
                                        userAccountService.depositTomo(userDepositInput);
                                    }
                                }
                            });
                            userAccountService.updateLastBlockNo(Integer.toString(endBlockNo));
                        }
                    }
                    catch (IOException e) {
                        logger.info("IOException happened");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 0, 10000);


        /*else {
                if(tx.getValue().compareTo(new BigInteger("0")) > 0){
                    logger.info("From: {} To: {} Tomo: {}",tx.getFrom(),tx.getTo(),tx.getValue());
                    UserDepositInput userDepositInput = new UserDepositInput();
                    userDepositInput.setFromAddress(tx.getFrom());
                    userDepositInput.setToAddress(tx.getTo());
                    userDepositInput.setBlockNo(tx.getBlockNumber().toString());
                    userDepositInput.setTxHash(tx.getHash());
                    userDepositInput.setCrypto("VIC");
                    userDepositInput.setAmount(tx.getValue().toString());
                    userAccountService.depositTomo(userDepositInput);
                }

                if(!Objects.isNull(tx.getTo()) && tx.getTo().equals("0x0fd0288aaae91eaf935e2ec14b23486f86516c8c")){
                    logger.info("From: {} To: {} Input: {}",tx.getFrom(),tx.getTo(),tx.getInput());
                }
            }*/
    }
}
