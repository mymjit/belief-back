package com.whilte;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;


/**
  * @date        : 2017/12/28
  * @author      : whilte
  * @Description :
  * ->@EnableCaching 实体类缓存
  * ->@EnableScheduling
  * ->@ServletComponentScan 拦截器扫描druid等配置的后台需要
  */
@EnableCaching
@EnableScheduling
@SpringBootApplication
@ServletComponentScan
public class BeliefBackApplication {

	public static void main(String[] args) {
		SpringApplication.run(BeliefBackApplication.class, args);
	}
}
