package com.gly.springboot.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;

import javax.sql.DataSource;

/**
 * 此类的作用是读取配置文件中的数据,将数据源替换为Druid,
 *
 * @author: gly.
 * @date: 2020/3/31.
 * @time: 8:29.
 */
@Configuration
public class DataSourceConfig {
    /**
     * http://localhost:8080/druid/login.html 登录此网页进行监控
     * <p>
     * Druid是一个高效的数据查询系统，主要解决的是对于大量的基于时序的数据进行聚合查询。数据可以实时摄入，进入到Druid后立即可查，
     * 同时数据是几乎是不可变。通常是基于时序的事实事件，事实发生后进入Druid，外部系统就可以对该事实进行查询
     * 配置实践两种方式:
     * 第一种:采用分三个类进行配置, 读取配置文件类,配置视图监控类,配置监控拦截器.
     * 第二种:也可以合并为一个类,进行处理.
     * 具体可参考笔记
     */

    @Autowired
    private Environment env;

    /**
     * 此方法用于在spring中注册Druid数据源
     *
     * @return
     */
    @Bean  //声明其为bean实例
//        @ConfigurationProperties(prefix = "spring.datasource")
    @Primary  //在同样的DataSource中,优先使用被标注的DataSource
    public DataSource getDataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(env.getProperty("spring.datasource.druid.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.druid.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.druid.password"));
        return dataSource;
    }

    /**
     * 监视视图配置
     * 使用需要加上 @bean
     *
     * @return
     */
//    @Bean
    public ServletRegistrationBean druidStatViewServlet() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        registrationBean.addInitParameter("allow", "127.0.0.1");
        registrationBean.addInitParameter("loginUsername", "admin");
        registrationBean.addInitParameter("loginPassword", "admin");
        registrationBean.addInitParameter("resetEnable", "true");

        return registrationBean;
    }

    /**
     * 配置监控拦截器
     * 过滤掉不需要监控的资源
     * 使用需要加上 @bean
     *
     * @return
     */
//    @Bean
    public FilterRegistrationBean druidWebStatViewFilter() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean(new WebStatFilter());
        registrationBean.addInitParameter("urlPatterns", "/*");
        registrationBean.addInitParameter("exclusions", "*.js,*.gif,*.bmp,*.png,*.css,*.ico,/druid/*");//忽略资源

        return registrationBean;
    }
}
