package com.gly.springboot.entity.sys;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * <p>
 * web日志记录表
 * </p>
 *
 * @author EugeneGe
 * @since 2022-08-13
 */
@TableName("sys_log")
@ApiModel(value = "SysLog对象", description = "web日志记录表")
public class SysLog extends Model<SysLog> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField("ip")
    @ApiModelProperty(value = "ip端口")
    private String ip;

    @TableField("url")
    @ApiModelProperty(value = "请求地址")
    private String url;

    @TableField("method")
    @ApiModelProperty(value = "http请求方法")
    private String method;

    @TableField("parameter")
    @ApiModelProperty(value = "请求参数")
    private String parameter;

    @TableField("controller")
    @ApiModelProperty(value = "访问controller")
    private String controller;

    @TableField("access_method")
    @ApiModelProperty(value = "调用方法")
    private String accessMethod;

    @TableField("headers")
    @ApiModelProperty(value = "headers")
    private String headers;

    @TableField("response")
    @ApiModelProperty(value = "响应体")
    private String response;

    @TableField("login_account")
    @ApiModelProperty(value = "登录名")
    private String loginAccount;

    @TableField("request_time")
    @ApiModelProperty(value = "请求时间")
    private String requestTime;

    @TableField("response_time")
    @ApiModelProperty(value = "响应时间")
    private String responseTime;

    @TableField("execution_duration")
    @ApiModelProperty(value = "耗时，毫秒")
    private Integer executionDuration;

    @TableField("user_agent")
    @ApiModelProperty(value = "用户访问代理客户端")
    private String userAgent;

    @TableField("response_length")
    @ApiModelProperty(value = "响应返回数据长度")
    private Integer responseLength;

    @TableField("cache_status")
    @ApiModelProperty(value = "是否为缓存数据，1.是来自缓存，0.不是")
    private Integer cacheStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public String getAccessMethod() {
        return accessMethod;
    }

    public void setAccessMethod(String accessMethod) {
        this.accessMethod = accessMethod;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getLoginAccount() {
        return loginAccount;
    }

    public void setLoginAccount(String loginAccount) {
        this.loginAccount = loginAccount;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getExecutionDuration() {
        return executionDuration;
    }

    public void setExecutionDuration(Integer executionDuration) {
        this.executionDuration = executionDuration;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public Integer getResponseLength() {
        return responseLength;
    }

    public void setResponseLength(Integer responseLength) {
        this.responseLength = responseLength;
    }

    public Integer getCacheStatus() {
        return cacheStatus;
    }

    public void setCacheStatus(Integer cacheStatus) {
        this.cacheStatus = cacheStatus;
    }

    @Override
    public Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("ip", getIp())
                .append("url", getUrl())
                .append("method", getMethod())
                .append("parameter", getParameter())
                .append("controller", getController())
                .append("accessMethod", getAccessMethod())
                .append("headers", getHeaders())
                .append("response", getResponse())
                .append("loginAccount", getLoginAccount())
                .append("requestTime", getRequestTime())
                .append("responseTime", getResponseTime())
                .append("executionDuration", getExecutionDuration())
                .append("userAgent", getUserAgent())
                .append("responseLength", getResponseLength())
                .append("cacheStatus", getCacheStatus())
                .toString();
    }
}

