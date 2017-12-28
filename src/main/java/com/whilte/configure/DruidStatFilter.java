package com.whilte.configure;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
  * @date        : 2017/12/28
  * @author      : whilte
  * @Description : 项目启动时启动Druid ,同时对文件等进行过滤
  */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*",
    initParams = {
        // 忽略资源
        @WebInitParam(name = "exclusions", value = "*.map,*.woff2,*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")
    }
)
public class DruidStatFilter extends WebStatFilter {
}
