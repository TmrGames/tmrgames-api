package com.tmr.tomoapi;

import com.tmr.tomoapi.service.UserAccountService;
import com.tmr.tomoapi.service.impl.UserAccountServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;

import java.io.Serial;

@MapperScan("com.tmr.tomoapi.mapper")
@SpringBootApplication
public class TomoApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(TomoApiApplication.class, args);
	}

}
