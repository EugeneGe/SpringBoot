package com.gly.springboot.config.druid;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 * 配置监控拦截器
 * Druid监控拦截器,过滤不需要监控的后缀
 *
 * @author: EugeneGe.
 * @date: 2020/2/30.
 * @time: 15:48.
 */
@WebFilter(filterName = "druidWebStatFilter",
        urlPatterns = "/*",
        initParams = {
                @WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*"),// 忽略资源
        })
public class DruidStatFilter extends WebStatFilter {
//有人想在这里直接继承StatFilter进行操作，但是这样会导致重复导入，导致无法运行，因为Druid之前已经有将其置入
}
