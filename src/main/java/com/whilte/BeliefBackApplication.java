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
  * ->@EnableCaching 针对常用的方法或类进行缓存  在需要的类上添加@Cacheable
  * ->@EnableScheduling 开启对计划任务的支持
  * ->@ServletComponentScan 拦截器组件的扫描 可见druid配置
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
