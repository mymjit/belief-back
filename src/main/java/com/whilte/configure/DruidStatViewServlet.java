package com.whilte.configure;

import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.sql.DataSource;


/**
  * @date        : 2017/12/28
  * @author      : whilte
  * @Description : druid启动时的配置
  */
@WebServlet(
    urlPatterns = "/druid/*",
    initParams = {
        // 用户名
        @WebInitParam(name = "loginUsername", value = "admin"),
        // 密码
        @WebInitParam(name = "loginPassword", value = "admin"),
        // 禁用HTML页面上的“Reset All”功能
        @WebInitParam(name = "resetEnable", value = "false"),
        //允许清空统计数据
        @WebInitParam(name = "resetEnable", value = "true"),
        //访问白名单
        @WebInitParam(name = "allow", value = "127.0.0.1")
    }
)
public class DruidStatViewServlet extends StatViewServlet {
    @Bean(name = "dataSource")
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return DataSourceBuilder.create().type(com.alibaba.druid.pool.DruidDataSource.class).build();
    }
}
